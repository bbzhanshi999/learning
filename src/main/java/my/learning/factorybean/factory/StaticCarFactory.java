package my.learning.factorybean.factory;

import my.learning.factorybean.entity.Car;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/18 0018.
 */
public class StaticCarFactory {

    private static Map<String,Car> cars = new HashMap();

    static{
        cars.put("audi",new Car("audi",10000.00));
        cars.put("benz",new Car("benz",20000.00));
        cars.put("bmw",new Car("bmw",30000.00));
    }

    public static Car getCar(String brand){
        return cars.get(brand);
    }
}
