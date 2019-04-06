package com.osama.wirlesstask.presentation.feture.taskslist;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.osama.wirlesstask.R;
import com.osama.wirlesstask.entities.Task;
import com.osama.wirlesstask.presentation.feture.addtask.AddTaskActivity;
import com.osama.wirlesstask.presentation.feture.taskslist.adapter.TasksAdapter;
import com.osama.wirlesstask.presentation.viewModel.TaskViewModel;
import com.osama.wirlesstask.presentation.viewModel.ViewModelFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView tasksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tasksList = findViewById(R.id.taskslist);
        TaskViewModel taskViewModel = ViewModelProviders.of(this, getViewModelFactory(this.getApplication())).get(TaskViewModel.class);
        taskViewModel.getAllTasks();
        taskViewModel.tasks.observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                tasksList.setAdapter(new TasksAdapter(MainActivity.this,tasks));
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MainActivity.this, AddTaskActivity.class));
            }
        });
    }

    ////// return instance from viewmodelfactory
    private ViewModelFactory getViewModelFactory(Application application)
    {
        return new ViewModelFactory(application);
    }


}
