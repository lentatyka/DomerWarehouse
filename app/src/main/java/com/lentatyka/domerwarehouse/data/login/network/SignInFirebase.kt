package com.lentatyka.domerwarehouse.data.login.network

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignInFirebase @Inject constructor(
    private val auth: FirebaseAuth
) : LoginFirebaseApi {
    override suspend fun invoke(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }
}