package com.team.neon.notifications.dao

import com.team.neon.notifications.model.User
import org.springframework.stereotype.Repository

@Repository
class InMemorySubscriptionsDao : SubscriptionsDao {

    override fun getUsersSubscribedTo(topic: String): List<User> {
        return when (topic) {
            "topic1" -> listOf(User("id1"))
            "topic2" -> listOf(User("id1"), User("id2"), User("id3"))
            else -> listOf()
        }

    }
}