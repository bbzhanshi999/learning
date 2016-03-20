package my.learning.mongdb.entity;

/**
 * Created by Administrator on 2016/3/20 0020.
 */
public class School {

    private String school;
    private String score;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "School{" +
                "school='" + school + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
