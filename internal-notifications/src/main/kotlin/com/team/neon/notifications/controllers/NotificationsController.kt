package com.team.neon.notifications.controllers

import com.team.neon.notifications.dao.SubscriptionsDao
import com.team.neon.notifications.model.NotificationRequest
import com.team.neon.notifications.service.HttpCircuitBreaker
import com.team.neon.notifications.service.NotificationsHandler
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class NotificationsController(val subscriptionsDao: SubscriptionsDao, val notificationsHandler: NotificationsHandler, val httpCircuitBreaker: HttpCircuitBreaker) {

    @RequestMapping(method = [(RequestMethod.POST)], path = ["notification"])
    @ResponseBody
    fun sendNotification(@RequestBody notificationRequest: NotificationRequest): String {
        val users = subscriptionsDao.getUsersSubscribedTo(notificationRequest.topic)
        notificationsHandler.send(users.map { it.id }, notificationRequest.content)
        return ""
    }

    @RequestMapping(method = [(RequestMethod.GET)], path = ["status"])
    @ResponseBody
    fun getCircutBreakerStatus(): String {
        return httpCircuitBreaker.state.getState()
    }
}