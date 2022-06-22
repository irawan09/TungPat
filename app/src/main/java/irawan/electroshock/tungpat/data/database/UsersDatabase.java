package irawan.electroshock.tungpat.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import irawan.electroshock.tungpat.data.model.UsersScore;

@Database(entities = {UsersScore.class}, version = 1, exportSchema = false)
public abstract class UsersDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
