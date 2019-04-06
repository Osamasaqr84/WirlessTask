package com.osama.wirlesstask.presentation.feture.addtask;

import android.app.Application;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.osama.wirlesstask.R;
import com.osama.wirlesstask.model.entities.Task;
import com.osama.wirlesstask.presentation.viewModel.TaskViewModel;
import com.osama.wirlesstask.presentation.viewModel.ViewModelFactory;

public class AddTaskActivity extends AppCompatActivity {

    Task task;
    EditText title, description;
    TaskViewModel taskViewModel;
    Boolean isDone = false;//////by default insert task not done

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        taskViewModel = ViewModelProviders.of(this, getViewModelFactory(this.getApplication())).get(TaskViewModel.class);
        taskViewModel.validate.observe(this, s -> Toast.makeText(AddTaskActivity.this, s, Toast.LENGTH_SHORT).show());
        taskViewModel.addStatues.observe(this, sBoolean ->
        {
            if (sBoolean) {
                Toast.makeText(AddTaskActivity.this, "You add Task Successfully", Toast.LENGTH_SHORT).show();
                title.setText("");
                description.setText("");
            }
        });
    }

    public void addTask(View view) {
        taskViewModel.addTask(new Task(title.getText().toString(), description.getText().toString(), isDone));

    }

    ////// return instance from viewmodelfactory
    private ViewModelFactory getViewModelFactory(Application application) {
        return new ViewModelFactory(application);
    }
}
