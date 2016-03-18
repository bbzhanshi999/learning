package my.learning.generic.dao;

import my.learning.generic.entity.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by Administrator on 2016/3/18 0018.
 */
@Repository
public class UserDao implements BaseDao<User> {


    public User find(User entity) {
        return entity;
    }

    public void save(User entity) {
        System.out.println("successful saved");
    }
}
