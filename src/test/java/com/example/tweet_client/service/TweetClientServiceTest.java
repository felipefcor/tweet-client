package com.example.tweet_client.service;


import com.example.tweet_client.common.StatusClass;
import com.example.tweet_client.model.Tweet;
import com.example.tweet_client.repository.TweetClientRepository;
import com.example.tweet_client.twitter.TwitterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.List;

class TweetClientServiceTest {

    @Mock
    TweetClientRepository tweetClientRepository;
    @Mock
    TwitterService twitterService;

    @InjectMocks
    TweetClientService tweetClientService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllInSeveralLanguagesAndUserBeyond1500followersCorrectly() throws TwitterException {
        Status status = StatusClass.statusClassWithMoreThan1500Followers();
        List<Status> timeline = new ArrayList<>();
        timeline.add(status);

        Tweet tweet = new Tweet();
        tweet.setUser("test");

        Mockito.when(twitterService.getTimeLine()).thenReturn(timeline);

        List<Tweet> timelineResult = tweetClientService.findAllInSeveralLanguagesAndUserBeyond1500followers();

        ArgumentCaptor<Tweet> captorQuery = ArgumentCaptor.forClass(Tweet.class);

        Mockito.verify(tweetClientRepository, Mockito.times(1))
                .save(captorQuery.capture());

        Tweet queryCaptured = captorQuery.getValue();

        Assertions.assertEquals(tweet.getUser(), queryCaptured.getUser());
        Assertions.assertEquals(1, timelineResult.size());
    }

    @Test
    void findAllInSeveralLanguagesAndUserLess1500followersNotPassingFilters() throws TwitterException {
        Status status = StatusClass.statusClassWithLessThan1500Followers();
        List<Status> timeline = new ArrayList<>();
        timeline.add(status);

        Mockito.when(twitterService.getTimeLine()).thenReturn(timeline);

        List<Tweet> timelineResult = tweetClientService.findAllInSeveralLanguagesAndUserBeyond1500followers();

        Assertions.assertEquals(0, timelineResult.size());
    }
}