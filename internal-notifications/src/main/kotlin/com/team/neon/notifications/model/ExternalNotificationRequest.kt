package com.team.neon.notifications.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ExternalNotificationRequest constructor(@JsonProperty("ids") val ids: List<String>, @JsonProperty("content") val content: String)