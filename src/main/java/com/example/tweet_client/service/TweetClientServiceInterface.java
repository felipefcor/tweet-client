package com.example.tweet_client.service;

import com.example.tweet_client.model.Tweet;
import twitter4j.TwitterException;

import java.util.List;

public interface TweetClientServiceInterface {
    List<Tweet> findAll() throws TwitterException;
}
