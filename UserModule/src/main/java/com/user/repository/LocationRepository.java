package com.user.repository;

import com.user.model.Location;
import com.user.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

    public List<Location> findAllByUser(User user);
}
