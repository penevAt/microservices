package com.team.neon.notifications.service

import org.springframework.web.client.ResourceAccessException
import org.springframework.web.client.RestTemplate
import java.net.URI
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


interface CircuitBreakerState {
    fun execute(url: String, request: Any?): URI?
    fun getState(): String
}


class OpenState(val httpCircuitBreaker: HttpCircuitBreaker, val restTemplate: RestTemplate) : CircuitBreakerState {
    override fun getState() = "Open"

    var lastAttempt: LocalDateTime = LocalDateTime.now()
    var failures = 0
    override fun execute(url: String, request: Any?): URI? {
        failures++
        if (lastAttempt.until(LocalDateTime.now(), ChronoUnit.SECONDS) >= 5) {
            lastAttempt = LocalDateTime.now()

            val s = restTemplate.postForLocation(url, request)
            httpCircuitBreaker.state = ClosedState(httpCircuitBreaker, restTemplate)

            if (failures-- > 0)
                println("Succeeded after $failures failures")

            return s
        }
        throw Exception("Circuit broken")
    }
}

class ClosedState(val httpCircuitBreaker: HttpCircuitBreaker, val restTemplate: RestTemplate) : CircuitBreakerState {
    override fun getState() = "Closed"


    override fun execute(url: String, request: Any?): URI? {
        try {
            val s = restTemplate.postForLocation(url, request)
            return s
        } catch (e: ResourceAccessException) {
            httpCircuitBreaker.state = OpenState(httpCircuitBreaker, restTemplate)
            throw e
        }
    }
}


class HttpCircuitBreaker(restTemplate: RestTemplate) {
    var state: CircuitBreakerState = ClosedState(this, restTemplate)

    fun postForLocation(url: String, request: Any?): URI? {
        return state.execute(url, request)
    }

}