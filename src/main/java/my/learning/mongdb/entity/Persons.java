package my.learning.mongdb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/3/18 0018.
 */
@Document(collection = "persons")
public class Persons {

    public Persons(String name, int age, String[] books, List<School> school) {
        this.name = name;
        this.age = age;
        this.books = books;
        this.school = school;
    }

    public Persons(){};

    @Id
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

    public void setId(String id) {
        this.id = id;
    }
}