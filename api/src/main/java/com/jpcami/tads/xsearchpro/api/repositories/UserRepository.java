package com.jpcami.tads.xsearchpro.api.repositories;

import com.jpcami.tads.xsearchpro.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginAndPassword(String login, String password);
}
