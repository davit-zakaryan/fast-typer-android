package com.dzakaryan.fasttyper.presentation.core

import android.widget.ImageView
import android.widget.TextView

interface NavigationHeaderView {

    fun getHeaderImageView(): ImageView

    fun getDisplayNameView(): TextView

    fun getEmailView(): TextView
}