package com.login.SpringBootLogin.repository;

import com.login.SpringBootLogin.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser,Integer> {


    // find User By Email
    Optional<ApplicationUser> findByEmail(String email);


}
