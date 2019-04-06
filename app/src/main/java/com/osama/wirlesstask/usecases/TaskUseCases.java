package com.osama.wirlesstask.usecases;

import android.arch.lifecycle.MutableLiveData;

import com.osama.wirlesstask.model.entities.Task;
import com.osama.wirlesstask.repositries.TaskRepositry;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class TaskUseCases {

    public static  void retrieveAllTasks (TaskRepositry repositry, MutableLiveData<List<Task>> tasksLivedata)
    {
        repositry.getAllTasks().subscribe(new SingleObserver<List<Task>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(List<Task> tasks) {
            tasksLivedata.postValue(tasks);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public static  void AddTask(TaskRepositry repositry, Task task, MutableLiveData<String> validate, MutableLiveData<Boolean> addStatues)
    {
        if (validateInput(task,validate)) {
            repositry.AddTask(task);
            addStatues.postValue(true);
        }
        else
            addStatues.postValue(false);
    }

    private static boolean validateInput(Task task, MutableLiveData<String> validate)
    {
        if (task.getTasktitle().matches("")||task.getTaskdescription().matches(""))
        {
            validate.postValue("please complete data fielda");
            return false;
        }
        else
            return true;
    }
    public static  void updateTaskStatues (TaskRepositry repositry ,Task  task)
    {
        repositry.editTaskStatues(task);
    }

}
