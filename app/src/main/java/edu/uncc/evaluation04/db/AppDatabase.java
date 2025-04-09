package edu.uncc.evaluation04.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import edu.uncc.evaluation04.models.Grade;


@Database(entities = {Grade.class},version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract GradeDAO gradeDAO();


}
