package external.handler

import external.model.ExternalNotificationRequest
import org.springframework.stereotype.Service

@Service
class ExternalNotificationHandler {

    var count = 0

    fun handle(externalNotificationRequest: ExternalNotificationRequest) {
        count++
        println(externalNotificationRequest.ids)
        println(externalNotificationRequest.content)
    }
}