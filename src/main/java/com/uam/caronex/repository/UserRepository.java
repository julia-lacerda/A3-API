package com.uam.caronex.repository;

import com.uam.caronex.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final MongoTemplate mongoTemplate;
    private final MongoOperations mongoOperations;


    public void checkAccess(String email, String password){
        Query query = new Query();
        query.addCriteria(Criteria.where("account.email").is(email).and("account.password").is(password));

        mongoTemplate.exists(query, UserEntity.class);
    }

    public UserEntity findUserById(String cpf) {
        Query query = new Query();
        query.addCriteria(Criteria.where("cpf").is(cpf));

        return mongoTemplate.findOne(query, UserEntity.class);
    }

    public UserEntity create(UserEntity user) {
        return mongoTemplate.save(user, "users");
    }

    public UserEntity update(UserEntity user) {
        return mongoTemplate.save(user, "users");
    }

    public void deleteById(String cpf) {
        Query query = new Query();
        query.addCriteria(Criteria.where("cpf").is(cpf));

        mongoTemplate.remove(query, UserEntity.class);
    }
}
