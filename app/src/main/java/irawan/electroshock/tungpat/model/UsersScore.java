package irawan.electroshock.tungpat.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_user")
public class UsersScore {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="user_id")
    public int idUser;
    @ColumnInfo(name="user_name")
    public String username;
    @ColumnInfo(name="user_score")
    public String score;
    @ColumnInfo(name="user_numberOfQuestions")
    public String numberOfQuestions;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(String numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

}
