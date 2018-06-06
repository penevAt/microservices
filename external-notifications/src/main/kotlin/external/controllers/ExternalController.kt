package external.controllers

import external.handler.ExternalNotificationHandler
import external.model.ExternalNotificationRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class ExternalController(val externalNotificationHandler: ExternalNotificationHandler) {

    @RequestMapping(method = [(RequestMethod.POST)], path = ["notification"])
    @ResponseBody
    fun pushNotification(@RequestBody externalNotificationRequest: ExternalNotificationRequest): String {
        externalNotificationHandler.handle(externalNotificationRequest)
        return ""
    }

    @RequestMapping(method = [(RequestMethod.GET)], path = ["count"])
    @ResponseBody
    fun getCount(): String = externalNotificationHandler.count.toString()
}