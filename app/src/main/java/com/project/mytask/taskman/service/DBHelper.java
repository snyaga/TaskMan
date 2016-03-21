package com.project.mytask.taskman.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.project.mytask.taskman.config.TaskManConstant;
import com.project.mytask.taskman.model.Billing;
import com.project.mytask.taskman.model.Client;
import com.project.mytask.taskman.model.Expense;
import com.project.mytask.taskman.model.Project;
import com.project.mytask.taskman.model.Reminder;
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
    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TaskManConstant.TABLE_USER + "("
            + TaskManConstant.KEY_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + TaskManConstant.KEY_USERNAME + " TEXT NOT NULL,"
            + TaskManConstant.KEY_USEREMAIL + " TEXT NOT NULL,"
            + TaskManConstant.KEY_USERPASSWORD + " TEXT NOT NULL"
            + ")";

    //PROJECT Table - Create Statement
    private static final String CREATE_TABLE_PROJECT = "CREATE TABLE " + TaskManConstant.TABLE_PROJECT + "("
            + TaskManConstant.KEY_PROJECTID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + TaskManConstant.KEY_PROJECTNAME + " TEXT NOT NULL,"
            + TaskManConstant.KEY_TOTALTIME + " INTEGER"
            + TaskManConstant.KEY_CLIENTIDFK + " INTEGER FOREIGN KEY (" +TaskManConstant.KEY_CLIENTIDFK +") REFERENCES "
            + TaskManConstant.TABLE_CLIENT + "(Id) " + ")";

    //CLIENT Table - Create Statement
    private static final String CREATE_TABLE_CLIENT = "CREATE TABLE "
            + TaskManConstant.TABLE_CLIENT + "(" + TaskManConstant.KEY_CLIENTID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + TaskManConstant.KEY_CLIENTNAME + " TEXT,"
            +TaskManConstant.KEY_CLIENTEMAIL + " TEXT" + ")";

    //TASK Table - Create Statement
    private static final String CREATE_TABLE_TASK = "CREATE TABLE " + TaskManConstant.TABLE_TASK + "("
            + TaskManConstant.KEY_TASKID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + TaskManConstant.KEY_TASKNAME + " TEXT,"
            + TaskManConstant.KEY_TASKDESCRIPTION + " TEXT,"
            + TaskManConstant.KEY_TASKDATE + " DATETIME,"
            + TaskManConstant.KEY_TASKSTARTTIME + " DATETIME,"
            + TaskManConstant.KEY_TASKENDTIME + " DATETIME,"
            + TaskManConstant.KEY_TASKDURATION + " INTEGER"
            + TaskManConstant.KEY_PROJECTIDFK + " INTEGER FOREIGN KEY (" +TaskManConstant.KEY_PROJECTIDFK +") REFERENCES "
            + TaskManConstant.TABLE_PROJECT + "(Id) " + ")";

    //REMINDER Table - Create Statement
    private static final String CREATE_TABLE_REMINDER = "CREATE TABLE " + TaskManConstant.TABLE_REMINDER + "("
            + TaskManConstant.KEY_REMINDERID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + TaskManConstant.KEY_REMINDERNAME + " TEXT,"
            + TaskManConstant.KEY_REMINDERTIME + " DATETIME"
            + TaskManConstant.KEY_TASKIDFK + " INTEGER FOREIGN KEY (" +TaskManConstant.KEY_TASKIDFK +") REFERENCES "
            + TaskManConstant.TABLE_TASK + "(Id) " + ")";

    //BILLING Table - Create Statement
    private static final String CREATE_TABLE_BILLING = "CREATE TABLE " + TaskManConstant.TABLE_BILLING + "("
            + TaskManConstant.KEY_BILLINGID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + TaskManConstant.KEY_BILLINGRATE + " INTEGER,"
            + TaskManConstant.KEY_BILLINGAMOUNT + " INTEGER"
            + TaskManConstant.KEY_PROJECTIDFK + " INTEGER FOREIGN KEY (" +TaskManConstant.KEY_PROJECTIDFK +") REFERENCES "
            + TaskManConstant.TABLE_PROJECT + "(Id) " + ")";

    //EXPENSE Table - Create Statement
    private static final String CREATE_TABLE_EXPENSE = "CREATE TABLE " + TaskManConstant.TABLE_EXPENSE + "("
            + TaskManConstant.KEY_EXPENSEID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + TaskManConstant.KEY_EXPENSEDESCRIPTION + " TEXT,"
            + TaskManConstant.KEY_EXPENSEAMOUNT + " INTEGER"
            + TaskManConstant.KEY_TASKIDFK + " INTEGER FOREIGN KEY (" +TaskManConstant.KEY_TASKIDFK +") REFERENCES "
            + TaskManConstant.TABLE_TASK + "(Id) " + ")";

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
        //Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
       // values.put(TaskManConstant.KEY_USERID, user.getId());
        values.put(TaskManConstant.KEY_USERNAME, user.getName());
        values.put(TaskManConstant.KEY_USEREMAIL, user.getEmail());
        values.put(TaskManConstant.KEY_USERPASSWORD, user.getPassword());
        //Insert row
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
        user.setName(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_USERNAME)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_USEREMAIL)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_USERPASSWORD)));

        return user;

    }
    //Get all users

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        String selectQuery = "SELECT * FROM " + TaskManConstant.TABLE_USER;

        Log.i(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_USERID)));
                user.setName(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_USERNAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_USEREMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_USERPASSWORD)));

                users.add(user);
            } while (cursor.moveToNext());
        }
        return users;
    }

    //Update single user
        public int updateUser(User user) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(TaskManConstant.KEY_USERNAME, user.getName());
            values.put(TaskManConstant.KEY_USEREMAIL, user.getEmail());
            values.put(TaskManConstant.KEY_USERPASSWORD, user.getPassword());

            //updating row
            return db.update(TaskManConstant.TABLE_USER, values, TaskManConstant.KEY_USERID + "= ?",
                    new String[]{String.valueOf(user.getId())});
    }

    //Delete single user
    public void deleteUser(long userId){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TaskManConstant.TABLE_USER, TaskManConstant.KEY_USERID + " = ?",
                new String[]{String.valueOf(userId)});
    }

    //PROJECT Table
    //Create new project
    public long createProject(Project project) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(TaskManConstant.KEY_PROJECTID, project.getId());
        values.put(TaskManConstant.KEY_PROJECTNAME, project.getName());
        long projectId = db.insert(TaskManConstant.TABLE_PROJECT, null, values);

        return projectId;
    }

    //Get single project
    public Project getProject(long projectId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TaskManConstant.TABLE_PROJECT + "WHERE"
                + TaskManConstant.KEY_PROJECTID + " = " + projectId;

        Log.i(LOG, selectQuery);

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        Project project = new Project();
        project.setId(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_PROJECTID)));
        project.setName(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_PROJECTNAME)));

        return project;
    }

    //Get all project
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<Project>();
        String selectQuery = "SELECT * FROM " + TaskManConstant.TABLE_PROJECT;

        Log.i(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Project project = new Project();
                project.setId(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_PROJECTID)));
                project.setName(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_PROJECTNAME)));

                projects.add(project);
            } while (cursor.moveToNext());
        }
        return projects;
    }

    //Update single project
    public int updateProject(Project project) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TaskManConstant.KEY_PROJECTNAME, project.getName());

        //updating row
        return db.update(TaskManConstant.TABLE_PROJECT, values, TaskManConstant.KEY_PROJECTID + "= ?",
                new String[]{String.valueOf(project.getId())});
    }

    //Delete single project
    public void deleteProject(long projectId){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TaskManConstant.TABLE_PROJECT, TaskManConstant.KEY_PROJECTID + " = ?",
                new String[]{String.valueOf(projectId)});
    }

    //CLIENT Table
    //Create new client
    public long createClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(TaskManConstant.KEY_CLIENTID, client.getId());
        values.put(TaskManConstant.KEY_CLIENTNAME, client.getName());
        values.put(TaskManConstant.KEY_CLIENTEMAIL, client.getEmail());
        ;
        long clientId = db.insert(TaskManConstant.TABLE_CLIENT, null, values);

        return clientId;
    }

    //Get single client
    public Client getClient(long clientId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TaskManConstant.TABLE_CLIENT + "WHERE"
                + TaskManConstant.KEY_CLIENTID + " = " + clientId;

        Log.i(LOG, selectQuery);

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        Client client = new Client();
        client.setId(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_CLIENTID)));
        client.setName(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_CLIENTNAME)));
        client.setEmail(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_CLIENTEMAIL)));

        return client;
    }

    //Get all client
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<Client>();
        String selectQuery = "SELECT * FROM " + TaskManConstant.TABLE_CLIENT;

        Log.i(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Client client = new Client();
                client.setId(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_CLIENTID)));
                client.setName(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_CLIENTNAME)));
                client.setEmail(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_CLIENTEMAIL)));

                clients.add(client);
            } while (cursor.moveToNext());
        }
        return clients;
    }

    //Update single client
    public int updateClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TaskManConstant.KEY_CLIENTNAME, client.getName());
        values.put(TaskManConstant.KEY_CLIENTEMAIL, client.getEmail());

        //updating row
        return db.update(TaskManConstant.TABLE_CLIENT, values, TaskManConstant.KEY_CLIENTID + "= ?",
                new String[]{String.valueOf(client.getId())});
    }

    //Delete single client
    public void deleteClient(long clientId){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TaskManConstant.TABLE_CLIENT, TaskManConstant.KEY_CLIENTID + " = ?",
                new String[]{String.valueOf(clientId)});
    }

    //TASK Table
    //Create new task
    public long createTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(TaskManConstant.KEY_TASKID, task.getId());
        values.put(TaskManConstant.KEY_TASKNAME, task.getName());
        values.put(TaskManConstant.KEY_TASKDESCRIPTION, task.getDescription());
        values.put(TaskManConstant.KEY_TASKDATE, task.getDate());
        values.put(TaskManConstant.KEY_TASKSTARTTIME, task.getStartTime());
        values.put(TaskManConstant.KEY_TASKENDTIME, task.getEndTime());
        values.put(TaskManConstant.KEY_TASKDURATION, task.getDuration());
        //Insert row
        long taskId = db.insert(TaskManConstant.TABLE_TASK, null, values);

        return taskId;
    }

    //Get single task
    public Task getTask(long taskId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TaskManConstant.TABLE_TASK + "WHERE"
                + TaskManConstant.KEY_TASKID + " = " + taskId;

        Log.i(LOG, selectQuery);

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        Task task = new Task();
        task.setId(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_TASKID)));
        task.setName(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_TASKNAME)));
        task.setDescription(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_TASKDESCRIPTION)));
        task.setDate(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_TASKDATE)));
        task.setStartTime(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_TASKSTARTTIME)));
        task.setEndTime(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_TASKENDTIME)));
        task.setDuration(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_TASKDURATION)));

        return task;

    }
    //Get all task
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<Task>();
        String selectQuery = "SELECT * FROM " + TaskManConstant.TABLE_TASK;

        Log.i(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_TASKID)));
                task.setName(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_TASKNAME)));
                task.setDescription(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_TASKDESCRIPTION)));
                task.setDate(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_TASKDATE)));
                task.setStartTime(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_TASKSTARTTIME)));
                task.setEndTime(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_TASKENDTIME)));
                task.setDuration(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_TASKDURATION)));

                tasks.add(task);
            } while (cursor.moveToNext());
        }
        return tasks;
    }

    //Update single task
    public int updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TaskManConstant.KEY_TASKNAME, task.getName());
        values.put(TaskManConstant.KEY_TASKDESCRIPTION, task.getDescription());
        values.put(TaskManConstant.KEY_TASKDATE, task.getDate());
        values.put(TaskManConstant.KEY_TASKSTARTTIME, task.getStartTime());
        values.put(TaskManConstant.KEY_TASKENDTIME, task.getEndTime());
        values.put(TaskManConstant.KEY_TASKDURATION, task.getDuration());

        //updating row
        return db.update(TaskManConstant.TABLE_TASK, values, TaskManConstant.KEY_TASKID + "= ?",
                new String[]{String.valueOf(task.getId())});
    }

    //Delete single user
    public void deleteTask(long taskId){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TaskManConstant.TABLE_TASK, TaskManConstant.KEY_TASKID + " = ?",
                new String[]{String.valueOf(taskId)});
    }

    //REMINDER Table
    //Create new reminder
    public long createReminder(Reminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(TaskManConstant.KEY_REMINDERID, reminder.getId());
        values.put(TaskManConstant.KEY_REMINDERNAME, reminder.getName());
        values.put(TaskManConstant.KEY_REMINDERTIME, reminder.getTime());
        //Insert row
        long reminderId = db.insert(TaskManConstant.TABLE_REMINDER, null, values);

        return reminderId;
    }

    //Get single reminder
    public Reminder getReminder(long reminderId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TaskManConstant.TABLE_REMINDER + "WHERE"
                + TaskManConstant.KEY_REMINDERID + " = " + reminderId;

        Log.i(LOG, selectQuery);

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        Reminder reminder = new Reminder();
        reminder.setId(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_REMINDERID)));
        reminder.setName(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_REMINDERNAME)));
        reminder.setTime(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_REMINDERTIME)));

        return reminder;
    }

    //Get all reminder
    public List<Reminder> getAllReminders() {
        List<Reminder> reminders = new ArrayList<Reminder>();
        String selectQuery = "SELECT * FROM " + TaskManConstant.TABLE_REMINDER;

        Log.i(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Reminder reminder = new Reminder();
                reminder.setId(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_REMINDERID)));
                reminder.setName(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_REMINDERNAME)));
                reminder.setTime(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_REMINDERTIME)));

                reminders.add(reminder);
            } while (cursor.moveToNext());
        }
        return reminders;
    }

    //Update single reminder
    public int updateReminder(Reminder reminder) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TaskManConstant.KEY_REMINDERNAME, reminder.getName());
        values.put(TaskManConstant.KEY_REMINDERTIME, reminder.getTime());

        //updating row
        return db.update(TaskManConstant.TABLE_REMINDER, values, TaskManConstant.KEY_REMINDERID + "= ?",
                new String[]{String.valueOf(reminder.getId())});
    }

    //Delete single reminder
    public void deleteReminder(long reminderId){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TaskManConstant.TABLE_REMINDER, TaskManConstant.KEY_REMINDERID + " = ?",
                new String[]{String.valueOf(reminderId)});
    }

    //BILLING Table
    // Create new billing
    public long createBilling(Billing billing) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(TaskManConstant.KEY_BILLINGID, billing.getId());
        values.put(TaskManConstant.KEY_BILLINGRATE, billing.getRate());
        values.put(TaskManConstant.KEY_BILLINGAMOUNT, billing.getAmount());

        //Insert row
        long billingId = db.insert(TaskManConstant.TABLE_BILLING, null, values);

        return billingId;
    }

    //Get single billing
    public Billing getBilling(long billingId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TaskManConstant.TABLE_BILLING + "WHERE"
                + TaskManConstant.KEY_BILLINGID + " = " + billingId;

        Log.i(LOG, selectQuery);

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        Billing billing = new Billing();
        billing.setId(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_BILLINGID)));
        billing.setRate(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_BILLINGRATE)));
        billing.setAmount(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_BILLINGAMOUNT)));

        return billing;
    }
    //Get all billing
    public List<Billing> getAllBillings() {
        List<Billing> billings = new ArrayList<Billing>();
        String selectQuery = "SELECT * FROM " + TaskManConstant.TABLE_BILLING;

        Log.i(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Billing billing = new Billing();
                billing.setId(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_BILLINGID)));
                billing.setRate(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_BILLINGRATE)));
                billing.setAmount(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_BILLINGAMOUNT)));

                billings.add(billing);
            } while (cursor.moveToNext());
        }
        return billings;
    }

    //Update single billing
    public int updateBilling(Billing billing) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TaskManConstant.KEY_BILLINGRATE, billing.getRate());
        values.put(TaskManConstant.KEY_BILLINGAMOUNT, billing.getAmount());

        //updating row
        return db.update(TaskManConstant.TABLE_BILLING, values, TaskManConstant.KEY_BILLINGID + "= ?",
                new String[]{String.valueOf(billing.getId())});
    }

    //Delete single billing
    public void deleteBilling(long billingId){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TaskManConstant.TABLE_BILLING, TaskManConstant.KEY_BILLINGID + " = ?",
                new String[]{String.valueOf(billingId)});
    }
    //EXPENSE Table
    //Create new expense
    public long createExpense(Expense expense) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(TaskManConstant.KEY_EXPENSEID, expense.getId());
        values.put(TaskManConstant.KEY_EXPENSEDESCRIPTION, expense.getDescription());
        values.put(TaskManConstant.KEY_EXPENSEAMOUNT, expense.getAmount());

        //Insert row
        long expenseId = db.insert(TaskManConstant.TABLE_EXPENSE, null, values);

        return expenseId;
    }

    //Get single expense
    public Expense getExpense(long expenseId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TaskManConstant.TABLE_EXPENSE + "WHERE"
                + TaskManConstant.KEY_EXPENSEID + " = " + expenseId;

        Log.i(LOG, selectQuery);

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        Expense expense = new Expense();
        expense.setId(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_EXPENSEID)));
        expense.setDescription(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_EXPENSEDESCRIPTION)));
        expense.setAmount(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_EXPENSEAMOUNT)));

        return expense;
    }
    //Get all expense
    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<Expense>();
        String selectQuery = "SELECT * FROM " + TaskManConstant.TABLE_EXPENSE;

        Log.i(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Expense expense = new Expense();
                expense.setId(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_EXPENSEID)));
                expense.setDescription(cursor.getString(cursor.getColumnIndex(TaskManConstant.KEY_EXPENSEDESCRIPTION)));
                expense.setAmount(cursor.getInt(cursor.getColumnIndex(TaskManConstant.KEY_EXPENSEAMOUNT)));

                expenses.add(expense);
            } while (cursor.moveToNext());
        }
        return expenses;
    }

    //Update single expense
    public int updateExpense(Expense expense) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TaskManConstant.KEY_EXPENSEDESCRIPTION, expense.getDescription());
        values.put(TaskManConstant.KEY_EXPENSEAMOUNT, expense.getAmount());

        //updating row
        return db.update(TaskManConstant.TABLE_EXPENSE, values, TaskManConstant.KEY_EXPENSEID + "= ?",
                new String[]{String.valueOf(expense.getId())});
    }

    //Delete single expense
    public void deleteExpense(long expenseId){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TaskManConstant.TABLE_EXPENSE, TaskManConstant.KEY_EXPENSEID + " = ?",
                new String[]{String.valueOf(expenseId)});
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}
