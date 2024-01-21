package com.example.contactsapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val name: String? ,
    val phone:String?,
    val description:String?,
    val image:Int

):Parcelable