package com.team.neon.notifications.service

import com.amazonaws.services.sns.AmazonSNS
import com.team.neon.notifications.model.Event
import com.team.neon.notifications.model.ExternalNotificationRequest
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate
import org.springframework.stereotype.Service

@Service
class SnsNotificationsHandler(val restTemplate: HttpCircuitBreaker, val amazonSNS: AmazonSNS) : NotificationsHandler {

    val notificationMessagingTemplate = NotificationMessagingTemplate(amazonSNS)
    //    val topic = "arn:aws:sns:us-east-1:123456789012:notifications:a539e8da-cc0a-4e71-a581-903eb7f30b62"
    val topic = "notifications"

    override fun send(ids: List<String>, content: String) {
        if (ids.isEmpty())
            return

        this.notificationMessagingTemplate.sendNotification(topic, Event(ExternalNotificationRequest(ids, content)), "notification")

        print("Notification sent")
    }
}