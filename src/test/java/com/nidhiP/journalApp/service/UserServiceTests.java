package com.nidhiP.journalApp.service;

import com.nidhiP.journalApp.entity.User;
import com.nidhiP.journalApp.repository.UserRepo;
import com.nidhiP.journalApp.services.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.CsvSources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

//    @ParameterizedTest
//    @ArgumentsSource(UserArgumentsProvider.class)
//    public void testSaveNewUser(User u){
//        assertTrue(userService.saveNewUser(u));
//    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,2,3",
            "2,4,6"
    })
    public void add(int a, int b, int exp){
        assertEquals(exp, a+b);
    }
}
