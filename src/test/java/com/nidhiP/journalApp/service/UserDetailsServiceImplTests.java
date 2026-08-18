package com.nidhiP.journalApp.service;

import com.nidhiP.journalApp.entity.User;
import com.nidhiP.journalApp.repository.UserRepo;
import com.nidhiP.journalApp.services.UserDetailsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepo userRepo;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){
        when(userRepo.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("nidhi").password("shsh").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("nidhi");

        Assertions.assertNotNull(user);
    }
}
