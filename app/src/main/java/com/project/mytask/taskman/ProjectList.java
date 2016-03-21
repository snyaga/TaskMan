package com.project.mytask.taskman;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

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


        List<Project> projectsList = db.getAllProjects();

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        ArrayAdapter<Project> adapter = new ArrayAdapter<Project>(this,android.R.layout.simple_list_item_2, projectsList);
       // ListAdapter = new ArrayAdapter<String>(this, android.R.id.projectListView, adapter);

        projectListView.setAdapter(adapter);
    }

}
