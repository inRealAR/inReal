package com.proct.activities.inreal.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun Fragment.findChildNavControllerByHostId(navHostId: Int): NavController =
    (childFragmentManager.findFragmentById(navHostId) as NavHostFragment).navController
