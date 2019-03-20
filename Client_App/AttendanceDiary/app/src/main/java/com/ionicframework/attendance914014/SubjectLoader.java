package com.ionicframework.attendance914014;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by akash on 24/12/14.
 */
public class SubjectLoader {



    private static SubjectLoader sSubjectLoader;
    private Context _AppContext;

    private ArrayList<Subject> _subjects;

    private SubjectLoader(Context appContext) {
        _AppContext = appContext;

        _subjects = new ArrayList<Subject>();
        
        //Code to populate Subjects use it to get it from db 

       /* for (int i = 0; i < 5; i++) {
            Subject sub = new Subject();
            sub.setName("Subject " + i);
            sub.setPresentClass(7);
            sub.setTotalClass(10);
            _subjects.add(sub);
        }*/

        DatabaseHandler databaseHandler = new DatabaseHandler(appContext);

        _subjects = databaseHandler.getSubjects();

    }

    public static SubjectLoader get(Context c) {
        if (sSubjectLoader == null) {
            sSubjectLoader = new SubjectLoader(c.getApplicationContext());
        }

        return sSubjectLoader;
    }

    public ArrayList<Subject> getSubjects() {
        return _subjects;
    }

    public Subject getSubject(long id) {
        for (Subject s: _subjects) {
            if (s.getId() == id) {
                return s;
            }
        }

        return null;
    }

}
