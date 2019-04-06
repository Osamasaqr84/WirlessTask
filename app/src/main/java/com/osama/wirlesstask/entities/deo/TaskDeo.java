package com.osama.wirlesstask.entities.deo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.osama.wirlesstask.entities.Task;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface TaskDeo {

    @Insert(onConflict = REPLACE)
    public Long insertTask(Task productDeo);

    @Update
    public void upateProduct(Task task);

    @Delete
    public void deleteProduct(Task task);

    @Query("select * from Task")
    public Single<List<Task>> selectAll();

}
