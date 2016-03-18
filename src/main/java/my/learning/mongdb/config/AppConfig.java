package my.learning.mongdb.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

import java.net.UnknownHostException;

/**
 * Created by Administrator on 2016/3/18 0018.
 */
//@Configuration
public class AppConfig {
    /*
   * Use the standard Mongo driver API to create a com.mongodb.Mongo instance.
   */
  /*  public @Bean(name="client")
    MongoClient mongoClient() throws UnknownHostException {
        return new MongoClient("localhost",27017);
    }


    public @Bean(name="dbFactory")
    MongoClientFactoryBean mongo() {
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        mongo.setPort(27017);
        mongo.setHost("localhost");
        return mongo;
    }*/
}
