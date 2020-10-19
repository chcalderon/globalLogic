package com.example.demo.globallogic.userApi.repo;

import com.example.demo.globallogic.userApi.entity.PhoneEntity;
import com.example.demo.globallogic.userApi.entity.UserEntity;
import com.example.demo.globallogic.userApi.model.Phone;
import com.example.demo.globallogic.userApi.model.User;
import com.example.demo.globallogic.userApi.util.JavaUtil;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Repository
public class UserRepository {
    private final EntityUserRepository entityUserRepository;

    public UserRepository(EntityUserRepository entityUserRepository) {
        this.entityUserRepository = entityUserRepository;
    }

    public UserEntity createUser(final User user) {

        UserEntity u = new UserEntity();
        u.setEmail(user.getEmail());
        u.setName(user.getName());
        u.setPassword(user.getPassword());
        u.setCreado(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        u.setLast_login(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        u.setActive(true);
        u.setToken(JavaUtil.getJWTToken(user.getName()));
        List<PhoneEntity> phoneEntities = new ArrayList<>();
        for (Phone phone : user.getPhones()) {
            PhoneEntity phoneEntity = new PhoneEntity();
            phoneEntity.setCitycode(phone.getCitycode());
            phoneEntity.setCountrycode(phone.getCountrycode());
            phoneEntity.setNumber(phone.getNumber());
            phoneEntity.setEntityUser(u);
            phoneEntities.add(phoneEntity);

        }
        u.setPhones(phoneEntities);
            return entityUserRepository.save(u);
    }

    public UserEntity findByName(String nameUser){

       return entityUserRepository.findByName(nameUser);
    }
}
