package com.lentatyka.domerwarehouse.data.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface LoginRepository {
    suspend operator fun invoke(email: String, password: String): FirebaseUser

    class Base @Inject constructor(
        private val fbAuth: FirebaseAuth
    ) : LoginRepository {
        override suspend fun invoke(email: String, password: String) =
            suspendCoroutine<FirebaseUser> { continuation ->
                fbAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    continuation.resume(it.user!!)

                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
            }

    }
}