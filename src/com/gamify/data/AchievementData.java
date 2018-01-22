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
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.gamify.model.Achievement;
import static com.mongodb.client.model.Filters.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class AchievementData {
	static AchievementData ud = null;
	static MongoCollection<Achievement> colAchievement;
	
	public static AchievementData getInstance() {
		if(ud == null) {
			ud = new AchievementData();			
			
			CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
			@SuppressWarnings("resource")
			MongoClient mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
			MongoDatabase dbGame = mongoClient.getDatabase("Gamify");
			colAchievement = dbGame.getCollection("Achievements", Achievement.class);
		}
		return ud;
	}
	
	
	public void insertData(Achievement achievement) {			
			colAchievement.insertOne(achievement);
	}	

	public List<Achievement> getData(String appID) {
		
		List<Achievement> achievements = new ArrayList<Achievement>();
		
		Block<Achievement> printBlock = new Block<Achievement>() {
		    @Override
		    public void apply(final Achievement achievement) {
		        achievements.add(achievement);
		    }
		};
		
		colAchievement.find(eq("appID", appID)).forEach(printBlock);

		return achievements;
		
	}
	
	public Achievement getSpecificData(String appID, String achievementID) {	
		
		final List<Achievement> achievements = new ArrayList<Achievement>();
		
		Block<Achievement> printBlock = new Block<Achievement>() {
		    public void apply(final Achievement achievement) {
		    	achievements.add(achievement);
		    }
		};
		
		Bson filter = Filters.and(
                Filters.eq("appID", appID), 
                Filters.eq("achievementID", achievementID)
                );
		
		colAchievement.find(filter).forEach(printBlock);
		
		return achievements.get(0);
		
	}
	
	public void changeData(String appID, String achievementID, String name, String reward, String goal, String type, String description) {
		Document setData = new Document();
        setData.append("name", name).append("reward", reward).append("goal", goal).append("type", type).append("description", description);
        Document update = new Document();
        update.append("$set", setData);
        
        Bson filter = Filters.and(
                Filters.eq("appID", appID), 
                Filters.eq("achievementID", achievementID)
                );
    
		colAchievement.updateOne(filter, update);
	}
	
	public void inputData(String appID, String achievementID, Achievement achievement) {
		Document setData = new Document();
        setData.append("inputs", achievement.getInputs());
        Document update = new Document();
        update.append("$set", setData);
        
        Bson filter = Filters.and(
                Filters.eq("appID", appID), 
                Filters.eq("achievementID", achievementID)
                );
    
		colAchievement.updateOne(filter, update);
	}

	public void removeData(String appID, String achievementID) {
		
		Bson filter = Filters.and(
                Filters.eq("appID", appID), 
                Filters.eq("achievementID", achievementID)
                );
		
		colAchievement.deleteOne(filter);		
	}


	
	
	
	
	
}

