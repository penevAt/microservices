package com.team.neon.notifications.model

import com.fasterxml.jackson.annotation.JsonCreator

data class NotificationRequest @JsonCreator constructor(val topic: String, val content: String)