package com.project.mytask.taskman.config;

/**
 * Created by Wanjiru Nyaga on 10-Mar-2016.
 */
public final class TaskManConstant {
    //Database Version
    public static final int DATABASE_VERSION = 1;

    //Database Name
    public static final String DATABASE_NAME = "taskMan";

    //Table Names
    public static final String TABLE_USER = "user";
    public static final String TABLE_PROJECT = "project";
    public static final String TABLE_CLIENT = "client";
    public static final String TABLE_TASK = "task";
    public static final String TABLE_BILLING = "billing";
    public static final String TABLE_EXPENSE = "expense";
    public static final String TABLE_REMINDER = "reminder";

    //USER Table - column names
    public static final String KEY_USERID = "Id";
    public static final String KEY_USERNAME = "name";
    public static final String KEY_USEREMAIL = "email";
    public static final String KEY_USERPASSWORD = "password";

    //PROJECT Table - column names
    public static final String KEY_PROJECTID = "Id";
    public static final String KEY_PROJECTNAME = "name";
    public static final String KEY_TOTALTIME = "totalTime";
    public static final String KEY_CLIENTIDFK = "clientIdFK";

    //CLIENT Table - column names
    public static final String KEY_CLIENTID = "Id";
    public static final String KEY_CLIENTNAME = "name";
    public static final String KEY_CLIENTEMAIL = "email";

    //TASK Table - column names
    public static final String KEY_TASKID = "Id";
    public static final String KEY_TASKNAME = "name";
    public static final String KEY_TASKDESCRIPTION = "description";
    public static final String KEY_TASKDATE = "date";
    public static final String KEY_TASKSTARTTIME = "startTime";
    public static final String KEY_TASKENDTIME = "endTime";
    public static final String KEY_TASKDURATION = "duration";
    public static final String KEY_PROJECTIDFK = "projectIdFK";

    //REMINDER Table - column names
    public static final String KEY_REMINDERID = "Id";
    public static final String KEY_REMINDERNAME = "name";
    public static final String KEY_REMINDERTIME = "time";

    //BILLING Table - column names
    public static final String KEY_BILLINGID = "Id";
    public static final String KEY_BILLINGRATE = "rate";
    public static final String KEY_BILLINGAMOUNT = "amount";

    //EXPENSE Table - column names
    public static final String KEY_EXPENSEID = "Id";
    public static final String KEY_EXPENSEDESCRIPTION = "description";
    public static final String KEY_EXPENSEAMOUNT = "amount";
    public static final String KEY_TASKIDFK = "taskIdFK";

}
