package com.horizonV1;

import java.util.*;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaProducer {
	
	 public void Produce(){
		 
		 	//long events = Long.parseLong(args[0]);
	        Random rnd = new Random();
	 
	        Properties props = new Properties();
	        props.put("metadata.broker.list", "broker1:9092,broker2:9092 ");
	        props.put("serializer.class", "kafka.serializer.StringEncoder");
	        props.put("partitioner.class", "example.producer.SimplePartitioner");
	        props.put("request.required.acks", "1");
	 
	        ProducerConfig config = new ProducerConfig(props);
	 
	        Producer<String, String> producer = new Producer<String, String>(config);
	 
//	        for (long nEvents = 0; nEvents < events; nEvents++) { 
//	               long runtime = new Date().getTime();  
//	               String ip = "a"; 
//	               String msg = "b"; 
//	               KeyedMessage<String, String> data = new KeyedMessage<String, String>("page_visits", ip, msg);
//	               //producer.send(data);
//	        }
	        producer.close();
		 
		 
	 }

}
 	