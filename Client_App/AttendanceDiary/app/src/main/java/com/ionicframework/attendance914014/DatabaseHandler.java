package com.ionicframework.attendance914014;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by akash on 28/12/14.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "attendanceDiary";

    private static final String TABLE_SUBJECTS = "Subjects";

    private static final String KEY_ID = "ID";
    private static final String KEY_NAME = "sub";
    private static final String KEY_PRESENT = "present";
    private static final String KEY_TOTAL = "absent";
    private static final String KEY_LAST_UPDATED = "last_updated";

    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_SUBJECTS_TABLE = "CREATE TABLE " + TABLE_SUBJECTS + "(" + KEY_ID +" INTEGER PRIMARY KEY," +
                KEY_NAME + " TEXT," + KEY_PRESENT + " INTEGER," + KEY_TOTAL + " INTEGER," + KEY_LAST_UPDATED + " TEXT)";

        sqLiteDatabase.execSQL(CREATE_SUBJECTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECTS);

        onCreate(sqLiteDatabase);
    }

    public ArrayList<Subject> getSubjects () {
        ArrayList<Subject> subjects = new ArrayList<Subject>();

        String selectQuery = "Select * from " + TABLE_SUBJECTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()) {
            do {
                Subject subject = new Subject();
                subject.setId(Integer.parseInt(cursor.getString(0)));
                subject.setName(cursor.getString(1));
                subject.setPresentClass(Integer.parseInt(cursor.getString(2)));
                subject.setTotalClass(Integer.parseInt(cursor.getString(3)));
                subject.setLastUpdated(cursor.getString(4));
                subjects.add(subject);
            } while (cursor.moveToNext());
        }
        db.close();
        return subjects;
    }

    public void updateSubject(Subject subject) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PRESENT,subject.getPresentClass());
        values.put(KEY_TOTAL,subject.getTotalClass());
        values.put(KEY_LAST_UPDATED,subject.getLastUpdated());
        db.update(TABLE_SUBJECTS,values,KEY_ID + " = ?",new String[] { String.valueOf(subject.getId())});
        db.close();
    }

    public void addSubject(Subject subject) {
        if (subject.getName() != "") {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_NAME, subject.getName());
            values.put(KEY_PRESENT, subject.getPresentClass());
            values.put(KEY_TOTAL, subject.getTotalClass());
            values.put(KEY_LAST_UPDATED,subject.getLastUpdated());

            db.insert(TABLE_SUBJECTS, null, values);
            db.close();
        }
    }

    // Getting single contact
    Subject getSubject(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SUBJECTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_PRESENT , KEY_TOTAL , KEY_LAST_UPDATED }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Subject subject = new Subject();
        subject.setId(Integer.parseInt(cursor.getString(0)));
        subject.setName(cursor.getString(1));
        subject.setPresentClass(Integer.parseInt(cursor.getString(2)));
        subject.setTotalClass(Integer.parseInt(cursor.getString(3)));
        subject.setLastUpdated(cursor.getString(4));
        db.close();
        // return contact
        return subject;
    }

    public void delete(Subject subject) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SUBJECTS,KEY_ID + " = ? ", new String[] {String.valueOf(subject.getId())});
        db.close();
    }

}
