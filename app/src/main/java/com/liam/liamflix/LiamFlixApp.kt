package com.liam.liamflix

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.liam.liamflix.core.navigation.BottomNavItem
import com.liam.liamflix.core.navigation.LiamScreens
import com.liam.liamflix.ui.features.home.HomeScreen
import com.liam.liamflix.ui.features.profile.ProfileScreen
import com.liam.liamflix.ui.features.rating.RatingScreen
import com.liam.liamflix.ui.features.sample.SampleScreen
import com.liam.liamflix.ui.features.search.SearchScreen

@Composable
fun LiamFlixApp() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            LiamBottomNavigation(
                containerColor = Color.Green,
                contentColor = Color.White,
                indicatorColor = Color.Green,
                navController = navController
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            LiamNavHost(
                navController = navController,
                startDestination = LiamScreens.Sample.name
            )
        }
    }
}

@Composable
private fun LiamNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = LiamScreens.Sample.name) {
            SampleScreen()
        }

        composable(route = LiamScreens.Home.name) {
            HomeScreen(
                onSearchClicked = { navController.navigate(LiamScreens.Search.name) }
            )
        }

        composable(route = LiamScreens.Rating.name) {
            RatingScreen()
        }

        composable(route = LiamScreens.Profile.name) {
            ProfileScreen()
        }

        composable(route = LiamScreens.Search.name) {
            SearchScreen()
        }
    }
}

@Composable
private fun LiamBottomNavigation(
    modifier: Modifier = Modifier,
    containerColor: Color,
    contentColor: Color,
    indicatorColor: Color,
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Rating,
        BottomNavItem.Profile
    )

    AnimatedVisibility(
        visible = items.map { it.screenRoute }.contains(currentRoute),
//        enter = ,
//        exit =
    ) {
        NavigationBar(
            modifier = modifier,
            containerColor = containerColor,
            contentColor = contentColor,
        ) {
            items.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.screenRoute,
                    label = {
                        Text(
                            text = stringResource(id = item.title),
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = stringResource(id = item.title)
                        )
                    },
                    colors = NavigationBarItemColors(
                        selectedIconColor = contentColor,
                        unselectedIconColor = Color.Gray,
                        selectedTextColor = contentColor,
                        unselectedTextColor = Color.Gray,
                        selectedIndicatorColor = indicatorColor,
                        disabledIconColor = Color.Gray,
                        disabledTextColor = Color.Gray
                    ),
                    onClick = {
                        navController.navigate(item.screenRoute) {
                            navController.graph.startDestinationRoute?.let {
                                popUpTo(it) { saveState = true }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LiamFlixPreView() {
    LiamFlixApp()
}