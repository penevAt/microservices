package com.team.neon.notifications

import au.com.dius.pact.consumer.Pact
import au.com.dius.pact.consumer.PactProviderRuleMk2
import au.com.dius.pact.consumer.PactVerification
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import org.junit.Rule
import org.junit.Test
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

class NotificationsApplicationTests {

    @Rule
    @JvmField
    var mockProvider = PactProviderRuleMk2("external_notifications", "localhost", 8082, this)

    @Pact(consumer = "internal_notifications", provider = "external_notifications")
    fun createPact(builder: PactDslWithProvider) = builder
            .given("test GET")
            .uponReceiving("GET REQUEST")
            .method("GET")
            .path("/logs")
            .willRespondWith()
            .status(200)
            .headers(mapOf(Pair("Content-Type", "text/plain")))
            .body("{\"ids\":[\"id1\",\"id2\",\"id3\"],\"content\":\"HELLO2\"}")
            .toPact()

    @Test
    @PactVerification("external_notifications")
    fun runTest() {
        RestTemplate().getForEntity<String>(url = "http://localhost:8082/logs")
    }
}