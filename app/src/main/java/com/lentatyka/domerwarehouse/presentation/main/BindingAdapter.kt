package com.lentatyka.domerwarehouse.presentation.main

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("address")
fun setAddress(view: TextView, list: List<String>?) {
    view.text = list?.joinToString(separator = " / ") ?: "-"
}
