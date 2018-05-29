package com.rtfmarket.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(val id: String,
                    val name: String,
                    val slug: String,
                    val title: String,
                    val description: String,
                    val products: List<Product>? = null): Parcelable