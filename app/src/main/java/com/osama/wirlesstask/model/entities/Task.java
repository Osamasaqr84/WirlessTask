package com.osama.wirlesstask.model.entities;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Task")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;

    public Task(String tasktitle, String taskdescription, Boolean isDone) {
        this.tasktitle = tasktitle;
        this.isDone = isDone;
        this.taskdescription = taskdescription;
    }

    @ColumnInfo(name = "tasktitle")
   private String tasktitle;

    @ColumnInfo(name = "isdone")
    private Boolean isDone;

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "taskdescription")
    private String taskdescription;


    public String getTasktitle() {
        return tasktitle;
    }

    public void setTasktitle(String tasktitle) {
        this.tasktitle = tasktitle;
    }

    public String getTaskdescription() {
        return taskdescription;
    }

    public void setTaskdescription(String taskdescription) {
        this.taskdescription = taskdescription;
    }
}
