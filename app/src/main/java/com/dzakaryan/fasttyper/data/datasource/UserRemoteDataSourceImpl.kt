package com.dzakaryan.fasttyper.data.datasource

import com.dzakaryan.fasttyper.domain.datasource.remote.UserRemoteDataSource
import com.dzakaryan.fasttyper.domain.model.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class UserRemoteDataSourceImpl : UserRemoteDataSource {

    private val rootRef = FirebaseFirestore.getInstance()
    private val usersRef = rootRef.collection(USERS)

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

    //region Companion object
    companion object {
        private const val USERS = "users"
    }
    //endregion
}