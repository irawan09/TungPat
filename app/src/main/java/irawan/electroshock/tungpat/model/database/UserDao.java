package irawan.electroshock.tungpat.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import irawan.electroshock.tungpat.model.UsersScore;

@Dao
public interface UserDao {

    @Query("SELECT * FROM tb_user")
    public List<UsersScore> getAllUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUser(UsersScore scoreEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAllUsers(UsersScore... scoreEntities);

    @Update
    public void updateUser(UsersScore scoreEntity);

    @Update
    public void updateAllUsers(UsersScore... scoreEntities);

    @Query("DELETE FROM tb_user WHERE user_id = :id")
    public void deleteUser(int id);

    @Delete
    void deleteAllUsers(UsersScore... scoreEntities);
}
