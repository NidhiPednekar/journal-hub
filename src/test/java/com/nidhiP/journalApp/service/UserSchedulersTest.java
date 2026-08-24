package com.nidhiP.journalApp.service;

import com.nidhiP.journalApp.entity.JournalEntry;
import com.nidhiP.journalApp.entity.User;
import com.nidhiP.journalApp.enums.Sentiment;
import com.nidhiP.journalApp.repository.UserRepoImpl;
import com.nidhiP.journalApp.scheduler.UserScheduler;
import com.nidhiP.journalApp.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserSchedulersTest {

    @Mock
    private EmailService emailService;

    @Mock
    private UserRepoImpl userRepo;

    @InjectMocks
    private UserScheduler userScheduler;

    @Test
    void testFetchUsersAndSendSaMail_sendsEmailForMostFrequentSentiment() {
        JournalEntry entry1 = new JournalEntry();
        entry1.setDate(LocalDateTime.now().minusDays(1));
        entry1.setSentiment(Sentiment.HAPPY);

        JournalEntry entry2 = new JournalEntry();
        entry2.setDate(LocalDateTime.now().minusDays(2));
        entry2.setSentiment(Sentiment.HAPPY);

        User user = new User();
        user.setEmail("test@example.com");
        user.setJournalEntries(List.of(entry1, entry2));

        when(userRepo.getUserForSA()).thenReturn(List.of(user));

        userScheduler.fetchUsersAndSendSaMail();

        verify(emailService, times(1))
                .sendEmail(eq("test@example.com"), eq("Sentiment for last 7 days"), eq("HAPPY"));
    }

    @Test
    void testFetchUsersAndSendSaMail_noEmailWhenNoSentiments() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setJournalEntries(List.of());

        when(userRepo.getUserForSA()).thenReturn(List.of(user));

        userScheduler.fetchUsersAndSendSaMail();

        verify(emailService, never()).sendEmail(anyString(), anyString(), anyString());
    }

}
