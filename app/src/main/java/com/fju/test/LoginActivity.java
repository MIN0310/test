package com.fju.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText edUserid = findViewById(R.id.ed_userid);
        SharedPreferences setting =
                getSharedPreferences("test", MODE_PRIVATE);
        edUserid.setText(setting.getString("PREF_USERID", ""));
    }

    public void login(View view) {
        EditText edUserid = findViewById(R.id.ed_userid);
        EditText edPassword = findViewById(R.id.ed_password);
        String uid = edUserid.getText().toString();
        String pw = edPassword.getText().toString();
        if ("Jasmine".equals(uid) && "1234".equals(pw)) {
            SharedPreferences setting =
                    getSharedPreferences("test", MODE_PRIVATE);
            setting.edit()
                    .putString("PREF_USERID", uid)
                    .apply();

            Toast.makeText(this, "登入成功", Toast.LENGTH_LONG).show();
            getIntent().putExtra("LOGIN_USERID", uid);
            getIntent().putExtra("LOGIN_PASSWORD", pw);
            setResult(RESULT_OK, getIntent());
            finish();
        } else { //登入失敗
            new AlertDialog.Builder(this)
                    .setTitle("Test")
                    .setMessage("登入失敗")
                    .setPositiveButton("OK", null)
                    .show();

        }
    }

}
