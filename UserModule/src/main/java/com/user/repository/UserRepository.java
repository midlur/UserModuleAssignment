package com.user.repository;

import com.user.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    public Optional<User> findByUserIdAndCreatedOnBetween(String id, Date fromDate, Date toEnd);


}
