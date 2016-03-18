package my.learning.mongdb.service;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/3/18 0018.
 */
@Component
public class MongoService {



    private MongoClient client;

    public MongoClient getClient() {
        return client;
    }

    @Resource(name="client")
    public void setClient(MongoClient client) {
        this.client = client;
    }
}
