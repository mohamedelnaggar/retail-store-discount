package com.retail.store.discount.repository;

import com.retail.store.discount.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findOneById(Long id);

}
