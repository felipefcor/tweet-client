package com.example.tweet_client.repository;

import com.example.tweet_client.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetClientRepository extends JpaRepository<Tweet, Long> {
    List<Tweet> findByValidation(boolean validation);
}
