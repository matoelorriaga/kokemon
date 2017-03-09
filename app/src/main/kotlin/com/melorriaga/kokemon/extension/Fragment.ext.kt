package com.melorriaga.kokemon.extension

import android.support.v4.app.Fragment
import com.melorriaga.kokemon.KokemonApp

val Fragment.app: KokemonApp
    get() = activity.application as KokemonApp
