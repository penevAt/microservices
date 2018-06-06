package eventstore.logs

import eventstore.model.ExternalNotificationRequest


//@Component
class InMemoryNotificationLogger : NotificationLogger {

    val logs = ArrayList<ExternalNotificationRequest>()

    override fun getLogs(): List<ExternalNotificationRequest> {
        return logs
    }

    override fun log(event: ExternalNotificationRequest) {
        logs.add(event)
    }

}