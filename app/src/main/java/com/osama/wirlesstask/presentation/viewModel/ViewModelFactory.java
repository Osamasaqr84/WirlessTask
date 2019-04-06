package com.osama.wirlesstask.presentation.viewModel;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.osama.wirlesstask.entities.LocalDatabase;
import com.osama.wirlesstask.entities.deo.TaskDeo;
import com.osama.wirlesstask.repositries.TaskRepositry;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Application application;

    public ViewModelFactory(Application application1) {
        application = application1;
    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends android.arch.lifecycle.ViewModel> T create(@NonNull Class<T> modelClass) {
         if (modelClass == TaskViewModel.class)
        {
            return (T) new TaskViewModel(getTaskRepositry());
        }
        throw new IllegalArgumentException("Invalid view model class type");
    }

    private TaskRepositry getTaskRepositry() {
        return new TaskRepositry(getTaskDeo());
    }
    private TaskDeo getTaskDeo() {
        return LocalDatabase.getInstance(application).taskDeo();
    }


}
