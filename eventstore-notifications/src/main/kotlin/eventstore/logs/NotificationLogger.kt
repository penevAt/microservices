package eventstore.logs

import eventstore.model.ExternalNotificationRequest


interface NotificationLogger {
    fun log(event: ExternalNotificationRequest)

    fun getLogs(): List<ExternalNotificationRequest>
}