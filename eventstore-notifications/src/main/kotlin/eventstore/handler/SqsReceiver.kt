package eventstore.controllers

import eventstore.handler.ExternalNotificationHandler
import eventstore.model.Event
import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.stereotype.Service

@Service
class SqsReceiver(val externalNotificationHandler: ExternalNotificationHandler) {

    @SqsListener("external2-notifications")
    fun pushNotification(@NotificationMessage event: Event) {
        externalNotificationHandler.handle(event.payload)
    }
}