package my.learning.mongdb.main;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import my.learning.mongdb.service.MongoService;
import my.learning.mongdb.utils.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

import static com.mongodb.client.model.Filters.*;

public class MyMongoApp {

    public static Log log = LogFactory.getLog(MyMongoApp.class);
    public static  MongoDatabase mydb;


    @Test
    public void autoWiredTest(){

        MongoService mongoService = SpringUtils.getBean("mongoService", MongoService.class);
        MongoClient mongoClient = mongoService.getClient();
        System.out.println(mongoClient.getDatabase("mydb").getCollection("persons").find().first().toJson());
    }

    @Test
    public void find(){
        FindIterable<Document> documents = mydb.getCollection("persons").find().projection(Projections.fields(Projections.include("name","job"),Projections.excludeId())).limit(3);
        MongoCursor<Document> iterator = documents.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toJson());
        }

        //Document doc = mydb.getCollection("persons").find(and(eq("name","zhangsan"), eq("job","javaEngineer"))).first();
        Document doc = mydb.getCollection("persons").find(regex("job","java+")).projection(Projections.include("job")).first();
        System.out.println(doc.toJson());

    }

    @Test
    public void update() {


        MongoCollection<Document> persons = mydb.getCollection("persons");

        persons.updateOne(new Document("name", "zhangsan"), new Document("$set", new Document("job", "javaEngineer")), new UpdateOptions().upsert(true));

    }

    @Test
    public void insert(){
        mydb.getCollection("persons").insertOne(new Document(new Document("name","mongoJava").append("job", Arrays.asList("java","mongoDb"))
                                                .append("city","NanJing").append("province","JiangSu").append("country","china")
                                                .append("school",new Document("highSchool","南化一中").append("college","huaiyingshabidaxue"))));
    }

}