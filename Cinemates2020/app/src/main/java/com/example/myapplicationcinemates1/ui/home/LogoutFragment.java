package com.example.myapplicationcinemates1.ui.home;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.example.myapplicationcinemates1.MainActivity;
//import com.example.myapplicationcinemates1.R;

import static com.facebook.FacebookSdk.getApplicationContext;

public class LogoutFragment extends Fragment {

    public LogoutFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent openPage1 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(openPage1);
    }
}