package my.learning.factorybean.factory;

import my.learning.factorybean.entity.Car;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/3/18 0018.
 */
@Service
public class CarFactoryBean implements FactoryBean<Car> {

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Value("${brand}")
    private String brand;

    @Value("${price}")
    private Double price;


    public Car getObject() throws Exception {
        return new Car(brand,price);
    }

    public Class<?> getObjectType() {
        return Car.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
