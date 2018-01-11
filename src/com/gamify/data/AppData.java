package com.gamify.data;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import com.gamify.model.App;
import static com.mongodb.client.model.Filters.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class AppData {
	static AppData ad = null;
	static MongoCollection<App> colApp;
	
	public static AppData getInstance() {
		if(ad == null) {
			ad = new AppData();			
			
			CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
			MongoClient mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
			MongoDatabase dbGame = mongoClient.getDatabase("Gamify");
			colApp = dbGame.getCollection("Apps", App.class);
		}
		return ad;
	}
	
	
	public void insertData(App app) {			
			colApp.insertOne(app);
	}	

	public List<App> getAllData() {
	List<App> apps = new ArrayList<App>();
	
	Block<App> printBlock = new Block<App>() {
	    @Override
	    public void apply(final App app) {
	        apps.add(app);
	    }
	};
	
	colApp.find().forEach(printBlock);

	return apps;
}
	public List<App> getData(String userRequested) {
		
		List<App> apps = new ArrayList<App>();
		
		Block<App> printBlock = new Block<App>() {
		    @Override
		    public void apply(final App app) {
		        apps.add(app);
		    }
		};
		
		colApp.find(eq("userID", userRequested)).forEach(printBlock);

		return apps;
		
	}
	
	public List<App> getSpecificData(String appID, String userRequested) {	
		
		final List<App> apps = new ArrayList<App>();
		
		Block<App> printBlock = new Block<App>() {
		    public void apply(final App app) {
		    	apps.add(app);
		    }
		};
		Bson filter = Filters.and(
                Filters.eq("appID", appID), 
                Filters.eq("userID", userRequested)
                );
		
		colApp.find(filter).forEach(printBlock);
		
		return apps;
		
	}
	
	public void changeData(String appID, String appName, String type, String description) {
		Document setData = new Document();
        setData.append("appName", appName).append("type", type).append("description", description);
        Document update = new Document();
        update.append("$set", setData);
    
		colApp.updateOne(eq("appID",appID), update);
	}

	public void removeData(String appID) {
		colApp.deleteOne(eq("appID", appID));		
	}


	
	
	
	
	
}

