package com.gnyblecraft.marcul.ideasproject.Registry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gnyblecraft.marcul.ideasproject.R;

/**
 * Created by AndroidDeveloper on 02.10.17.
 */

public class ResetPasswordActivity extends Activity {

    ImageView imageView;
    Button resetPassword;
    EditText email;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

        resetPassword = (Button) findViewById(R.id.resetPasswordButton);
        resetPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //отправить сообщение на мыло пользователя
                Intent intent = new Intent(ResetPasswordActivity.this,LogInActivity.class);
                startActivity(intent);

            }
        });

        textView = (TextView) findViewById(R.id.backToLogIn);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResetPasswordActivity.this,LogInActivity.class);
                startActivity(intent);
                finish();
            }
        });
        imageView = (ImageView) findViewById(R.id.imageCrossResetPassword);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResetPasswordActivity.this,LogInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
