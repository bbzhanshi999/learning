package my.learning.generic.main;

import my.learning.generic.entity.User;
import my.learning.generic.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;

/**
 * Created by Administrator on 2016/3/18 0018.
 */
public class app {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app-generic.xml");
        UserService userSerive = context.getBean("userService", UserService.class);
        userSerive.save(new User(UUID.randomUUID().toString(),"wori",12));
        userSerive.find(new User());
    }

}
