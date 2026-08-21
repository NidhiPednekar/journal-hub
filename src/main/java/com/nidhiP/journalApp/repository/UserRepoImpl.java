package com.nidhiP.journalApp.repository;
import com.nidhiP.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class UserRepoImpl {

    @Autowired
    public MongoTemplate mongoTemplate;


    public List<User> getUserForSA(){
        Query q = new Query();
        q.addCriteria(Criteria.where("email")
                        .regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
                        .and("sentimentAnalysis").is(true));

       List<User> users =  mongoTemplate.find(q, User.class);
       return users;
    }
}
