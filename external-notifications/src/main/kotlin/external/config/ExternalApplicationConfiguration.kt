package external.config

import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsyncClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class TestAwsCredentialsProvider : AWSCredentialsProvider {
    override fun refresh() {
    }

    override fun getCredentials() = BasicAWSCredentials("", "")

}

@Configuration
class ExternalApplicationConfiguration {

    @Bean
    fun amazonSQS() = AmazonSQSAsyncClient.asyncBuilder().withCredentials(TestAwsCredentialsProvider())
            .withEndpointConfiguration(
                    AwsClientBuilder.EndpointConfiguration("http://localstack:4576", "us-east-1")
            )

            .build()
}