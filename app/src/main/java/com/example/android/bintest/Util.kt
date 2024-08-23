package com.example.android.bintest

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.bintest.database.CardInfoEntity

@BindingAdapter("formattedCityString")
fun TextView.formattedCityString(item: CardInfoEntity?){
    text = context.getString(R.string.city_field, item?.bankCity)
}

@BindingAdapter("formattedUrlString")
fun TextView.formattedUrlString(item: CardInfoEntity?){
    text = context.getString(R.string.url_field, item?.bankUrl)
}

@BindingAdapter("formattedPhoneString")
fun TextView.formattedPhoneString(item: CardInfoEntity?){
    text = context.getString(R.string.number_field, item?.bankPhone)
}


//@BindingAdapter("sleepDurationFormatted")
//fun TextView.setSleepDurationFormatted(item: SleepNight?){
//    item?.let {
//        text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli,context.resources)
//    }
//}