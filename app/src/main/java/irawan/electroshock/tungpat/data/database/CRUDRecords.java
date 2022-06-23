package irawan.electroshock.tungpat.data.database;

import android.content.Context;

import androidx.room.Room;

import irawan.electroshock.tungpat.data.model.UsersScore;
import irawan.electroshock.tungpat.data.utils.Executor;

public class CRUDRecords {

    private final UserDao userDao;

    public CRUDRecords(Context context) {
        String DB_NAME = "user_score.db";
        UsersDatabase usersDatabase = Room.databaseBuilder(context, UsersDatabase.class, DB_NAME)
                .fallbackToDestructiveMigration()
                .build();

        this.userDao = usersDatabase.userDao();

    }

    public void insertUser(UsersScore usersScore){
        Executor.IOThread(() -> userDao.insertUser(usersScore));
    }

    public void updateUser(UsersScore usersScore){
        Executor.IOThread(() -> userDao.updateUser(usersScore));
    }

    public void deleteUser(UsersScore usersScore){
        int id = usersScore.getIdUser();
        Executor.IOThread(() -> userDao.deleteUser(id));
    }
}
