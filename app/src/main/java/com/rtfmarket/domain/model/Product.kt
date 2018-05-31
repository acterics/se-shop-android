package com.rtfmarket.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(val id: String,
                   val name: String,
                   val title: String,
                   val slug: String,
                   val description: String,
                   val price: Int,
                   val media: Media): Parcelable