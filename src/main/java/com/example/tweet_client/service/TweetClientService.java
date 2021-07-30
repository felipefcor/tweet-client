package com.example.tweet_client.service;

import com.example.tweet_client.exception.TweetNotFound;
import com.example.tweet_client.model.Tweet;
import com.example.tweet_client.repository.TweetClientRepository;
import com.example.tweet_client.twitter.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
                .map(status -> tweetClientRepository.save(new Tweet(status.getUser().getName(),
                     status.getText(), existLocation(status.getPlace()) ?
                        status.getPlace().getFullName() : null, false)))
                .collect(Collectors.toList());
    }

    @Override
    public Tweet findById(Long id) {
        Optional<Tweet> tweetOptional =  tweetClientRepository.findById(id);
        if(tweetOptional.isEmpty()) {
            throw new TweetNotFound();
        } else {
            return tweetOptional.get();
        }
    }

    @Override
    public Tweet validateById(Long id) {
        Optional<Tweet> tweetOptional =  tweetClientRepository.findById(id);
        if(tweetOptional.isEmpty()) {
            throw new TweetNotFound();
        } else {
            tweetOptional.get().setValidation(tweetOptional.get().getValidation().equals(Boolean.FALSE));
            tweetClientRepository.save(tweetOptional.get());
            return tweetOptional.get();
        }
    }

    @Override
    public List<Tweet> findAllValidate() {
        return tweetClientRepository.findByValidation(true);
    }

    @Override
    public List<String> findTrends() throws TwitterException {
        Trends trends = twitterService.getTrends();
        return Arrays.stream(trends.getTrends())
                .limit(10)
                .map(Trend::getName)
                .collect(Collectors.toList());
    }

    private boolean existLocation(Place place) {
        return place != null;
    }
}
