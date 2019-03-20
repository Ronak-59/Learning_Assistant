package com.ionicframework.attendance914014;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by akash on 24/12/14.
 */
public class Subject {

    private long _id;
    private String _name;
    private int _totalClass;
    private int _presentClass;
    private String _lastUpdated;
    private ArrayList<Integer> _undoStack;

    public Subject() {
        Calendar c = Calendar.getInstance();
        _id = c.getTimeInMillis();
        _undoStack = new ArrayList<Integer>();
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public int getTotalClass() {
        return _totalClass;
    }

    public void setTotalClass(int totalClass) {
        _totalClass = totalClass;
    }

    public int getPresentClass() {
        return _presentClass;
    }

    public void setPresentClass(int presentClass) {
        _presentClass = presentClass;
    }

    public ArrayList<Integer> getUndoStack() {
        return _undoStack;
    }

    public void setUndoStack(ArrayList<Integer> undoStack) {
        _undoStack = undoStack;
    }

    public String getLastUpdated() {
        return _lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        _lastUpdated = lastUpdated;
    }
}
