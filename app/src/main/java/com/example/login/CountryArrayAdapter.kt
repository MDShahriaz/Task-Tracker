package com.example.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.spinner_item.view.*

class CountryArrayAdapter(context: Context,countryList:List<Country>):ArrayAdapter<Country>(context,0, countryList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = initView(position, convertView, parent)
        view.countryName.setTextColor(ContextCompat.getColor(context, R.color.black))
//        return initView1(position, convertView, parent)
        return view
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View{
        val country = getItem(position)
        val view = LayoutInflater.from(context).inflate(R.layout.spinner_item,parent,false)
        country?.image?.let{view.countryImage.setImageResource(it)}
        view.countryName.text = country?.name
//        view.countryName.setTextColor(ContextCompat.getColor(context, R.color.black)
        return view
    }
}

