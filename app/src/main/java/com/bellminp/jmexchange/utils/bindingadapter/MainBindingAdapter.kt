package com.bellminp.jmexchange.utils.bindingadapter

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bellminp.jmexchange.R

@BindingAdapter("setRecipientCountryValue")
fun setRecipientCountryValue(tv: TextView, type: Int) {
    tv.text = tv.context.resources.getStringArray(R.array.recipient_country_list)[type]
}