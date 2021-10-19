package com.example.bloodoor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.alterac.blurkit.BlurLayout;

public class SignUp_BB extends AppCompatActivity {

    BlurLayout blurLayout;
    CardView signupcard, signincard;
    private EditText name, handlerName, mobileNo, phoneNo, email, address, city;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);     //removes title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();    //removes action bar
        blurLayout = findViewById(R.id.blurLayout);         //for blurring background
        setContentView(R.layout.activity_sign_up__b_b);

        //Hooks
        name = findViewById(R.id.enterBloodBankName);
        handlerName = findViewById(R.id.enterFullName);
        mobileNo = findViewById(R.id.mobileNumber);
        phoneNo = findViewById(R.id.phoneNumber);
        email = findViewById(R.id.emailID);
        address = findViewById(R.id.homeAddress);
        city = findViewById(R.id.city);



        //Save data in Firebase on Button Click
        signupcard = (CardView) findViewById(R.id.signupcard);
        signupcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regName = name.getText().toString();
                String regHandlerName = handlerName.getText().toString();
                String regMobileNo = mobileNo.getText().toString();
                String regPhoneNo = phoneNo.getText().toString();
                String regEmail = email.getText().toString();
                String regAddress = address.getText().toString();
                String regCity = city.getText().toString();
                bloodBankHelperClass helper = new bloodBankHelperClass(regName, regHandlerName, regMobileNo,
                        regPhoneNo, regEmail, regAddress, regCity);
                if (!mobileNo.getText().toString().trim().isEmpty()) {
                    if ((mobileNo.getText().toString().trim()).length() == 10) {


                        signupcard.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + mobileNo.getText().toString(),
                                90,
                                TimeUnit.SECONDS,
                                SignUp_BB.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                        signupcard.setVisibility(View.INVISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        signupcard.setVisibility(View.INVISIBLE);
                                        Toast.makeText(SignUp_BB.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                        signupcard.setVisibility(View.INVISIBLE);
                                        Intent intent = new Intent(getApplicationContext(), verifyotp_BB.class);
                                        bloodBankHelperClass helper = new bloodBankHelperClass(regName, regHandlerName, regMobileNo,
                                                regPhoneNo, regEmail, regAddress, regCity);
                                        intent.putExtra("mobileNo", mobileNo.getText().toString());
                                        intent.putExtra("backendotp", backendotp);
                                        intent.putExtra("Helper", helper);
                                        startActivity(intent);

                                    }
                                }
                        );

//                        Intent intent = new Intent(getApplicationContext(),verifyotp.class);
//                        intent.putExtra("mobile",entermobilenumber.getText().toString());
//                        startActivity(intent);

                    } else {
                        Toast.makeText(SignUp_BB.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignUp_BB.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signincard = (CardView) findViewById(R.id.signincard);
        signincard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), enternumber.class);
                startActivity(intent);
            }
        });
    }

    //FUnctions for making background blur
    @Override
    protected void onStart() {
        super.onStart();
        blurLayout = findViewById(R.id.blurLayout);
        blurLayout.startBlur();
    }

    //FUnctions for making background blur
    @Override
    protected void onStop() {
        blurLayout.pauseBlur();
        super.onStop();
    }
}

