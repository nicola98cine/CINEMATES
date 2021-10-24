package com.example.myapplicationcinemates1.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicationcinemates1.MainActivity;
import com.example.myapplicationcinemates1.PopUp;
import com.example.myapplicationcinemates1.R;
import com.example.myapplicationcinemates1.datamodel.UtentiModel;
import com.facebook.login.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import static com.example.myapplicationcinemates1.HomeActivity.TipoUtente;
import static com.example.myapplicationcinemates1.MainActivity.mFirebaseAuth;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    public static EditText fieldEmail;
    public static EditText fieldPassword;
    public static EditText fieldnome  ;
    public static EditText fieldcognome;
    public static EditText fieldnickname;
    public static EditText fielddatanascita;
    private Button emailCreateAccountButton;
    LoginPresenter lp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupactivity);
        Log.d(MainActivity.TAG,"inizio signup");
        lp=new LoginPresenter(SignupActivity.this);

        emailCreateAccountButton=findViewById( R.id.emailCreateAccountButton);
        emailCreateAccountButton.setOnClickListener(this);

    }

    private void create_Account(String email, String password) {
        Log.d(MainActivity.TAG, "createAccount:" + email);
        if (!lp.validateFormSignup(this.findViewById(android.R.id.content).getRootView())) {
            return;
        }
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(MainActivity.TAG, "createUserWithEmail:success");
                            FirebaseUser user = mFirebaseAuth.getCurrentUser();
                            UtentiModel u=new UtentiModel(lp.email,lp.nickname,"avatar00",lp.nome,lp.cognome,
                                    lp.datanascita,lp.password,"0","mobile");
                            lp.addUtente(u);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(MainActivity.TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            PopUp p=new PopUp(SignupActivity.this,"REGISTRAZIONE NON ESEGUITA: MAIL NON VALIDA ");
                            p.PopUpMsg1();
                        }

                    }
                });
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.emailCreateAccountButton) {
            lp.validateFormSignup(this.findViewById(android.R.id.content).getRootView());
            Log.d(MainActivity.TAG,"ispezione campi");
            create_Account(lp.email, lp.password);
        }
    }

}
