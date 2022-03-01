package com.example.tweet_client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(
            value = HttpStatus.NOT_FOUND,
            reason = "Tweet not found")
    @ExceptionHandler(TweetNotFound.class)
    public void handleTweetNotFound(TweetNotFound exception) {
    }

    @ResponseStatus(
            value = HttpStatus.NO_CONTENT,
            reason = "Tweet not found")
    @ExceptionHandler(TimelineNotAvailable.class)
    public void handleTimelineNotAvailable(TimelineNotAvailable exception) {
    }
}