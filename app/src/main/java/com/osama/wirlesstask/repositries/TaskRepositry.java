package com.osama.wirlesstask.repositries;

import android.annotation.SuppressLint;
import android.util.Log;

import com.osama.wirlesstask.model.entities.Task;
import com.osama.wirlesstask.model.lacaldatabase.deo.TaskDeo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TaskRepositry {

    private TaskDeo taskDeo;

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
           }

           @Override
           public void onNext(Long aLong) {
           }

           @Override
           public void onError(Throwable e) {
               Log.d("d","fs");
           }

           @Override
           public void onComplete() {
           }
       });
    }

    @SuppressLint("CheckResult")
    public void editTaskStatues(Task task)
    {
        Observable.fromCallable(() ->taskDeo.upateProduct(task)).subscribeOn(Schedulers.io()).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Integer integer) {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
             }
        });
    }

}
