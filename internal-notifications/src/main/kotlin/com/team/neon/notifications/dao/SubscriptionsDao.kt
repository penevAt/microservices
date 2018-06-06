package com.team.neon.notifications.dao

import com.team.neon.notifications.model.User

interface SubscriptionsDao {

    fun getUsersSubscribedTo(topic: String): List<User>
}