package com.example.myapplicationcinemates1.ui.login;

import android.content.Context;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.example.myapplicationcinemates1.MainActivity;
import com.example.myapplicationcinemates1.PopUp;
import com.example.myapplicationcinemates1.R;
import com.example.myapplicationcinemates1.datamodel.UtentiAPIGW;
import com.example.myapplicationcinemates1.datamodel.UtentiModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

//import java.util.concurrent.Executor;

//import bolts.AndroidExecutors;

import org.apache.log4j.chainsaw.Main;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.example.myapplicationcinemates1.HomeActivity.TipoUtente;
import static com.example.myapplicationcinemates1.MainActivity.DAO;
import static com.example.myapplicationcinemates1.MainActivity.mFirebaseAuth;
//import static com.google.firebase.installations.FirebaseInstallations.CORE_POOL_SIZE;

public class LoginPresenter {
    UtentiAPIGW fa;
    public Context context;
    public String email;
    public String password;
    public String nome;
    public String cognome;
    public String nickname;
    public String datanascita;
    public int TipoLogin;

     public LoginPresenter(Context c) {
        fa = (UtentiAPIGW) DAO.getUtentiDAO();
        this.context=c;
    }

    public void addUtente(UtentiModel u) {
        Log.d(MainActivity.TAG,"USERID="+ email+","+password+","+nome+","+cognome+","+datanascita);

        fa.addUtente(u);
        System.out.println("UTENTE INSERITO, context="+context.toString());
        PopUp p=new PopUp(context,"REGISTRAZIONE OK");
        p.PopUpMsg1();

    }

    public boolean validateFormSignup(View v )  {
        Log.d(MainActivity.TAG,"validateForm");
        boolean valid = true;

        SignupActivity.fieldEmail=v.findViewById(R.id.fieldEMail);
        email = SignupActivity.fieldEmail.getText().toString();

        SignupActivity.fieldnome=v.findViewById(R.id.editText3);
        nome= SignupActivity.fieldnome.getText().toString();
        if(TextUtils.isEmpty(nome)){
            Log.d(MainActivity.TAG, "Nome Required");
            SignupActivity.fieldnome.setError("Required");
            valid=false;
        }
        else {
            SignupActivity.fieldnome.setError(null);
        }

        SignupActivity.fieldcognome=v.findViewById(R.id.editText4);
        cognome = SignupActivity.fieldcognome.getText().toString();
        if(TextUtils.isEmpty(cognome)){
            Log.d(MainActivity.TAG, "Cognome Required");
            SignupActivity.fieldcognome.setError("Required");
            valid=false;
        }
        else {
            SignupActivity.fieldcognome.setError(null);
        }

        SignupActivity.fieldnickname=v.findViewById(R.id.editText5);
        nickname = SignupActivity.fieldnickname.getText().toString();
        if(TextUtils.isEmpty(nickname)){
            Log.d(MainActivity.TAG, "Nickname Required");
            SignupActivity.fieldnickname.setError("Required");
            valid=false;
        }
        else {
            SignupActivity.fieldnickname.setError(null);
        }

        SignupActivity.fielddatanascita=v.findViewById(R.id.editTextDate);
        datanascita = SignupActivity.fielddatanascita.getText().toString();


        if (TextUtils.isEmpty(email)) {
            Log.d(MainActivity.TAG,"Mail Required");
            SignupActivity.fieldEmail.setError("Required.");
            valid = false;
        }   else {
            SignupActivity.fieldEmail.setError(null);
        }

        SignupActivity.fieldPassword=v.findViewById(R.id.fieldPassword);
        password = SignupActivity.fieldPassword.getText().toString();
        Log.d(MainActivity.TAG,"passwd="+password);
        if (TextUtils.isEmpty(password)) {
            SignupActivity.fieldPassword.setError("Required.");
            valid = false;
        } else {
            SignupActivity.fieldPassword.setError(null);
        }

        return valid;
    }

    public boolean validateFormSignin(View v) {
        Log.d(MainActivity.TAG,"Validate Form");
        boolean valid = true;

        MainActivity.fieldEmail=v.findViewById(R.id.fieldEmail);
        email = MainActivity.fieldEmail.getText().toString();

        if (TextUtils.isEmpty(email)) {
            MainActivity.fieldEmail.setError("Required.");
            valid = false;
        }   else {
            MainActivity.fieldEmail.setError(null);
        }

        MainActivity.fieldPassword=v.findViewById(R.id.fieldPassword);
        password = MainActivity.fieldPassword.getText().toString();

        if (TextUtils.isEmpty(password)) {
            MainActivity.fieldPassword.setError("Required.");
            valid = false;
        } else {
            MainActivity.fieldPassword.setError(null);
        }
        return valid;
    }


}
