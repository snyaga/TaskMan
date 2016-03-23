package com.project.mytask.taskman;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    public void onClick(View v) {
        if (v.getId() == R.id.mainLoginButton) {
            startActivity(new Intent(this, Login.class));
        } else if (v.getId() == R.id.mainRegisterButton) {
            startActivity(new Intent(this, ProjectList.class));
        }
    }
}
