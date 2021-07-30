package com.example.tweet_client.twitter;

import com.example.tweet_client.config.twitter.TwitterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Status;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TwitterService {

    @Autowired
    TwitterConfiguration twitterConfiguration;

    public List<Status> getTimeLine() throws TwitterException {
        Twitter twitter = twitterConfiguration.getInstance();
        return new ArrayList<>(twitter.getHomeTimeline());
    }

    public Trends getTrends() throws TwitterException {
        Twitter twitter = twitterConfiguration.getInstance();
        int barcelona = 753692;
        return twitter.getPlaceTrends(barcelona);
    }
}
