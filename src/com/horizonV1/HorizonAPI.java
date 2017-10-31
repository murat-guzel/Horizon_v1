package com.horizonV1; 

import java.util.ArrayList;

import javax.ws.rs.GET; 
import javax.ws.rs.POST;
import javax.ws.rs.Path; 
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;  

import twitter4j.Twitter;
@Path("/api") 

public class HorizonAPI {  
    
   @GET 
   @Path("/getresult/{fact1}/{fact2}")
   @Produces(MediaType.TEXT_PLAIN) 
   public String HelloWorld(@PathParam("fact1") String fact1,
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
    
	   
      return fact1+" : " + Float.toString((fact1Positive - fact1Negative)) + " "+fact2 +" : " + Float.toString((fact2Positive - fact2Negative)); 
   }  
}