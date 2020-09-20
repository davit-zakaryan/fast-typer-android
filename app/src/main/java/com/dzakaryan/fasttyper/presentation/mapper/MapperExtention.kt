package com.dzakaryan.fasttyper.presentation.mapper

import com.dzakaryan.fasttyper.domain.model.User
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toDomainUser(): User {
    return User(
        uid = this.uid,
        displayName = this.displayName,
        email = this.email,
        photoUrl = this.photoUrl.toString(),
        stats = null
    )
}

