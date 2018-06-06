package com.team.neon.notifications.config

import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sns.AmazonSNSClient
import com.team.neon.notifications.service.HttpCircuitBreaker
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

class TestAwsCredentialsProvider : AWSCredentialsProvider {
    override fun refresh() {
    }

    override fun getCredentials() = BasicAWSCredentials("", "")

}

@Configuration
class NotificationsConfiguration {

    @Bean
    fun restTemplate() = RestTemplate()

    @Bean
    fun circuitBreaker(restTemplate: RestTemplate) = HttpCircuitBreaker(restTemplate)

    @Bean
    fun amazonSNS() = AmazonSNSClient.builder()
            .withCredentials(TestAwsCredentialsProvider())
            .withEndpointConfiguration(
                    AwsClientBuilder.EndpointConfiguration("http://localstack:4575", "us-east-1")
            )
            .build()
}