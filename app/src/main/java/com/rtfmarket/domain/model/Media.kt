package com.rtfmarket.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Media(val main: String,
                 val fallback: String): Parcelable