package com.nidhiP.journalApp.services;

import com.nidhiP.journalApp.entity.User;
import com.nidhiP.journalApp.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailService emailService;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean saveNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepo.save(user);

            if (user.getEmail() != null && !user.getEmail().isBlank()) {
                emailService.sendEmail(
                        user.getEmail(),
                        "Welcome to Journal-Hub!!",
                        "Hi " + user.getUserName() + ",\n\n" +
                                "Welcome aboard! Your account has been created successfully.\n" +
                                "Start journaling your thoughts today.\n\n" +
                                "Cheers,\nJournal-Hub Team"
                );
            } else {
                log.warn("No email provided for user {}, skipping welcome mail", user.getUserName());
            }

            return true;
        } catch (Exception e) {
            log.error("Error occurred for {} : ", user.getUserName(), e);
            return false;
        }

    }

    public void saveUser(User user){
        userRepo.save(user);
    }

    public void updateUser(User existingUser, User incoming){
        existingUser.setUserName(incoming.getUserName());
        if (incoming.getPassword() != null && !incoming.getPassword().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(incoming.getPassword()));
        }
        userRepo.save(existingUser);
    }

    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepo.save(user);
    }

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepo.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepo.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepo.findByUserName(userName);
    }

}
