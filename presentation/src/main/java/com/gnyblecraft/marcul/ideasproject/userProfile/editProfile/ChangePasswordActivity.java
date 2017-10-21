package com.gnyblecraft.marcul.ideasproject.userProfile.editProfile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.gnyblecraft.marcul.ideasproject.R;

/**
 * Created by lenovo on 17.10.2017.
 */

public class ChangePasswordActivity extends Activity{
    private EditText actualPass;
    private EditText newPass1;
    private EditText newPass2;
    private TextInputLayout oldPassLayout,newPassLayout1,newPassLayout2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        oldPassLayout = findViewById(R.id.text_input_change_oldPassword);
        newPassLayout1 = findViewById(R.id.text_input_change_newPassword1);
        newPassLayout2 = findViewById(R.id.text_input_change_newPassword2);
        actualPass = findViewById(R.id.change_oldPassword);
        newPass1 = findViewById(R.id.change_newPassword1);
        newPass2 = findViewById(R.id.change_newPassword2);
        ImageView cross = (ImageView) findViewById(R.id.imageCrossChangePassword);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangePasswordActivity.this,EditProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        final ImageView confirm = (ImageView) findViewById(R.id.imageConfirmNewPassword);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateAll()){
                    confirm.setAlpha(1.0f);
                }
            }
        });
    }

    private boolean checkPass() {
        if(newPass1.getText().equals(newPass2.getText()))
            return true;
        else
            return false;
    }

    private boolean validateAll() {
        if(validateActual()==true && validateNew1()==true && validateNew2()==true)
            return true;
        else
            return false;
    }

    private boolean validateActual() {
        if (actualPass.getText()==null) {
            actualPass.setError(getString(R.string.error_msg_name));
            return false;
        } else {
            oldPassLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateNew1() {
        if (newPass1.getText()==null) {
            newPass1.setError(getString(R.string.error_msg_name));
            return false;
        } else {
            newPassLayout1.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateNew2() {
        if (newPass2.getText()==null) {
            newPass2.setError(getString(R.string.error_msg_name));
            return false;
        } else if(!checkPass()) {
            newPass2.setError(getString(R.string.password_mismatch));
            return false;
        } else {
            newPassLayout2.setErrorEnabled(false);
        }
        return true;
    }

}
