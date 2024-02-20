package com.liam.liamflix.core.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.liam.liamflix.R

sealed class BottomNavItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val screenRoute: String
) {
    object Home : BottomNavItem(R.string.home, R.drawable.ic_launcher_foreground, LiamScreens.Home.name)
    object Rating : BottomNavItem(R.string.rating, R.drawable.ic_launcher_foreground, LiamScreens.Rating.name)
    object Profile : BottomNavItem(R.string.profile, R.drawable.ic_launcher_foreground, LiamScreens.Profile.name)
}