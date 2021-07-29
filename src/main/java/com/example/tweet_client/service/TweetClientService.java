package com.example.tweet_client.service;

import com.example.tweet_client.model.Tweet;
import com.example.tweet_client.repository.TweetClientRepository;
import com.example.tweet_client.twitter.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.TwitterException;

import java.util.List;

@Service
public class TweetClientService implements TweetClientServiceInterface{

    @Autowired
    TweetClientRepository tweetClientRepository;

    @Autowired
    TwitterService twitterService;

    @Override
    public List<Tweet> findAll() throws TwitterException {
        System.out.println(twitterService.getTweets());
        return null;
    }
}
