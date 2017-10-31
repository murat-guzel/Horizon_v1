package com.horizonV1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.*;

public class Scoring {

	public TweetEntity GetScore(String tweet){
		 
		 try {
			    tweet = tweet.replace(" ", "+");

				URL url = new URL("https://api.uclassify.com/v1/uClassify/Sentiment/classify/?readKey=P1WHBORJAzK5&text="+tweet);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				conn.connect(); 

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

				String output;
				String jsonObj = null;
				System.out.println("Output from Server .... \n");
				
				while ((output = br.readLine()) != null) {
					System.out.println(output);
					jsonObj = output;
				}
				
				JSONObject obj = new JSONObject(jsonObj);
				String positive = obj.optString("positive");
				String negative = obj.optString("negative");
				System.out.println("MyResult : " +positive);
				 TweetEntity tweetEntity = new TweetEntity();
				 tweetEntity.Content = tweet;
				 tweetEntity.Negative = Float.parseFloat(negative);;
				 tweetEntity.Positive= Float.parseFloat(positive);;
				 
				 conn.disconnect();
				 
				 return tweetEntity;
						
				

			  } catch (MalformedURLException e) {

				  	 TweetEntity tweetEntity = new TweetEntity();
					 tweetEntity.Content = tweet;
					 tweetEntity.Negative = Float.parseFloat("0.5");;
					 tweetEntity.Positive= Float.parseFloat("0.5");;
					 
				 
					 
					 return tweetEntity;

			  } catch (Exception e) {

				  TweetEntity tweetEntity = new TweetEntity();
					 tweetEntity.Content = tweet;
					 tweetEntity.Negative = Float.parseFloat("0.5");;
					 tweetEntity.Positive= Float.parseFloat("0.5");;
					 
				 
					 
					 return tweetEntity;

			  }
		 
		 
		 
		 
	}
}
