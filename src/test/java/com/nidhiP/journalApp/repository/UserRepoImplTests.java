package com.nidhiP.journalApp.repository;

import com.nidhiP.journalApp.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepoImplTests {

    @Autowired
    private UserRepoImpl userRepo;

    @Test
     public void testSaveNewUser(){
    Assertions.assertNotNull(userRepo.getUserForSA());
    }

}
