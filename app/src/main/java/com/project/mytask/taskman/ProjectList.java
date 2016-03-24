package com.project.mytask.taskman;

import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.project.mytask.taskman.config.TaskManConstant;
import com.project.mytask.taskman.model.Project;
import com.project.mytask.taskman.model.User;
import com.project.mytask.taskman.service.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ProjectList extends AppCompatActivity {

    private ListView projectListView;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DBHelper(this);

        projectListView = (ListView) findViewById(R.id.projectListView);

//        List<User> projectsList = db.getAllUsers();
//        Log.i("my data", String.valueOf(projectsList));
//        for(User user : projectsList){
//            String log = "ID" + user.getId() + ", Name: " + user.getName().toString();
//            Log.d("Name", log);
//        }

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        //String[] myarray = {"Iphone", "Samsung"};
//        ArrayAdapter adapter = new ArrayAdapter<User>(this, R.layout.project_list_format, projectsList);
//        ListView projects = (ListView) findViewById(R.id.projectListView);

        Cursor cursor = db.getAllUsers();
        String [] fromFiledName = new String [] {TaskManConstant.KEY_USEREMAIL, TaskManConstant.KEY_USERNAME};
        int [] toViewIDs  = new int [] {R.id.user_id, R.id.user_name};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.project_list_format, cursor, fromFiledName, toViewIDs,0);
        ListView projects = (ListView) findViewById(R.id.projectListView);
        projects.setAdapter(myCursorAdapter);
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
