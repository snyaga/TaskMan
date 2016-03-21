package com.project.mytask.taskman;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.mytask.taskman.model.User;
import com.project.mytask.taskman.service.DBHelper;

import java.util.List;

public class Register extends AppCompatActivity {

    private EditText registerUserName, registerEmail, registerPassword;
    private TextView textViewUser;
    private Button registerButton;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DBHelper(this);

        registerUserName = (EditText) findViewById(R.id.registerUserName);
        registerEmail = (EditText) findViewById(R.id.registerEmail);
        registerPassword = (EditText) findViewById(R.id.registerPassword);
        textViewUser = (TextView) findViewById(R.id.textViewUser);
        registerButton = (Button) findViewById(R.id.registerButton);


        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                User user = new User();
                user.setId(0);
                user.setName(registerUserName.toString());
                user.setEmail(registerEmail.toString());
                user.setPassword(registerPassword.toString());
                db.createUser(user);

                db.getAllUsers();
                //String usersList = db.getAllUsers().toString();
                List<User> usersList = db.getAllUsers();
                String string = "UserName: " + usersList.getClass().getName() + "\nEmail:  \n\n";

                textViewUser.setText(string);


            }
        });
    }
}




