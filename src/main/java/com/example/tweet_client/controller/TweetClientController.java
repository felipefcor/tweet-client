package com.example.tweet_client.controller;

import com.example.tweet_client.model.Tweet;
import com.example.tweet_client.service.TweetClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.TwitterException;

import java.util.List;

@RestController
public class TweetClientController {

    @Autowired
    TweetClientServiceInterface tweetClientServiceInterface;

    @GetMapping(value = "/tweets")
    public ResponseEntity<List<Tweet>> findAll() throws TwitterException {
        List<Tweet> listTweets = tweetClientServiceInterface.findAllInSeveralLanguagesAndUserBeyond1500followers();
        return new ResponseEntity<>(listTweets, HttpStatus.OK);
    }
}
