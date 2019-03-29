package com.retail.store.discount.repository;

import com.retail.store.discount.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Collection<User> findAll();

    User findOneById(Long id);

}
