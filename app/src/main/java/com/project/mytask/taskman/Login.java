package com.project.mytask.taskman;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    private EditText loginEmailUsername;
    private EditText loginPassword;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loginEmailUsername = (EditText) findViewById(R.id.loginEmailUsername);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        loginButton = (Button) findViewById(R.id.loginButton);

    }

    @Override
    public boolean
    onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_projects:
                if (item.isChecked())
                    item.setChecked(false);
                else item.setChecked(true);
                startActivity(new Intent(this, NewProject.class));
                return true;

            case R.id.action_projects_list:
                if (item.isChecked())
                    item.setChecked(false);
                else item.setChecked(true);
                startActivity(new Intent(this, ProjectList.class));
                return true;

            case R.id.action_task_list:
                if (item.isChecked())
                    item.setChecked(false);
                else item.setChecked(true);
                startActivity(new Intent(this, TaskList.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
