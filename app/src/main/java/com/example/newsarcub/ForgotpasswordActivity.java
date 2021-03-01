package com.example.newsarcub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ForgotpasswordActivity extends AppCompatActivity {


    FirebaseAuth auth;
    FirebaseUser user;
    EditText frgtPasstxt;

    Button BtnPasswordForgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        frgtPasstxt=findViewById(R.id.frgtPasstxt);
        BtnPasswordForgot=findViewById(R.id.BtnPasswordForgot);

        BtnPasswordForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailRecover=frgtPasstxt.getText().toString().trim();

                if(TextUtils.isEmpty(emailRecover)){
                    Toast.makeText(ForgotpasswordActivity.this, "Please Enter Your Email ID", Toast.LENGTH_SHORT).show();
                }
                else {
                    recoverPassword(emailRecover);
                }
            }
        });


    }

    private void recoverPassword(String emailRecover) {

        final SweetAlertDialog pDialogSecond = new SweetAlertDialog(ForgotpasswordActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialogSecond.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialogSecond.setTitleText("Please Wait");
        pDialogSecond.setContentText("Sending Email To Reset Password");
        pDialogSecond.setCancelable(false);
        pDialogSecond.setConfirmButton("Okay",null);
        pDialogSecond.show();

        auth.sendPasswordResetEmail(emailRecover).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    pDialogSecond.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    pDialogSecond.setTitle("Email Sent Successfully");
                    frgtPasstxt.setText("");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pDialogSecond.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                pDialogSecond.setTitle(""+e.getMessage());
            }
        });


    }


}
