package com.melorriaga.kokemon.view.types

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class TypesFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var fragments = listOf<TypePageFragment>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int): String? =
            fragments[position].arguments.getString(TypePageFragment.ARG_TYPE_NAME)

    override fun getItem(position: Int) = fragments[position]

}
