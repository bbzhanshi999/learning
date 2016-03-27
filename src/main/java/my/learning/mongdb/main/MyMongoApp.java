package my.learning.mongdb.main;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import my.learning.factorybean.entity.Car;
import my.learning.mongdb.entity.Persons;
import my.learning.mongdb.entity.School;
import my.learning.mongdb.service.MongoService;
import my.learning.utils.SpringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.junit.Test;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


import java.util.Arrays;
import java.util.List;

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
        /*通过代码组装工厂，而mongoclient为注入*/
        MongoService mongoService = SpringUtils.getBean("mongoService", MongoService.class);
        MongoClient mongoClient = mongoService.getClient();
        MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(mongoClient,"mydb"));


        /*直接配置工厂的方式*/
        /*MongoDbFactory dbFactory = SpringUtils.getBean("dbFactory", MongoDbFactory.class);
        MongoOperations mongoOps = new MongoTemplate(dbFactory);*/

        //Persons person1 = mongoOps.findOne(new Query(Criteria.where("name").is("zhangsan")), Persons.class);
        Persons person =
                mongoOps.findOne(new Query(Criteria.where("name").is("shabi")),
                                                        Persons.class,"persons");//指定表名
     /*   mongoOps.insert(new Persons("user",25));*//*如不指定对应collection，用类名小写对应表名*//*
        mongoOps.insert(new Persons("user",25),"persons");*//*插入时直接对应表名*/
        System.out.println(person);

    }

    /**
     * 利用springTemplate来实现orm式的更新操作
     */
    @Test
    public  void updateByTemplate(){
        MongoDbFactory dbFactory = SpringUtils.getBean("dbFactory", MongoDbFactory.class);
        MongoOperations mongoOps = new MongoTemplate(dbFactory);
        Persons person =
                mongoOps.findOne(new Query(Criteria.where("name").is("shabi")),
                        Persons.class,"persons");//指定表名
        person.getSchool().get(0).setSchool("caocaocao");
        mongoOps.save(person);
        WriteResult writeResult = mongoOps.updateFirst(new Query(Criteria.where("school.school").is("nima")), new Update().set("school.$.score", 55), "persons");
        System.out.println(writeResult);
    }

    /**
     * 以orm的方式更新对象,如果有相同id，就更新，没有，就插入
     */
    @Test
    public void saveByTemplate(){
        MongoDbFactory dbFactory = SpringUtils.getBean("dbFactory", MongoDbFactory.class);
        MongoOperations mongoOps = new MongoTemplate(dbFactory);
        Persons person =
                mongoOps.findOne(new Query(Criteria.where("name").is("shabi")),
                        Persons.class,"persons");//指定表名
        person.getSchool().get(0).setSchool("caocaocao");
        mongoOps.save(person);
    }

    /**
     * 利用springTemplate来实现orm式的插入操作
      */
    @Test
    public void insertByTemplate(){
        MongoDbFactory dbFactory = SpringUtils.getBean("dbFactory", MongoDbFactory.class);
        MongoOperations mongoOps = new MongoTemplate(dbFactory);
        School s1 = new School("北大","12");
        School s2 = new School("清华","13");
        School s3 = new School("南大","14");
        School s4 = new School("家里蹲","15");
        List<School> schools = Arrays.asList(s1, s2, s3, s4);
        Persons p = new Persons("shabi",23,new String[]{"js","java","mongo"},schools);

        mongoOps.insert(p,"persons");
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