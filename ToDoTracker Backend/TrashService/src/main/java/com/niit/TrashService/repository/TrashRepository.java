package com.niit.TrashService.repository;

import com.niit.TrashService.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrashRepository extends MongoRepository <User,String> {

    List findByEmail(String email);
}
