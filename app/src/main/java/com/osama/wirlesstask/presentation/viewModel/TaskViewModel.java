package com.osama.wirlesstask.presentation.viewModel;

import android.arch.lifecycle.MutableLiveData;
import com.osama.wirlesstask.entities.Task;
import com.osama.wirlesstask.entities.deo.TaskDeo;
import com.osama.wirlesstask.repositries.TaskRepositry;
import com.osama.wirlesstask.usecases.TaskUseCases;

import java.util.List;


public class TaskViewModel extends android.arch.lifecycle.ViewModel {

    public MutableLiveData<List<Task>> tasks = new MutableLiveData<>();
    public MutableLiveData<Throwable> throwableMutableLiveData = new MutableLiveData<>();
    private TaskDeo taskDeo;
    private TaskRepositry repositry;

    public TaskViewModel( TaskRepositry taskRepositry) {
        repositry = taskRepositry;
    }

    public void getAllTasks ()
    {
        TaskUseCases.retrieveAllTasks(repositry,tasks);
    }


    public  void  addTask (Task task)
    {
        TaskUseCases.AddTask(repositry,task);
    }

}
