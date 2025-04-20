package com.example.demo.app.repositorys;

import com.example.demo.app.models.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository
        extends JpaRepository<RoleEntity, Long> {
}
