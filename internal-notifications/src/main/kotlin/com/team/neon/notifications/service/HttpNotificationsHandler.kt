package com.team.neon.notifications.service

import com.team.neon.notifications.model.ExternalNotificationRequest
import org.springframework.http.HttpEntity

//@Service
class HttpNotificationsHandler(val restTemplate: HttpCircuitBreaker) : NotificationsHandler {

    override fun send(ids: List<String>, content: String) {
        if (ids.isEmpty())
            return

        val requestEntity = HttpEntity(ExternalNotificationRequest(ids, content))

        restTemplate.postForLocation("http://localhost:8081/notification", requestEntity)
        print("Notification sent")
    }
}