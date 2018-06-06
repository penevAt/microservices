package external.controllers

import external.handler.ExternalNotificationHandler
import external.model.Event
import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Service

@Service
class SqsReceiver(val externalNotificationHandler: ExternalNotificationHandler) {

    @SqsListener("external-notifications")
    fun pushNotification(@NotificationMessage event: Event) {
        externalNotificationHandler.handle(event.payload)
    }
}