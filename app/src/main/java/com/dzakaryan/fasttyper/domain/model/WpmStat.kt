package com.dzakaryan.fasttyper.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WpmStat(
    val value: Int,
    val created: Long,
    val userId: String,
) : Parcelable