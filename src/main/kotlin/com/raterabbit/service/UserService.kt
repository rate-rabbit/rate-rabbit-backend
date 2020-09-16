package com.raterabbit.service

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import com.raterabbit.entity.User
import org.bson.BsonValue

class UserService(private val mongoClient: MongoClient) {
    fun findByUserName(userName: String): User? {
        return getCollection().find(Filters.eq("userName", userName)).first()
    }

    fun insert(user: User): BsonValue? {
        return getCollection().insertOne(user).insertedId
    }

    private fun getCollection(): MongoCollection<User> {
        return mongoClient.getDatabase("raterabbit")
                .getCollection("users", User::class.java)
    }
}
