@file:JvmName("test3MainClass")

package eventstore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EventstoreApplication

fun main(args: Array<String>) {
    runApplication<EventstoreApplication>(*args)
}
