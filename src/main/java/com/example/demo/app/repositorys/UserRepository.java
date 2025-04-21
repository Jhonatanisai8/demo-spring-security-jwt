package com.example.demo.app.repositorys;

import com.example.demo.app.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);
//    @Query("SELECT u FROM UserEntity u WHERE u.userName = ?1")
//    Optional<UserEntity> getUserName(String email);
}
