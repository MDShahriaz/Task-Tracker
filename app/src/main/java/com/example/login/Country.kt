package com.example.login

data class Country(val image: Int, val name: String)

object Countries {

    private val images = intArrayOf(
        R.drawable.cc,
        R.drawable.china,
        R.drawable.pak,
        R.drawable.srl,
        R.drawable.china
    )

    private val countries = arrayOf(
        "+88",
        "+60",
        "+10",
        "+47",
        "+34"
    )

    var list: ArrayList<Country>? = null
        get() {

            if (field != null)
                return field

            field = ArrayList()
            for (i in images.indices) {

                val imageId = images[i]
                val countryName = countries[i]

                val country = Country(imageId, countryName)
                field!!.add(country)
            }

            return field
        }
}
