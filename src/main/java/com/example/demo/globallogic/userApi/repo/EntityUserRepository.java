package com.example.demo.globallogic.userApi.repo;

import java.util.UUID;

import com.example.demo.globallogic.userApi.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface EntityUserRepository extends CrudRepository<UserEntity, UUID> {
    UserEntity findByName(String name);
}
