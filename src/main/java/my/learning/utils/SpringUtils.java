package my.learning.utils;


import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/3/18 0018.
 */
public class SpringUtils {

    public static ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationsContext.xml");

    public static Object getBean(String beanName){
        return classPathXmlApplicationContext.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> requiredType){
        return classPathXmlApplicationContext.getBean(beanName,requiredType);
    }

}
