package com.dzakaryan.fasttyper.data.datasource

import com.dzakaryan.fasttyper.data.datasource.FirestoreConstants.USERS
import com.dzakaryan.fasttyper.domain.datasource.remote.UserRemoteDataSource
import com.dzakaryan.fasttyper.domain.model.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class UserRemoteDataSourceImpl : UserRemoteDataSource {

    private val usersRef = Firebase.firestore.collection(USERS)

    override suspend fun saveAuthenticatedUser(user: User): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val userDoc = usersRef.document(user.uid).get().await()
                if (!userDoc.exists()) {
                    usersRef.document(user.uid).set(user).await()
                }
                true
            } catch (e: Exception) {
                false
            }
        }
    }
}