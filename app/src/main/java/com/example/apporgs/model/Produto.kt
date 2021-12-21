package com.example.apporgs.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Entity
@Parcelize
class Produto(
    val id: Int,
    val nome: String,
    val descricao: String,
    val valor: BigDecimal,
    val imagem: String? = null
): Parcelable {

}