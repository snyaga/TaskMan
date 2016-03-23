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
import android.widget.TextView;

public class TaskView extends AppCompatActivity {

    private EditText editTextTaskName, editTextTaskDesc, editTextStartTime, editTextEndTime;
    private Button buttonTaskSave;
    private TextView textViewTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextTaskName = (EditText) findViewById(R.id.editTextTaskName);
        editTextTaskDesc = (EditText) findViewById(R.id.editTextTaskDesc);
        editTextStartTime = (EditText) findViewById(R.id.editTextStartTime);
        editTextEndTime = (EditText) findViewById(R.id.editTextEndTime);
        buttonTaskSave = (Button) findViewById(R.id.buttonTaskSave);
        textViewTask = (TextView) findViewById(R.id.textViewTask);

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
