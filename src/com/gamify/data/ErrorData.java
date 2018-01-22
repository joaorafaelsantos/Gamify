package com.gamify.data;

import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.gamify.model.Error;
import static com.mongodb.client.model.Filters.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class ErrorData {
	static ErrorData ed = null;
	static MongoCollection<Error> colError;
	
	public static ErrorData getInstance() {
		if(ed == null) {
			ed = new ErrorData();			
			
			CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
			@SuppressWarnings("resource")
			MongoClient mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
			MongoDatabase dbGame = mongoClient.getDatabase("Gamify");
			colError = dbGame.getCollection("Errors", Error.class);
		}
		return ed;
	}
	
	public List<Error> getData() {
		
		List<Error> errors = new ArrayList<Error>();
		
		Block<Error> printBlock = new Block<Error>() {
		    @Override
		    public void apply(final Error error) {
		        errors.add(error);
		    }
		};
		
		colError.find().forEach(printBlock);

		return errors;
		
	}
	
	public Error getData(String errorID) {	
		
		final List<Error> errors = new ArrayList<Error>();
		
		Block<Error> printBlock = new Block<Error>() {
		    public void apply(final Error error) {
		    	errors.add(error);
		    }
		};
		
		colError.find(eq("errorID",errorID)).forEach(printBlock);
		
		return errors.get(0);
		
	}
	


	
	
	
	
	
}

