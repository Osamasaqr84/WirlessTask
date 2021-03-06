package com.osama.wirlesstask.presentation.feture.taskslist.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.osama.wirlesstask.R;
import com.osama.wirlesstask.model.entities.Task;
import com.osama.wirlesstask.presentation.feture.taskslist.MainActivity;

import java.util.List;


public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.CustomView> {

    private FragmentActivity context;
    private  List<Task> taskList;

    public TasksAdapter(FragmentActivity activity, List<Task> tasks) {
        this.context = activity;
        taskList = tasks;
    }


    @Override
    public TasksAdapter.CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);

        return new TasksAdapter.CustomView(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CustomView holder, final int position) {

        holder.title.setText(taskList.get(position).getTasktitle());
        holder.description.setText(taskList.get(position).getTaskdescription());
        holder.isdone.setChecked(taskList.get(position).getIsDone());
            if (!taskList.get(position).getIsDone())
            MainActivity.undonetasks +=1;

        holder.isdone.setOnCheckedChangeListener((buttonView, isChecked) -> {
            taskList.get(position).setIsDone(isChecked);
            //notifyDataSetChanged();
            if (!isChecked)
                MainActivity.undonetasks +=1;
            else
                MainActivity.undonetasks -=1;

            ((MainActivity)context).changeStatues(taskList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class CustomView extends RecyclerView.ViewHolder {

        private final View mView;
        private TextView title,description;
        private CheckBox isdone;
        private CustomView(View view) {
            super(view);
            mView = view;
            title = mView.findViewById(R.id.title);
            description = mView.findViewById(R.id.description);
            isdone = mView.findViewById(R.id.check);
        }
    }

}
