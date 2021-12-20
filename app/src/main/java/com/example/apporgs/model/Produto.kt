package com.example.apporgs.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal


@Parcelize
class Produto(
    val nome: String,
    val descricao: String,
    val valor: BigDecimal,
    val imagem: String? = null
): Parcelable {

}