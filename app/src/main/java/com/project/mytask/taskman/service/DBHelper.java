package com.project.mytask.taskman.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.project.mytask.taskman.config.TaskManConstant;
import com.project.mytask.taskman.model.Task;
import com.project.mytask.taskman.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wanjiru Nyaga on 10-Mar-2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";

    //Table Create Statements
    //USER Table - Create Statement
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TaskManConstant.TABLE_USER + "(" + TaskManConstant.KEY_USERID + " INTEGER PRIMARY KEY," + TaskManConstant.KEY_USERNAME + " TEXT,"
            + TaskManConstant.KEY_USEREMAIL + " TEXT," + TaskManConstant.KEY_USERPASSWORD + " TEXT" + ")";

    //PROJECT Table - Create Statement
    private static final String CREATE_TABLE_PROJECT = "CREATE TABLE "
            + TaskManConstant.TABLE_PROJECT + "(" + TaskManConstant.KEY_PROJECTID + " INTEGER PRIMARY KEY," + TaskManConstant.KEY_PROJECTNAME + " TEXT,"
            + TaskManConstant.KEY_TOTALTIME + " INTEGER" + ")";

    //CLIENT Table - Create Statement
    private static final String CREATE_TABLE_CLIENT = "CREATE TABLE "
            + TaskManConstant.TABLE_CLIENT + "(" + TaskManConstant.KEY_CLIENTID + " INTEGER PRIMARY KEY," + TaskManConstant.KEY_CLIENTNAME + " TEXT,"
            +TaskManConstant.KEY_CLIENTEMAIL + " TEXT" + ")";

    //TASK Table - Create Statement
    private static final String CREATE_TABLE_TASK = "CREATE TABLE "
            + TaskManConstant.TABLE_TASK + "(" + TaskManConstant.KEY_TASKID + " INTEGER PRIMARY KEY," + TaskManConstant.KEY_TASKNAME + " TEXT,"
            + TaskManConstant.KEY_TASKDESCRIPTION + " TEXT," + TaskManConstant.KEY_TASKDATE + " DATETIME," + TaskManConstant.KEY_TASKSTARTTIME + " DATETIME,"
            + TaskManConstant.KEY_TASKENDTIME + " DATETIME," + TaskManConstant.KEY_TASKDURATION + " INTEGER" + ")";

    //REMINDER Table - Create Statement
    private static final String CREATE_TABLE_REMINDER = "CREATE TABLE "
            + TaskManConstant.TABLE_REMINDER + "(" + TaskManConstant.KEY_REMINDERID + " INTEGER PRIMARY KEY," + TaskManConstant.KEY_REMINDERNAME + " TEXT,"
            +TaskManConstant.KEY_REMINDERTIME + " DATETIME" + ")";

    //BILLING Table - Create Statement
    private static final String CREATE_TABLE_BILLING = "CREATE TABLE "
            + TaskManConstant.TABLE_BILLING + "(" + TaskManConstant.KEY_BILLINGID + " INTEGER PRIMARY KEY," + TaskManConstant.KEY_BILLINGRATE + " INTEGER,"
            + TaskManConstant.KEY_BILLINGAMOUNT + " INTEGER" + ")";

    //EXPENSE Table - Create Statement
    private static final String CREATE_TABLE_EXPENSE = "CREATE TABLE "
            + TaskManConstant.TABLE_EXPENSE + "(" + TaskManConstant.KEY_EXPENSEID + " INTEGER PRIMARY KEY," + TaskManConstant.KEY_EXPENSEDESCRIPTION + " TEXT,"
            + TaskManConstant.KEY_EXPENSEAMOUNT + " INTEGER," + ")";

    public DBHelper(Context context){
        super(context, TaskManConstant.DATABASE_NAME, null, TaskManConstant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        //creating required tables
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_PROJECT);
        db.execSQL(CREATE_TABLE_CLIENT);
        db.execSQL(CREATE_TABLE_TASK);
        db.execSQL(CREATE_TABLE_REMINDER);
        db.execSQL(CREATE_TABLE_BILLING);
        db.execSQL(CREATE_TABLE_EXPENSE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TaskManConstant.TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TaskManConstant.TABLE_PROJECT);
        db.execSQL("DROP TABLE IF EXISTS " + TaskManConstant.TABLE_CLIENT);
        db.execSQL("DROP TABLE IF EXISTS " + TaskManConstant.TABLE_TASK);
        db.execSQL("DROP TABLE IF EXISTS " + TaskManConstant.TABLE_REMINDER);
        db.execSQL("DROP TABLE IF EXISTS " + TaskManConstant.TABLE_BILLING);
        db.execSQL("DROP TABLE IF EXISTS " + TaskManConstant.TABLE_EXPENSE);

        //create new tables
        onCreate(db);
    }

    //CRUD Operations
    //USER Table
    //Create new user
    public long createUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TaskManConstant.KEY_USERID, user.getId());
        values.put(TaskManConstant.KEY_USERNAME, user.getName());
        values.put(TaskManConstant.KEY_USEREMAIL, user.getEmail());
        values.put(TaskManConstant.KEY_USERPASSWORD, user.getPassword());
        long userId = db.insert(TaskManConstant.TABLE_USER, null, values);

        return userId;
}
    //Get single user

    public User getUser(long userId){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TaskManConstant.TABLE_USER + "WHERE"
                + TaskManConstant.KEY_USERID + " = " +userId;

        Log.i(LOG, selectQuery);

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        User user = new User();
        user.setId(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_USERID)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_USEREMAIL)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_USERPASSWORD)));

        return user;

    }
    //Get all users

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<User>();
        String selectQuery = "SELECT * FROM " + TaskManConstant.TABLE_USER;

        Log.i(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if(cursor.movetoFirst()){
            do{
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_USERID)));
                user.setName();
                user.setEmail(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_USEREMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_USERPASSWORD)));

        }
    }

    //Update single user

    //Delete single user

    //PROJECT Table
    //Create new project

    //Get single project

    //Get all projects

    //Update single project

    //Delete single project

    //CLIENT Table
    //Create new client

    //Get single client

    //Get all clients

    //Update single client

    //Delete single client

    //TASK Table
    //Create new task

    //Get single task

    //Get all tasks

    //Update single task

    //Delete single task

    //ALERT Table
    //Create new alert

    //Get single alert

    //Get all alerts

    //Update single alert

    //Delete single alert

    //BILLING Table


    //TRAVEL Table
    //Create new travel

    //Get single travel

    //Get all travels

    //Update single travel

    //Delete single travel


}
