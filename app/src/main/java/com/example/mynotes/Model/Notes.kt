package com.example.mynotes.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Notes")
@Suppress
class Notes(
    @PrimaryKey(autoGenerate = true)
    var id: Int? =null,
    var title: String,
    var notes: String,
    //var imgpath:String,
    var date: String,

):Parcelable