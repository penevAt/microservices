package com.team.neon.notifications.service

interface NotificationsHandler {

    fun send(ids: List<String>, content: String)
}