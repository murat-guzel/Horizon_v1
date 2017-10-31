package com.horizonV1;

 
import java.util.ArrayList;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
 

public class TwitterSearch {

	public ArrayList<TweetEntity> Search(String fact)
	{
		 
		Twitter twitter = Configuration(); 
	    Query query = new Query(fact).count(5);
	    QueryResult result;
	    ArrayList<TweetEntity> tweetList = new ArrayList<TweetEntity>();
		try {
			result = twitter.search(query);
			 for (Status status : result.getTweets()) {
			        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
			        TweetEntity tweet = new TweetEntity();
			        tweet.Content = status.getText();
			        tweetList.add(tweet);
			    }
			 
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			return null;
		}
	   
		return tweetList;
		
	}
	
	public Twitter Configuration(){
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("9b2CZNxxfhtPs3ZNM0SetpJ9C")
		  .setOAuthConsumerSecret("aTaN3x9Rj8dUshhFuQv1uKMVclahYsLD8DR6fcTJghAdhLWLCk")
		  .setOAuthAccessToken("922433559910109186-yzjNYdnWHcSX6GxpDf8UXoMGqfsxdFs")
		  .setOAuthAccessTokenSecret("XVylE0qZugw9pMNp7kLNNAhWGJsz7gX7iSm5fKdeRIKoS");
		TwitterFactory tf = new TwitterFactory(cb.build());
		return  tf.getInstance();
	}
}
