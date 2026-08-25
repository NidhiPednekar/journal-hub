package com.nidhiP.journalApp.services;

import com.nidhiP.journalApp.entity.JournalEntry;
import com.nidhiP.journalApp.entity.User;
import com.nidhiP.journalApp.repository.JournalEntryRepo;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    @Transactional
    @CacheEvict(value = "journals", key = "#userName")
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());

            JournalEntry saved = journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);

            userService.saveUser(user);

        } catch (Exception e) {
            log.error("Error occurred while saving journal entry", e);
            throw new RuntimeException(
                    "An error occurred while saving the entry",
                    e
            );
        }
    }

    @CacheEvict(value = "journals", key = "#userName")
    public void updateEntry(JournalEntry journalEntry, String userName) {
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepo.findById(id);
    }

    @Transactional
    @CacheEvict(value = "journals", key = "#userName")
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries()
                    .removeIf(x -> x.getId().equals(id));

            if (removed) {
                userService.saveUser(user);
                journalEntryRepo.deleteById(id);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting journal entry", e);
            throw new RuntimeException(
                    "Error occurred while deleting", e
            );
        }
        return removed;
    }

    public List<JournalEntry> findByUserName(String userName) {
        User user = userService.findByUserName(userName);
        return user.getJournalEntries();
    }

    @Cacheable(value = "journals", key = "#userName")
    public List<JournalEntry> getJournalsByUser(String userName) {
        System.out.println("Fetching from MongoDB...");
        User user = userService.findByUserName(userName);

        if (user == null) {
            return Collections.emptyList();
        }

        return user.getJournalEntries();
    }
}