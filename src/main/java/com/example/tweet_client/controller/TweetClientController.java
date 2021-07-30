package com.example.tweet_client.controller;

import com.example.tweet_client.model.Tweet;
import com.example.tweet_client.service.TweetClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.TwitterException;

import java.util.List;

@RestController
public class TweetClientController {

    @Autowired
    TweetClientServiceInterface tweetClientServiceInterface;

    @GetMapping(value = "/tweet")
    public ResponseEntity<List<Tweet>> findAll() throws TwitterException {
        List<Tweet> listTweets = tweetClientServiceInterface.findAllInSeveralLanguagesAndUserBeyond1500followers();
        return new ResponseEntity<>(listTweets, HttpStatus.OK);
    }

    @GetMapping(value = "/tweet/{id}")
    public ResponseEntity<Tweet> findById(@PathVariable Long id) {
        Tweet tweet = tweetClientServiceInterface.findById(id);
        return new ResponseEntity<>(tweet, HttpStatus.OK);
    }

    @PutMapping(value = "/tweet/{id}")
    public ResponseEntity<Tweet> validateById(@PathVariable Long id) {
        Tweet tweet = tweetClientServiceInterface.validateById(id);
        return new ResponseEntity<>(tweet, HttpStatus.OK);
    }

    @GetMapping(value = "/tweet/validate")
    public ResponseEntity<List<Tweet>> findAllValidate() {
        List<Tweet> listTweets = tweetClientServiceInterface.findAllValidate();
        return new ResponseEntity<>(listTweets, HttpStatus.OK);
    }

    @GetMapping(value = "/tweet/trends")
    public ResponseEntity<List<String>> findTrends() throws TwitterException {
        List<String> listTweets = tweetClientServiceInterface.findTrends();
        return new ResponseEntity<>(listTweets, HttpStatus.OK);
    }
}
