package eventstore.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import eventstore.handler.ExternalNotificationHandler
import eventstore.logs.NotificationLogger
import eventstore.model.ExternalNotificationRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class ExternalController(val externalNotificationHandler: ExternalNotificationHandler, val eventLogger: NotificationLogger) {

    var objectMapper = ObjectMapper()

    @RequestMapping(method = [(RequestMethod.POST)], path = ["notification"])
    @ResponseBody
    fun pushNotification(@RequestBody externalNotificationRequest: ExternalNotificationRequest): String {
        externalNotificationHandler.handle(externalNotificationRequest)
        return ""
    }

    @RequestMapping(method = [(RequestMethod.GET)], path = ["logs"], produces = ["text/plain"])
    @ResponseBody
    fun getLogs(): String = eventLogger.getLogs().map { objectMapper.writeValueAsString(it) }.joinToString(separator = "\n")
}