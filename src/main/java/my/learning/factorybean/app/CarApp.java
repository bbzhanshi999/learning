package my.learning.factorybean.app;

import my.learning.factorybean.entity.Car;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/3/18 0018.
 */
public class CarApp {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app-factoryBean.xml");
        Car car1 = context.getBean("car1", Car.class);
        Car car2 = context.getBean("car2", Car.class);
        Car car3 = context.getBean("car3", Car.class);
        System.out.println(car1);
        System.out.println(car2);
        System.out.println(car3);
    }
}
