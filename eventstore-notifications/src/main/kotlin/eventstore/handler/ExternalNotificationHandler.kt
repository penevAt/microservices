package eventstore.handler

import eventstore.logs.NotificationLogger
import eventstore.model.ExternalNotificationRequest
import org.springframework.stereotype.Service

@Service
class ExternalNotificationHandler(val notificationLogger: NotificationLogger) {

    fun handle(externalNotificationRequest: ExternalNotificationRequest) {
        notificationLogger.log(externalNotificationRequest)
        println(externalNotificationRequest.ids)
        println(externalNotificationRequest.content)
    }
}