package com.chrrissoft.localstorage.app

import android.os.Parcel
import android.os.Parcelable

data class Uris(
    val data: String
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString() ?: "")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(data)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Uris> {
        override fun createFromParcel(parcel: Parcel): Uris {
            return Uris(parcel)
        }

        override fun newArray(size: Int): Array<Uris?> {
            return arrayOfNulls(size)
        }
    }
}
