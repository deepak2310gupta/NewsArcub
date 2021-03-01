package com.example.newsarcub;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class SignupinFragment extends Fragment {

    public SignupinFragment() {}

    ImageButton signupBackBtn;
    LinearLayout HelpLayouut,secondHelpLayout,thirdLin003;
    TextView signupbg12,signinbg;
    TextView iagreeforsign,txtagree,trmsconditions,txtSignupUserProfiles;
    CheckBox firstCheckbox;
    Button buttonSignUp,signingIn;
    EditText txtName00,txtEmail00,txtPassword00,txtMobile00,userEmailId,userPassword;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    TextView txtForgotPassword;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_signupin, container, false);

        firebaseAuth=FirebaseAuth.getInstance();
        user=firebaseAuth.getCurrentUser();
        signingIn=view.findViewById(R.id.signingIn);
        userPassword=view.findViewById(R.id.userPassword);
        userEmailId=view.findViewById(R.id.userEmailId);
        txtForgotPassword=view.findViewById(R.id.txtForgotPassword);
        signupBackBtn=view.findViewById(R.id.signupBackBtn);
        buttonSignUp=view.findViewById(R.id.buttonSignUp);
        secondHelpLayout=view.findViewById(R.id.secondHelpLayout);
        HelpLayouut=view.findViewById(R.id.HelpLayouut);
        txtMobile00=view.findViewById(R.id.txtMobile00);
        txtName00=view.findViewById(R.id.txtName00);
        txtEmail00=view.findViewById(R.id.txtEmail00);
        txtPassword00=view.findViewById(R.id.txtPassword00);


        signinbg=view.findViewById(R.id.signinbg);
        signupbg12=view.findViewById(R.id.signupbg12);
        iagreeforsign=view.findViewById(R.id.iagreeforsign);
        txtSignupUserProfiles=view.findViewById(R.id.txtSignupUserProfiles);
        txtagree=view.findViewById(R.id.txtagree);
        firstCheckbox=view.findViewById(R.id.firstCheckbox);
        thirdLin003=view.findViewById(R.id.thirdLin003);
        trmsconditions=view.findViewById(R.id.trmsconditions);


        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),ForgotpasswordActivity.class);
                getContext().startActivity(intent);

            }
        });


        signupbg12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupbg12.setBackgroundResource(R.drawable.signupbg);
                signinbg.setBackgroundColor(Color.parseColor("#FFFFFF"));
                signinbg.setTextColor(Color.parseColor("#000000"));
                HelpLayouut.setVisibility(View.VISIBLE);
                secondHelpLayout.setVisibility(View.GONE);
                iagreeforsign.setVisibility(View.VISIBLE);
                txtagree.setVisibility(View.VISIBLE);
                thirdLin003.setVisibility(View.VISIBLE);
                txtSignupUserProfiles.setVisibility(View.VISIBLE);
                firstCheckbox.setVisibility(View.VISIBLE);
                trmsconditions.setVisibility(View.VISIBLE);
                buttonSignUp.setVisibility(View.VISIBLE);
            }
        });

        signinbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinbg.setBackgroundResource(R.drawable.signupbg);
                signupbg12.setBackgroundColor(Color.parseColor("#FFFFFF"));
                signinbg.setTextColor(Color.parseColor("#FFFFFF"));
                signupbg12.setTextColor(Color.parseColor("#000000"));
                HelpLayouut.setVisibility(View.GONE);
                secondHelpLayout.setVisibility(View.VISIBLE);
                iagreeforsign.setVisibility(View.GONE);
                trmsconditions.setVisibility(View.GONE);
                txtagree.setVisibility(View.GONE);
                txtSignupUserProfiles.setVisibility(View.GONE);
                thirdLin003.setVisibility(View.GONE);
                firstCheckbox.setVisibility(View.GONE);
                buttonSignUp.setVisibility(View.GONE);
            }
        });



        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String one=txtName00.getText().toString();
                String two=txtEmail00.getText().toString();
                String three=txtPassword00.getText().toString();
                String four=txtMobile00.getText().toString();
                if(TextUtils.isEmpty(one) && TextUtils.isEmpty(two) && TextUtils.isEmpty(three) &&  TextUtils.isEmpty(four)){
                    Toast.makeText(getContext(), "Please Enter All Information", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(one)){
                    Toast.makeText(getContext(), "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(two)|| TextUtils.isEmpty(three)){
                    Toast.makeText(getContext(), "Please Enter Your Credentials", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(four)){
                    Toast.makeText(getContext(), "Please Enter Your Mobile Number", Toast.LENGTH_SHORT).show();
                }
                else if(!firstCheckbox.isChecked()){
                    Toast.makeText(getContext(), "Please Agree To Our Terms And Conditions", Toast.LENGTH_SHORT).show();
                }
                else{
                    registerTheUser(one,two,three,four);
                }

            }

        });


        signingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String one=userEmailId.getText().toString();
                String two=userPassword.getText().toString();

                if(TextUtils.isEmpty(one) || TextUtils.isEmpty(two))
                    Toast.makeText(getContext(), "Empty Credentials", Toast.LENGTH_SHORT).show();
                else
                    loginTheUser(one,two);
            }
        });

        return view;
    }


    private void loginTheUser(String one, String two) {

        final SweetAlertDialog pDialogSecond = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        pDialogSecond.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialogSecond.setTitleText("Please Wait");
        pDialogSecond.setContentText("Login User");
        pDialogSecond.setCancelable(false);
        pDialogSecond.setConfirmButton("Okay",null);
        pDialogSecond.show();
        firebaseAuth.signInWithEmailAndPassword(one, two).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                pDialogSecond.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                pDialogSecond.setTitle("Login Successfully");
                pDialogSecond.setContentText("");
                ProfileFragment myFragment123 = new ProfileFragment();
                FragmentTransaction fragmentTransaction123 = getFragmentManager().beginTransaction();
                fragmentTransaction123.replace(R.id.containers, myFragment123,"").commit();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                pDialogSecond.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                pDialogSecond.setContentText(""+e.getMessage());
                pDialogSecond.setTitle("Login Failed");

            }
        });

    }

    private void registerTheUser(final String one, final String two, String three, final String four) {

        final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Please Wait");
        pDialog.setContentText("Registering User");
        pDialog.setCancelable(false);
        pDialog.setConfirmButton("Okay",null);
        pDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(two,three).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                String timestamp= String.valueOf(System.currentTimeMillis());
                HashMap<String,Object> hashMap=new HashMap<>();
                hashMap.put("Name",one);
                hashMap.put("EmailId",two);
                hashMap.put("Phone",four);
                hashMap.put("UserId",user.getUid());
                hashMap.put("TimestampId",timestamp);
                hashMap.put("Subscribed","false");

                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("UsersApp");
                databaseReference.child(user.getUid()).setValue(hashMap);
                pDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                pDialog.setTitle("Registered Successfully");
                pDialog.setContentText("");
                ProfileFragment myFragment123 = new ProfileFragment();
                FragmentTransaction fragmentTransaction123 = getFragmentManager().beginTransaction();
                fragmentTransaction123.replace(R.id.containers, myFragment123,"").commit();



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                pDialog.setContentText(""+e.getMessage());
                pDialog.setTitle("Registeration Failed");


            }
        });



    }



}
