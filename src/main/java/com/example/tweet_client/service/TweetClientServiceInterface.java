package com.example.tweet_client.service;

import com.example.tweet_client.model.Tweet;
import twitter4j.TwitterException;

import java.util.List;

public interface TweetClientServiceInterface {
    List<Tweet> findAllInSeveralLanguagesAndUserBeyond1500followers() throws TwitterException;
    List<Tweet> findAll() throws TwitterException;
    Tweet findById(Long id);
    Tweet validateById(Long id);
    List<Tweet> findAllValidate();
    List<String> findTrends() throws TwitterException;
}
