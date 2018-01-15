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
import com.gamify.model.Leaderboard;
import static com.mongodb.client.model.Filters.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class LeaderboardData {
	static LeaderboardData ud = null;
	static MongoCollection<Leaderboard> colLeaderboard;

	public static LeaderboardData getInstance() {
		if (ud == null) {
			ud = new LeaderboardData();

			CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
					fromProviders(PojoCodecProvider.builder().automatic(true).build()));
			MongoClient mongoClient = new MongoClient("localhost",
					MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
			MongoDatabase dbGame = mongoClient.getDatabase("Gamify");
			colLeaderboard = dbGame.getCollection("Leaderboards", Leaderboard.class);
		}
		return ud;
	}

	public void insertData(Leaderboard leaderboard) {
		colLeaderboard.insertOne(leaderboard);
	}

	public List<Leaderboard> getData(String appID) {

		List<Leaderboard> leaderboards = new ArrayList<Leaderboard>();

		Block<Leaderboard> printBlock = new Block<Leaderboard>() {
			@Override
			public void apply(final Leaderboard leaderboard) {
				leaderboards.add(leaderboard);
			}
		};

		colLeaderboard.find(eq("appID", appID)).forEach(printBlock);

		return leaderboards;

	}

	public Leaderboard getSpecificData(String appID, String leaderboardID) {

		final List<Leaderboard> leaderboards = new ArrayList<Leaderboard>();

		Block<Leaderboard> printBlock = new Block<Leaderboard>() {
			public void apply(final Leaderboard leaderboard) {
				leaderboards.add(leaderboard);
			}
		};

		Bson filter = Filters.and(Filters.eq("appID", appID), Filters.eq("leaderboardID", leaderboardID));

		colLeaderboard.find(filter).forEach(printBlock);

		return leaderboards.get(0);

	}

	public void changeData(String appID, String leaderboardID, String name, String type, String description) {
		Document setData = new Document();
		setData.append("name", name).append("type", type).append("description", description);
		Document update = new Document();
		update.append("$set", setData);

		Bson filter = Filters.and(Filters.eq("appID", appID), Filters.eq("leaderboardID", leaderboardID));

		colLeaderboard.updateOne(filter, update);
	}

	public void inputData(String appID, String leaderboardID, Leaderboard leaderboard) {
		Document setData = new Document();
		setData.append("inputs", leaderboard.getInputs());
		Document update = new Document();
		update.append("$set", setData);

		Bson filter = Filters.and(Filters.eq("appID", appID), Filters.eq("leaderboardID", leaderboardID));

		colLeaderboard.updateOne(filter, update);
	}

	public void removeData(String appID, String leaderboardID) {

		Bson filter = Filters.and(Filters.eq("appID", appID), Filters.eq("leaderboardID", leaderboardID));

		colLeaderboard.deleteOne(filter);
	}
}
