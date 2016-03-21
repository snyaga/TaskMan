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

import com.project.mytask.taskman.model.Client;
import com.project.mytask.taskman.model.Project;
import com.project.mytask.taskman.model.User;
import com.project.mytask.taskman.service.DBHelper;

public class NewProject extends AppCompatActivity {

    private EditText editTextPName, editTextCName, editTextCEmail;
    private Button buttonCreateProject;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DBHelper(this);

        editTextPName = (EditText) findViewById(R.id.editTextPName);
        editTextCName = (EditText) findViewById(R.id.editTextCName);
        editTextCEmail = (EditText) findViewById(R.id.editTextCEmail);
        buttonCreateProject = (Button) findViewById(R.id.buttonCreateProject);

        buttonCreateProject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Project project = new Project();
                Client client = new Client();

                project.setName(editTextPName.toString());
                client.setName(editTextCName.toString());
                client.setEmail(editTextCName.toString());
                db.createProject(project);
                db.createClient(client);

            }
        });

    }

}
