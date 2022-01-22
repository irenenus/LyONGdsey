package com.irene.lyongdsey.utils

import android.util.Patterns

fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
fun CharSequence.isValidURL() = Patterns.WEB_URL.matcher(this).matches()
