@file:JvmName("test2MainClass")

package external

import external.handler.ExternalNotificationHandler
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExternalApplication

fun main(args: Array<String>) {
    val context = runApplication<ExternalApplication>(*args)
    val handler = context.getBean(ExternalNotificationHandler::class.java)
    for (event in context.getBean(EventStoreService::class.java).getEvents()) {
        handler.handle(event)
    }
}
