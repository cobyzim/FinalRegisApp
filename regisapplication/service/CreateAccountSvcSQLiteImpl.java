package zimmerman.regis.regisapplication.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import zimmerman.regis.regisapplication.domain.Account;
import zimmerman.regis.regisapplication.domain.Classes;

public class CreateAccountSvcSQLiteImpl extends SQLiteOpenHelper implements ICreateAccountSvc {

    private static final String TAG = "AccountSvcSQLiteImpl";
    //private static final String DBNAME = "accounts.db";
    //private static final String DBNAME2 = "accountsandclasses.db";
    private static final String DBNAME3 = "accountsandclassestwo.db";
    //private static final String DBNAME4 = "accountsandclassesthree.db";
    private static final int DBVERSION = 1;

    //first table in database to hold account info
    private final String CREATE_ACCOUNT_TABLE =
            "CREATE TABLE account (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "studentorfaculty TEXT NOT NULL, " +
                    "studentidorfacultyname TEXT UNIQUE NOT NULL, password TEXT NOT NULL)";

    //second table in database to hold student class schedule info
    private final String CREATE_SCHEDULE_TABLE =
            "CREATE TABLE IF NOT EXISTS schedule (id INTEGER PRIMARY KEY AUTOINCREMENT, " + //just added primary key autoincrement
                    "studentidorfacultyname TEXT NOT NULL, coursenametitle TEXT NOT NULL, " +
                    "meetinginfo TEXT, location TEXT NOT NULL, term TEXT NOT NULL, " +
                    "startdate TEXT NOT NULL)";

    private final String DROP_ACCOUNT_TABLE = "DROP TABLE IF EXISTS account";

    //drop second table
    private final String DROP_SCHEDULE_TABLE = "DROP TABLE IF EXISTS schedule";

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "onCreate");
        db.execSQL(CREATE_SCHEDULE_TABLE);
        db.execSQL(CREATE_ACCOUNT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        Log.i(TAG, "onUpgrade");
        db.execSQL(DROP_ACCOUNT_TABLE);
        db.execSQL(DROP_SCHEDULE_TABLE); //new
        onCreate(db);
    }

    private static CreateAccountSvcSQLiteImpl instance= null;

    public static CreateAccountSvcSQLiteImpl getInstance(Context context) {
        if (instance == null) {
            instance = new CreateAccountSvcSQLiteImpl(context);
        }
        return instance;
    }

    public CreateAccountSvcSQLiteImpl(Context context) {
        super(context, DBNAME3, null, DBVERSION);
    }

    //Check if username already exists in database
    public Boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from account where studentidorfacultyname = ?",
                new String[] {username});
        if (cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    //insert data into the account table
    public Boolean insert(String studentorfaculty, String studentidorfacultyname, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("studentorfaculty", studentorfaculty);
        contentValues.put("studentidorfacultyname", studentidorfacultyname);
        contentValues.put("password", password);
        long result = db.insert("account", null, contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    //insert data into the schedule table
    public Boolean insertSchedule(String studentidorfacultyname, String coursenametitle, String meetinginfo,
                    String location, String term, String startdate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("studentidorfacultyname", studentidorfacultyname);
        contentValues.put("coursenametitle", coursenametitle);
        contentValues.put("meetinginfo", meetinginfo);
        contentValues.put("location", location);
        contentValues.put("term", term);
        contentValues.put("startdate", startdate);
        long result = db.insert("schedule", null, contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean checkUsernamePassword (String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from account where studentidorfacultyname = ? " +
                "and password = ?", new String[] {username,password});
        if (cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean checkClass (String className) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from schedule where coursenametitle = ?",
                new String[] {className});
        if (cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public Classes create(Classes classes) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("studentidorfacultyname", classes.getStudentIdOrFacultyName());
        values.put("coursenametitle", classes.getCourseNameTitle());
        values.put("meetinginfo", classes.getMeetingInfo());
        values.put("location", classes.getLocation());
        values.put("term", classes.getTerm());
        values.put("startdate", classes.getStartDate());
        db.insert("schedule", null, values);

        Cursor cursor = db.rawQuery("SELECT last_insert_rowid()", null);
        cursor.moveToFirst();
        int id = cursor.getInt(0);
        classes.setId(id);
        cursor.close();
        db.close();
        return classes;
    }

    public List<Classes> retrieveAll() {
        List<Classes> classesList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT schedule.id, schedule.studentidorfacultyname, coursenametitle, " +
                "meetinginfo, location, term, startdate from schedule, account where " +
                "schedule.studentidorfacultyname = account.studentidorfacultyname", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Classes classes = getClasses(cursor);
            classesList.add(classes);
            cursor.moveToNext();
        }
        db.close();
        return classesList;
    }

    private Classes getClasses (Cursor cursor) {
        Classes classes = new Classes();
        classes.setId(cursor.getInt(0));
        classes.setStudentIdOrFacultyName(cursor.getString(1));
        classes.setCourseNameTitle(cursor.getString(2));
        classes.setMeetingInfo(cursor.getString(3));
        classes.setLocation(cursor.getString(4));
        classes.setTerm(cursor.getString(5));
        classes.setStartDate(cursor.getString(6));
        return classes;
    }

    public Classes update(Classes classes) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("studentidorfacultyname", classes.getStudentIdOrFacultyName());
        values.put("coursenametitle", classes.getCourseNameTitle());
        values.put("meetinginfo", classes.getMeetingInfo());
        values.put("location", classes.getLocation());
        values.put("term", classes.getTerm());
        values.put("startdate", classes.getStartDate());
        db.update("schedule", values, "id=" + classes.getId(), null);
        db.close();
        return classes;
    }

    public Classes delete(Classes classes) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("schedule", "id = " + classes.getId(), null);
        db.close();
        return classes;
    }

}