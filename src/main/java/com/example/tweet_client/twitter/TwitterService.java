package com.example.tweet_client.twitter;

import com.example.tweet_client.config.twitter.TwitterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TwitterService {

    @Autowired
    TwitterConfiguration twitterConfiguration;

    public List<String> getTweets() throws TwitterException {
        Twitter twitter = twitterConfiguration.getInstance();
        return twitter.getHomeTimeline().stream()
                .map(Status::getText)
                .collect(Collectors.toList());
    }
}
