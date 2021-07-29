package com.example.tweet_client.config.twitter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
public class TwitterConfiguration {
    @Value("${twitter4j.oauth.key}")
    private String key;
    @Value("${twitter4j.oauth.secret-key}")
    private String secretKey;
    @Value("${twitter4j.oauth.access-token}")
    private String accessToken;
    @Value("${twitter4j.oauth.access-token-secret}")
    private String accessTokenSecret;

    public Twitter getInstance(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(key)
                .setOAuthConsumerSecret(secretKey)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }
}
