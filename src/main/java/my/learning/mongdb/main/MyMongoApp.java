package my.learning.mongdb.main;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import my.learning.factorybean.entity.Car;
import my.learning.mongdb.entity.Persons;
import my.learning.mongdb.service.MongoService;
import my.learning.utils.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.util.Arrays;

import static com.mongodb.client.model.Filters.*;

public class MyMongoApp {

    public static Log log = LogFactory.getLog(MyMongoApp.class);
    public static MongoDatabase mydb;
    public static Mongo mongo;


    /**
     * 测试mongoDbFactory的使用
     */
    @Test
    public void SimpleMongoDbFactoryTest(){

        MongoService mongoService = SpringUtils.getBean("mongoService", MongoService.class);
        MongoClient mongoClient = mongoService.getClient();
        MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(mongoClient,"mydb"));
        //Persons person1 = mongoOps.findOne(new Query(Criteria.where("name").is("zhangsan")), Persons.class);
        Persons person =
                mongoOps.findOne(new Query(Criteria.where("name").is("zhangsan")),
                                                        Persons.class,"persons");//指定表名
        mongoOps.insert(new Persons("user",25));/*如不指定对应collection，用类名小写对应表名*/
        mongoOps.insert(new Persons("user",25),"persons");/*插入时直接对应表名*/
        System.out.println(person);

    }



    @Test
    public void autoWiredTest(){

        MongoService mongoService = SpringUtils.getBean("mongoService", MongoService.class);
        MongoClient mongoClient = mongoService.getClient();
        System.out.println(mongoClient.getDatabase("mydb").getCollection("persons").find().first().toJson());
    }


    @Test
    public void mongofactoryBeanTest(){
        mongo = SpringUtils.getBean("mongo", Mongo.class);
        System.out.println(mongo.getClass());
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