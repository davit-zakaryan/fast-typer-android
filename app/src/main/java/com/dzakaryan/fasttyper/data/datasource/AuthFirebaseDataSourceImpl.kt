package com.dzakaryan.fasttyper.data.datasource

import com.dzakaryan.fasttyper.domain.datasource.AuthRemoteDataSource
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthFirebaseDataSourceImpl : AuthRemoteDataSource {

    override suspend fun sighIn() {
        TODO("Not yet implemented")
    }

    override suspend fun signOut() {
        Firebase.auth.signOut()
    }
}