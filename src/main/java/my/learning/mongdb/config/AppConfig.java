package my.learning.mongdb.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

import java.net.UnknownHostException;

/**
 * Created by Administrator on 2016/3/18 0018.
 */
@Configuration
public class AppConfig {
    /**
     * 通过普通bean注入的方式获得mongoClient,增加了auth，通过传入MongoClientURI的方式
     * @return
     * @throws UnknownHostException
     */
    public @Bean(name="client")
    MongoClient mongoClient() throws UnknownHostException {
        return new MongoClient(new MongoClientURI("mongodb://root:1234@127.0.0.1:27017/?authSource=admin"));

    }


    /**
     *
     * 通过factoryBean的方式获得mongclient
     * @return
     */
    public @Bean(name="mongo")
    MongoClientFactoryBean mongo() {
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        mongo.setPort(27017);
        mongo.setHost("localhost");
        return mongo;
    }
}
