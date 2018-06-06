package com.team.neon.notifications.model

import com.fasterxml.jackson.annotation.JsonCreator
import java.time.LocalDateTime
import java.util.*

enum class EventType {
    NOTIFICATION
}

data class Event @JsonCreator constructor(val timestamp: LocalDateTime, val type: EventType, val id: String, val payload: ExternalNotificationRequest) {
    constructor(payload: ExternalNotificationRequest) : this(LocalDateTime.now(), EventType.NOTIFICATION, UUID.randomUUID().toString(), payload)
}