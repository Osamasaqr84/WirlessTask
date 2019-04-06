package com.osama.wirlesstask.presentation.feture.taskslist;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.osama.wirlesstask.R;
import com.osama.wirlesstask.model.entities.Task;
import com.osama.wirlesstask.presentation.feture.addtask.AddTaskActivity;
import com.osama.wirlesstask.presentation.feture.taskslist.adapter.TasksAdapter;
import com.osama.wirlesstask.presentation.viewModel.TaskViewModel;
import com.osama.wirlesstask.presentation.viewModel.ViewModelFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView tasksList;
    private TaskViewModel taskViewModel;
    public static int undonetasks;
    private TextView notfound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tasksList = findViewById(R.id.taskslist);
        notfound = findViewById(R.id.notfound);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, AddTaskActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        taskViewModel = ViewModelProviders.of(this, getViewModelFactory(this.getApplication())).get(TaskViewModel.class);
        taskViewModel.getAllTasks();
        taskViewModel.tasks.observe(this, tasks -> {
            undonetasks = 0;
            if (tasks.size() > 0) {
                tasksList.setAdapter(new TasksAdapter(MainActivity.this, tasks));
                notfound.setVisibility(View.GONE);
            } else
                notfound.setVisibility(View.VISIBLE);
        });
    }

    public void changeStatues(Task task) {
        taskViewModel.editTask(task);
    }

    ////// return instance from viewmodelfactory
    private ViewModelFactory getViewModelFactory(Application application) {
        return new ViewModelFactory(application);
    }

    @Override
    public void onBackPressed() {
        if (undonetasks > 0) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1")
                    .setSmallIcon(R.drawable.wir)
                    .setContentTitle("UnDone Tasks ")
                    .setContentText("You have  " + undonetasks + " " + "undone tasks")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(1, builder.build());
        }
        super.onBackPressed();
    }
}
