package com.aspsine.fragmentnavigator.demo.ui.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aspsine.fragmentnavigator.demo.R;
import com.aspsine.fragmentnavigator.demo.broadcast.BroadcastManager;
import com.aspsine.fragmentnavigator.demo.utils.SharedPrefUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = (EditText) findViewById(R.id.et_email);
        etPassword  = (EditText)findViewById(R.id.et_password);
        Button button = (Button) findViewById(R.id.login_in_button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_in_button){
            tryLogin();
        }
    }

    void tryLogin(){
        String email = String.valueOf(etEmail.getText()).trim();
        String password = String.valueOf(etPassword.getText()).trim();

        if(check(email, password)){
            markUserLogin();
            notifyUserLogin();
            finish();
        }
    }

    boolean check(String email, String password){
        if (TextUtils.isEmpty(email)){
            etEmail.setError(getString(R.string.error_invalid_email));
            return false;
        }
        if (TextUtils.isEmpty(password)){
            etPassword.setError(getString(R.string.error_invalid_password));
            return false;
        }
        return true;
    }

    private void markUserLogin(){
        SharedPrefUtils.login(this);
    }

    private void notifyUserLogin(){
        BroadcastManager.sendLoginBroadcast(this, 1);
    }
}

