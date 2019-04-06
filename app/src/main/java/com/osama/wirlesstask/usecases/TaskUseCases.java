package com.osama.wirlesstask.usecases;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.osama.wirlesstask.entities.Task;
import com.osama.wirlesstask.repositries.TaskRepositry;

import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observer;
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
        //       repositry.getAllTasks().subscribe(new FlowableSubscriber<List<Task>>() {
//           @Override
//           public void onSubscribe(Subscription s) {
//            Log.d("d","fs");
//           }
//
//           @Override
//           public void onNext(List<Task> tasks) {
//            tasksLivedata.postValue(tasks);
//           }
//
//           @Override
//           public void onError(Throwable t) {
//            Log.d("as",t.toString());
//           }
//
//           @Override
//           public void onComplete() {
//               Log.d("as","complete");
//
//           }
//       });
    }

    public static  void AddTask (TaskRepositry repositry ,Task  task)
    {
        repositry.AddTask(task);
    }


}
