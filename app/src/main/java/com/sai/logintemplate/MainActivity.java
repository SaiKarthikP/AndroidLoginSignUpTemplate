package com.sai.logintemplate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    Boolean signUpModeActive = true;
    TextView changeSignUpModeTextView;
    EditText password2EditText;
    EditText emailEditText;
    EditText usernameEditText;
    EditText passwordEditText;


    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        //check if enter key is pressed
        if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            SignUp(view);
        }
        return false;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.changeSignUpModeTextView) {

            Button signUpButton = (Button) findViewById(R.id.signupButton);
            if (signUpModeActive) {
                signUpModeActive = false;
                signUpButton.setText("Login");
                changeSignUpModeTextView.setText("or, Signup");
                password2EditText.setVisibility(View.GONE);
                emailEditText.setVisibility(View.GONE);

                passwordEditText.setOnKeyListener(this);
                password2EditText.setOnKeyListener(null);
//                usernameEditText.setHint("Enter your username...");
//                passwordEditText.setHint("Enter your password...");
            } else {
                signUpModeActive = true;
                signUpButton.setText("Signup");
                changeSignUpModeTextView.setText("or, Login");
                password2EditText.setVisibility(View.VISIBLE);
                emailEditText.setVisibility(View.VISIBLE);

                passwordEditText.setOnKeyListener(null);
                password2EditText.setOnKeyListener(this);
//                usernameEditText.setHint("Enter a username...");
//                passwordEditText.setHint("Enter a password...");
            }

            //TODO this not working, Hiding keyboard
        } else if (view.getId() == R.id.backgroundRelativeLayout) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromInputMethod(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void SignUp(View view) {

        if (signUpModeActive && (usernameEditText.getText().toString().matches("")
                || passwordEditText.getText().toString().matches("")
                || emailEditText.getText().toString().matches("")
                || password2EditText.getText().toString().matches(""))) {
            Toast.makeText(this, "All fields are required.", Toast.LENGTH_LONG).show();
        } else if (signUpModeActive && !passwordEditText.getText().toString().matches(password2EditText.getText().toString())) {
            Toast.makeText(this, "The passwords do not match.", Toast.LENGTH_LONG).show();

        } else if (!signUpModeActive && (usernameEditText.getText().toString().matches("")
                || passwordEditText.getText().toString().matches(""))) {
            Toast.makeText(this, "All fields are required.", Toast.LENGTH_LONG).show();

        } else {

            if (signUpModeActive) {
                if (usernameEditText.getText().toString().matches("")
                        || passwordEditText.getText().toString().matches("")
                        || emailEditText.getText().toString().matches("")) {
                    Toast.makeText(this, "All fields are required.", Toast.LENGTH_LONG).show();

                    //TODO new user sign up here

                } else {


                    //TODO existing user login verification here
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        changeSignUpModeTextView = (TextView) findViewById(R.id.changeSignUpModeTextView);
        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        password2EditText = (EditText) findViewById(R.id.password2EditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        RelativeLayout backgroundRelativeLayout = (RelativeLayout) findViewById(R.id.backgroundRelativeLayout);

        backgroundRelativeLayout.setOnClickListener(this);

        changeSignUpModeTextView.setOnClickListener(this);
        password2EditText.setOnKeyListener(this);
        passwordEditText.setOnKeyListener(null);


    }


}