package com.dzakaryan.fasttyper.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val uuId: String,
    val name: String,
) : Parcelable