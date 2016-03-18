package my.learning.generic.entity;

/**
 * Created by Administrator on 2016/3/18 0018.
 */
public class BaseEntity {

    public BaseEntity(String id) {
        this.id = id;
    }

    public BaseEntity(){};

    private String id;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
