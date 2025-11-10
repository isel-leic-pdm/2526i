package pt.isel.pdm.pokemonoftheday.domain


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SimpleModel(
    val id: String,
    val number: Int,
    val array: List<Int>,
)  : Parcelable{

    companion object {
        val none = SimpleModel("none", 1, listOf())
    }
}