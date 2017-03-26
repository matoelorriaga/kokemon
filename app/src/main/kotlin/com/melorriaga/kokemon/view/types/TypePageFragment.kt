package com.melorriaga.kokemon.view.types

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.melorriaga.kokemon.R
import kotlinx.android.synthetic.main.fragment_type_page.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class TypePageFragment : Fragment(), AnkoLogger {

    companion object {
        val ARG_TYPE_ID = "ARG_TYPE_ID"
        val ARG_TYPE_NAME = "ARG_TYPE_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info { "TypePageFragment: ${arguments.getInt(ARG_TYPE_ID)}, ${arguments.getString(ARG_TYPE_NAME)}" }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_type_page, container, false)

    override fun onResume() {
        super.onResume()
        type_name_text_view.text = arguments.getString(ARG_TYPE_NAME)
    }

}
