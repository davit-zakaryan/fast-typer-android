package com.dzakaryan.fasttyper.domain.model

data class User(
    val uid: String,
    val displayName: String?,
    val email: String?,
    val photoUrl: String?,
    val stats: List<WpmStat>?
)