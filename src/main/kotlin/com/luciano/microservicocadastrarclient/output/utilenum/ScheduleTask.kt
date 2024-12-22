package com.luciano.microservicocadastrarclient.output.utilenum

enum class ScheduleTask(val displayName: String) {
    DONE(ServiceStatus.DONE),
    PENDING(ServiceStatus.PENDING),
    NOT_DONE(ServiceStatus.NOT_DONE);

    companion object {
        fun fromDisplayName(displayName: String): ScheduleTask? {
            return values().find { it.displayName == displayName }
        }
    }

}
