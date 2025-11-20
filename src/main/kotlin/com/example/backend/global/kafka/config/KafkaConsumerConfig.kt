package com.example.backend.global.kafka.config

import com.example.backend.global.kafka.dto.ChatMessageDto
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer


@Configuration
class KafkaConsumerConfig (

    private val kafkaProperties: KafkaProperties,

    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String,

    @Value("\${spring.kafka.consumer.group-id}")
    private val groupId : String,

    @Value("\${spring.kafka.consumer.auto-offset-reset")
    private val autoOffsetReset: String
) {

    @Bean
    fun consumerFactory(): ConsumerFactory<String, ChatMessageDto> {
        val deserializer = JsonDeserializer(ChatMessageDto::class.java)
        deserializer.addTrustedPackages("com.song.chatpractice.kafka.dto")

        val props = mapOf<String, Any>(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG to groupId,
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to autoOffsetReset,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to deserializer
        )

        return DefaultKafkaConsumerFactory(props, StringDeserializer(), deserializer)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, ChatMessageDto> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, ChatMessageDto>()
        factory.consumerFactory = consumerFactory()
        return factory
    }

}
