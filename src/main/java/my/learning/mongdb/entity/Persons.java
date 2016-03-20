package my.learning.mongdb.entity;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/3/18 0018.
 */
public class Persons {




    private String id;
    private String name;
    private int age;
    private String[] books;
    private List<School> school;



    public Persons(String name, int age) {
        this.name = name;
        this.age = age;
    }



    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", books=" + Arrays.toString(books) +
                ", school=" + school +
                '}';
    }

    public String[] getBooks() {
        return books;
    }

    public void setBooks(String[] books) {
        this.books = books;
    }

    public List<School> getSchool() {
        return school;
    }

    public void setSchool(List<School> school) {
        this.school = school;
    }
}