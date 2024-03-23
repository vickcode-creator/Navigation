package com.example.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.navigation.ui.theme.NavigationTheme

data class BottomNavigationItem(val title:String,
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
    val hasNews:Boolean,
    val badgeCount:Int?= null)

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationTheme {
                val Items = listOf(
                    BottomNavigationItem(title = "Menu",
                    selectedIcon = Icons.Filled.Home,
                    unselectedIcon = Icons.Outlined.Home,
                    hasNews = true,
                    badgeCount = 0),
                    BottomNavigationItem(title = "Chart",
                    selectedIcon = Icons.Filled.Email,
                    unselectedIcon = Icons.Outlined.Email,
                    hasNews = true,
                    badgeCount = 0),
                    BottomNavigationItem(title = "Settings",
                        selectedIcon = Icons.Filled.Settings,
                        unselectedIcon = Icons.Outlined.Settings,
                        hasNews = true,
                        badgeCount = 0)
                )

                var selectedItemIndex by rememberSaveable {
                    mutableStateOf(0)
                }


                Surface(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Cyan),) {

                    Scaffold (bottomBar = { NavigationBar {
                        Items.forEachIndexed { index, Item ->
                            NavigationBarItem(
                                selected = selectedItemIndex== index,
                                onClick = { selectedItemIndex = index },
                                label = { Text(text = Item.title)},
                                icon = { BadgedBox(badge = {
                                    if (Item.badgeCount != null){
                                        Badge{
                                            Text(text = Item.badgeCount.toString())
                                        }
                                    }else if(Item.hasNews){
                                        Badge(){

                                        }
                                    }

                                }) {
                                    Icon(imageVector =if (index == selectedItemIndex){
                                                                                     Item.selectedIcon
                                    }else{Item.unselectedIcon},
                                        contentDescription =Item.title )

                                } })

                        }

                    }}) {

                    }

                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavigationTheme {

    }
}