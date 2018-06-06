package eventstore.logs

import com.fasterxml.jackson.databind.ObjectMapper
import eventstore.model.ExternalNotificationRequest
import org.springframework.stereotype.Component
import java.io.File

@Component
class FileNotificationLogger : NotificationLogger {

    var logs = File("./logs.log")
    var mapper = ObjectMapper()

    init {
        logs.createNewFile()
    }

    override fun getLogs(): List<ExternalNotificationRequest> =
            logs.readLines().map { mapper.readValue(it.trim(), ExternalNotificationRequest::class.java) }

    override fun log(event: ExternalNotificationRequest) {
        logs.appendText(mapper.writeValueAsString(event) + "\n")
    }

}