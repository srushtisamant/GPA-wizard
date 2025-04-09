package edu.uncc.evaluation04.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.uncc.evaluation04.models.Grade;

@Dao
public interface GradeDAO {

    @Query("Select * from grade")
    List<Grade> getAll();

    @Update
    void update(Grade grade);

    @Query("DELETE FROM grade")
    void deleteAll();

    @Insert
    void insertAll(Grade... grades);

    @Delete
    void delete(Grade... grades);
}
