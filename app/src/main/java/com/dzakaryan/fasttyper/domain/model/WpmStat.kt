package com.dzakaryan.fasttyper.domain.model

data class WpmStat(
    val value: Int = 0,
    val created: Long = -1,
    val userId: String? = null,
)