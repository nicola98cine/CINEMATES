package com.example.myapplicationcinemates1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
//import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicationcinemates1.datamodel.UtentiModel;
import com.example.myapplicationcinemates1.ui.login.LoginPresenter;
import com.example.myapplicationcinemates1.ui.login.SignupActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

import static com.example.myapplicationcinemates1.R.string.default_web_client_id;
import static com.google.android.gms.auth.api.signin.GoogleSignIn.getClient;
import static com.google.android.gms.auth.api.signin.GoogleSignIn.getSignedInAccountFromIntent;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static TextView mUserNameTV;
    private static ImageView mUserImageIV;
    private static ProgressDialog mProgressDialog;
    private static LinearLayout mUserInfoLayout;

    public static EditText fieldEmail;
    public static EditText fieldPassword;

    private SignInButton mGLoginButton;
    private static LoginButton mLoginButton;
    private Button emailSignInButton;
    private Button emailCreateAccountButton;

    public static FirebaseAuth mFirebaseAuth;
    private CallbackManager mCallbackManager;
    private static GoogleSignInClient mGoogleSignInClient;
    private static LoginPresenter lp;
    public static String userid="";
    public static String FBuserid="";
    private static final int RC_SIGN_IN = 9001;
    Intent openPage1;
    public static final String TAG = "CinematesLogin";
    UtentiModel u;

    public static DAOFactory DAO;
    String time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "Inizio Elaborazione");

        mUserNameTV = findViewById(R.id.tv_user_name);
        mUserImageIV = findViewById(R.id.iv_user_image);
        mUserInfoLayout = findViewById(R.id.linear_layout_user_info);
        mFirebaseAuth = FirebaseAuth.getInstance();

        emailSignInButton =findViewById(R.id.SignInButton);
        emailSignInButton.setOnClickListener(this::onClick);

        emailCreateAccountButton=findViewById(R.id.emailCreateAccountButton);
        emailCreateAccountButton.setOnClickListener(this::onClick);

        mGLoginButton = findViewById(R.id.google_login_button);
        mGLoginButton.setSize(SignInButton.SIZE_WIDE);
        mGLoginButton.setOnClickListener(this::onClick) ;

        mLoginButton = findViewById(R.id.fb_login_button);
        mLoginButton.setReadPermissions("email", "public_profile");
        mLoginButton.setOnClickListener(this::onClick) ;

        Button but=findViewById(R.id.bt_log_out);
        but.setOnClickListener(this::onClick);

        System.out.println ("database="+getResources().getString(R.string.database));

        DAO=DAOFactory.getDAOInstance(this.getApplicationContext());
        lp=new LoginPresenter(MainActivity.this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = getClient(this, gso);

        mCallbackManager = CallbackManager.Factory.create();

        mLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "registerCallback : success");
                mLoginButton.setVisibility(View.GONE);
                handleTokenFacebook(loginResult.getAccessToken());
            }
            @Override
            public void onCancel() {
                Log.d(TAG, "registerCallback : onCancel , user null");
                updateUI(null);
            }
            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "registerCallback : FB exception");
                updateUI(null);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (lp.TipoLogin==1) {
            Log.d(TAG, "onActivityResult per FB " + requestCode);
            super.onActivityResult(requestCode, resultCode, data);
            Log.d(TAG, "onActivityResult FB");
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
        else if (lp.TipoLogin==2) {
            Log.d(TAG, "onActivityResult per Google inizio");
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == RC_SIGN_IN) {
                Task<GoogleSignInAccount> task = getSignedInAccountFromIntent(data);
                try {
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    Log.d(TAG, "onActivityResult per Google");
                    firebaseAuthWithGoogle(account);
                } catch (ApiException e) {
                    updateUI(null);
                }
            }
        }
    }

    private void handleTokenFacebook(AccessToken token) {
        Log.d(TAG, "handleAccessToken:" + token);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
      //  System.out.println("token =" + credential.toString());
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            FBuserid=user.getEmail().toString();
                            Toast.makeText(MainActivity.this, "Authentication Succeeded.", Toast.LENGTH_SHORT).show();
                            updateUI(user);
                        } else {
                            // If sign-in fails, a message will display to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });

    }

    private void GsignIn() {
        System.out.println("GSignin");
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void GsignOut() {
        System.out.println("GSignout");
        mFirebaseAuth.signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }

    private void signOut() {
        Log.d(TAG, "Signout, tipoLogin=" + Integer.toString(lp.TipoLogin));

        if (lp.TipoLogin==2) {
            GsignOut();
        }
        else {
            mFirebaseAuth.signOut();
            Log.d(TAG, "Signout, tipoLogin=" + Integer.toString(lp.TipoLogin));

            LoginManager.getInstance().logOut();
            updateUI(null);

            if (lp.TipoLogin==1)
            {

            }
            //TipoLogin=0;
        }
    }

    private void GoHome(String uid) {
        openPage1 = new Intent(MainActivity.this, HomeActivity.class);
        openPage1.putExtra("userid", uid);
        startActivity(openPage1);
    }
    private void GoSignup() {
        openPage1 = new Intent(MainActivity.this, SignupActivity.class);
        startActivity(openPage1);
    }

    //@Override
    public void onClick(View v) {
        Log.d(TAG, "onCLick View"); //+ Integer.toString(TipoLogin));
        int i = v.getId();

        if (i == R.id.google_login_button) {
            Log.d(TAG, "google Login Button");
            lp.TipoLogin=2;
            GsignIn();
        }
        else if (i == R.id.fb_login_button) {
            Log.d(TAG, "onCLick Login FB");
            lp.TipoLogin=1;
        }
        else if (i == R.id.emailCreateAccountButton) {
            GoSignup();

        } else if (i == R.id.SignInButton) {
            Log.d(TAG, "Button Login Interna");
            lp.TipoLogin=0;
            boolean valid=lp.validateFormSignin(this.findViewById(android.R.id.content).getRootView());
            if (valid) {
                //signIn(fieldEmail.getText().toString(), fieldPassword.getText().toString());
                signIn(lp.email, lp.password);
            }
        }
        else if (i == R.id.bt_log_out) {
            Log.d(TAG, "onCLick button log_out, TipoLogin="+Integer.toString(lp.TipoLogin));
            Log.d(TAG, "user="+ userid);

            if (lp.TipoLogin==2) {
                GsignOut();
                u=new UtentiModel(userid,"nick01","avatar00", userid.substring(0,6),
                        userid.substring(6,10),"1990-01-20","pwd010101","0","mobile");
                lp.addUtente(u);
                GoHome(userid);
            }
            else if (lp.TipoLogin==1) {
                signOut();
                u=new UtentiModel(userid,"nick01","avatar00", userid.substring(0,6),
                        userid.substring(6,10),"1990-01-20","pwd010101","0","mobile");
                lp.addUtente(u);
                GoHome(FBuserid);
            }
            else {
                if (userid == null ) {
                    userid=fieldEmail.getText().toString();;
                }
                signOut();
                GoHome(userid);
            }

        }

    }

    public void showProgressDialog() {
        Log.d(TAG, "showProgressDialog");
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        Log.d(TAG, "hideProgressDialog");
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void updateUI(FirebaseUser user) {
        hideProgressDialog();
        Log.d(TAG, "updateUI");

        if (user != null) {
            //System.out.println("updateui, user = "+ user.getEmail());
            Log.d(TAG, "updateUI" + user.getEmail() );

            mUserInfoLayout.setVisibility(View.VISIBLE);
            mUserNameTV.setText(user.getDisplayName());
            userid = user.getEmail().toString();
            for(UserInfo profile : user.getProviderData()) {
                if(FacebookAuthProvider.PROVIDER_ID.equals(profile.getProviderId())) {
                    userid = profile.getUid();
                    //System.out.println("updateui, userid = "+ userid);
                    Log.d(TAG, "updateUI" + userid );
                }
            }
            String imageUrl = "https://graph.facebook.com/" + userid + "/picture?type=large";
            Picasso.with(this)
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder)
                    .into(mUserImageIV);
        } else {
            //System.out.println("updateui, user is null ");
            mLoginButton.setVisibility(View.VISIBLE);
            mUserInfoLayout.setVisibility(View.GONE);
        }
    }


    /*
    private void xupdateGUI(FirebaseUser user) {
        if (user != null) {
            mLoginButton.setVisibility(View.GONE);
            fieldEmail.setVisibility(View.GONE);
            fieldPassword.setVisibility(View.GONE);
            mUserInfoLayout.setVisibility(View.VISIBLE);
            mUserNameTV.setText(user.getDisplayName());
            String imageUrl = user.getPhotoUrl().toString();
            imageUrl = imageUrl.replace("/s96-c/","/s200-c/");
            //String userId = "";
            for(UserInfo profile : user.getProviderData()){
                if(FacebookAuthProvider.PROVIDER_ID.equals(profile.getProviderId())) {
                    userid = profile.getUid();
                }
            }
            Picasso.with(this)
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder)
                    .into(mUserImageIV);
        } else {
            mGLoginButton.setVisibility(View.VISIBLE);
            mUserInfoLayout.setVisibility(View.GONE);
        }
    }
    */

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        //System.out.println("firebaseAuthGoogle"+account.getEmail());
        Log.d(TAG, "firebaseAuthGoogle"+account.getEmail() );

        showProgressDialog();
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "OnComplete Google");
                        if (task.isSuccessful()) {
                            Log.d(TAG, "OnComplete Google success");
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            userid=user.getEmail().toString();
                            Log.d(TAG, "Userid Google="+userid);
                            updateUI(user);
                        } else {
                            Log.d(TAG, "OnComplete Google failed");
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        hideProgressDialog();
                    }
                });
    }



    public boolean signIn(String email, String password) {
        final String msg="LOGIN terminato correttamente , proseguire con Cinemates";
        final String msgKO="LOGIN terminato con errore , verificare credenziali ";
        boolean[] esitoLogin = new boolean[1];
        Log.d(TAG, "signIn:" + email);

        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            System.out.println("prima del Dialog");
                            PopUp p=new PopUp(MainActivity.this,msg);
                            p.PopUpMsg1();
                            updateUI(user);
                            esitoLogin[0] =true;
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            Log.w(TAG, "signInWithEmail:failure");
                            PopUp p=new PopUp(MainActivity.this,msgKO);
                            p.PopUpMsg1();
                            updateUI(null);
                            esitoLogin[0] =false;
                        }
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "task not successful");
                            esitoLogin[0] =false;
                        }
                    }
                });
        return esitoLogin[0];
    }

}
