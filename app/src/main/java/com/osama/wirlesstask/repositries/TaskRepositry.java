package com.osama.wirlesstask.repositries;

import android.annotation.SuppressLint;
import android.util.Log;

import com.osama.wirlesstask.entities.Task;
import com.osama.wirlesstask.entities.deo.TaskDeo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TaskRepositry {



    private List<Task> tasks = new ArrayList<>();
    public TaskDeo taskDeo;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public TaskRepositry(TaskDeo taskDeo) {
        this.taskDeo = taskDeo;
            }


    public Single<List<Task>> getAllTasks ()
    {
      return  taskDeo.selectAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @SuppressLint("CheckResult")
    public void AddTask(Task task)
    {
       Observable.fromCallable(() ->taskDeo.insertTask(task)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<Long>() {
           @Override
           public void onSubscribe(Disposable d) {
               Log.d("d","fs");
           }

           @Override
           public void onNext(Long aLong) {
               Log.d("d","fs");
           }

           @Override
           public void onError(Throwable e) {
               Log.d("d","fs");
           }

           @Override
           public void onComplete() {
               Log.d("d","fs");
               taskDeo.selectAll();
           }
       });
    }

    public void editTaskStatues(Task task)
    {
        taskDeo.upateProduct(task);
    }

}
