package irawan.electroshock.tungpat.model;

public class UsersScore {

    public String username;
    public String score;
    public String numberOfQuestions;

    public UsersScore(String username, String score, String numberOfQuestions) {
        this.username = username;
        this.score = score;
        this.numberOfQuestions = numberOfQuestions;
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
