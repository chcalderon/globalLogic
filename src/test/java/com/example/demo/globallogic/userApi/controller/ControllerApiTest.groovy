package com.example.demo.globallogic.userApi.controller

import com.example.demo.globallogic.userApi.model.User
import javafx.application.Application
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import spock.lang.Specification

@SpringBootTest
class ControllerApiTest extends Specification {
    @Autowired
    ControllerApi controllerApi

    void setup() {
    }

    void cleanup() {
    }

    def "Login"() {
        given:
        def user = new User()
        when:
        user.setEmail("email@test.com")
        user.setName("Test Name")
        user.setPassword("Abcd12")
        def login = controllerApi.login(user)
        then:
        login.getStatusCode()== HttpStatus.INTERNAL_SERVER_ERROR
    }


}
