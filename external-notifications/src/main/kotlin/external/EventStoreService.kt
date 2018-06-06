@file:JvmName("EventstoreApplication")

package external

import com.fasterxml.jackson.databind.ObjectMapper
import external.model.ExternalNotificationRequest
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class EventStoreService {

    val restTemplate = RestTemplate()
    val objectMapper = ObjectMapper()

    fun getEvents(): List<ExternalNotificationRequest> {
        val response = restTemplate.getForObject("http://eventstore:8080/logs", String::class.java)
                ?: return emptyList()
        return response.split("\n").map { objectMapper.readValue(it, ExternalNotificationRequest::class.java) }
    }
}