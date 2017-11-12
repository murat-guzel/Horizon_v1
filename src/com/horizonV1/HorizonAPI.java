package com.horizonV1; 

import java.util.ArrayList;

import javax.ws.rs.GET; 
import javax.ws.rs.POST;
import javax.ws.rs.Path; 
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;  

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import twitter4j.Twitter;
@Path("/api") 

public class HorizonAPI {  
    
   @GET 
   @Path("/getresult/{fact1}/{fact2}")
   @Produces(MediaType.APPLICATION_JSON) 
   public JSONObject  HelloWorld(@PathParam("fact1") String fact1,
		    @PathParam("fact2") String fact2){ 
	   
	   System.out.println(fact1 + fact2);
	   TwitterSearch twitter = new TwitterSearch();
	   ArrayList<TweetEntity> fact1List = twitter.Search(fact1);
	   ArrayList<TweetEntity> fact2List = twitter.Search(fact2);
	   
	   float fact1Positive = 0;
	   float fact2Positive = 0;
	   float fact1Negative = 0;
	   float fact2Negative = 0;
	   
	   Scoring scoring = new Scoring();
	   for (int i = 0; i < fact1List.size(); i++) {
		
		   fact1Positive = scoring.GetScore(fact1List.get(i).Content.substring(20)).Positive;
		   fact1Negative = scoring.GetScore(fact1List.get(i).Content.substring(20)).Negative;
	   	}
 	   for (int i = 0; i < fact2List.size(); i++) {
 			
 		   fact2Positive = scoring.GetScore(fact2List.get(i).Content).Positive;
 		   fact2Negative = scoring.GetScore(fact2List.get(i).Content).Negative;
	   	}
 	   
 	  JSONArray TweetArray = new JSONArray();
 	  
 	 
 	   
 	  for (int i = 0; i < fact1List.size(); i++) {
 		  
 		  if (fact1List.get(i).Negative>0.6) {
 			JSONObject tweetNegativeJSON= new JSONObject();
 	 		tweetNegativeJSON.put("name", fact1List.get(i).Name);
 	 		tweetNegativeJSON.put("image", "<img src="+fact1List.get(i).ProfileImage+"></img>");
 	 		tweetNegativeJSON.put("content", fact1List.get(i).Content);
 	 		tweetNegativeJSON.put("PosOrNeg", "Negative");
 	 		TweetArray.add(tweetNegativeJSON);
 	 		 
		}
 		  else{
 			
	 		 JSONObject tweetPositiveJSON= new JSONObject();
	 		 tweetPositiveJSON.put("name", fact1List.get(i).Name);
	 	 	 tweetPositiveJSON.put("image", "<img src="+fact1List.get(i).ProfileImage+"></img>");
	 	 	 tweetPositiveJSON.put("content", fact1List.get(i).Content);
	 	 	tweetPositiveJSON.put("PosOrNeg", "Positive");
	 	 	 
	 	 	TweetArray.add(tweetPositiveJSON);
 		  		}
 		  
 		
		    
	   	}
 	  for (int i = 0; i < fact1List.size(); i++) {
 		 if (fact2List.get(i).Negative>0.6) {
   			JSONObject tweetNegativeJSON= new JSONObject();
   	 		tweetNegativeJSON.put("name", fact2List.get(i).Name);
   	 		tweetNegativeJSON.put("image", "<img src="+fact2List.get(i).ProfileImage+"></img>");
   	 		tweetNegativeJSON.put("content", fact2List.get(i).Content);
   	 		tweetNegativeJSON.put("PosOrNeg", "Negative");
   	 		TweetArray.add(tweetNegativeJSON);
   	 		 
  		}
   		  else{
   			
  	 		 JSONObject tweetPositiveJSON= new JSONObject();
  	 		 tweetPositiveJSON.put("name", fact2List.get(i).Name);
  	 	 	 tweetPositiveJSON.put("image", "<img src="+fact2List.get(i).ProfileImage+"></img>");
  	 	 	 tweetPositiveJSON.put("content", fact2List.get(i).Content);
  	 	 	tweetPositiveJSON.put("PosOrNeg", "Positive");
  	 	 	 
  	 	 	TweetArray.add(tweetPositiveJSON);
   		  		}
	}
 	 
 	  JSONObject resultJSON= new JSONObject();
 	   	   
 	  resultJSON.put("fact1", fact1Positive-fact1Negative);
 	  resultJSON.put("fact2", fact2Positive-fact2Negative);
 	  resultJSON.put("Tweets", TweetArray);
 	   
       return resultJSON;
   }  
}