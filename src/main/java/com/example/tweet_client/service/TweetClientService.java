package com.example.tweet_client.service;

import com.example.tweet_client.model.Tweet;
import com.example.tweet_client.repository.TweetClientRepository;
import com.example.tweet_client.twitter.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Place;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetClientService implements TweetClientServiceInterface{

    @Autowired
    TweetClientRepository tweetClientRepository;

    @Autowired
    TwitterService twitterService;

    @Override
    public List<Tweet> findAllInSeveralLanguagesAndUserBeyond1500followers() throws TwitterException {

        List<Status> timeline = twitterService.getTimeLine();
        return timeline.stream()
                .filter(status -> status.getUser().getFollowersCount() > 1500)
                .filter(status -> status.getLang().equals("en") || status.getLang().equals("es") ||
                                  status.getLang().equals("it") || status.getLang().equals("fr"))
                .map(status -> tweetClientRepository.save(new Tweet(status.getId(), status.getUser().getName(),
                     status.getText(), existLocation(status.getPlace()) ?
                        status.getPlace().getFullName() : null, false)))
                .collect(Collectors.toList());
    }

    private boolean existLocation(Place place) {
        return place != null;
    }
}
