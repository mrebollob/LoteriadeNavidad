package com.mrebollob.loteria.android.presentation.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mrebollob.loteria.android.BuildConfig
import com.mrebollob.loteria.android.R
import com.mrebollob.loteria.android.presentation.platform.extension.loadCustomTabs
import com.mrebollob.loteria.android.presentation.platform.extension.sendEmail
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme
import com.mrebollob.loteria.android.presentation.settings.manageticket.ui.ManageTicketsScreen
import com.mrebollob.loteria.android.presentation.settings.menu.SettingItemId
import com.mrebollob.loteria.android.presentation.settings.menu.ui.SettingsScreen
import com.mrebollob.loteria.android.presentation.settings.share.ShareScreen

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val navController = rememberNavController()

            LotteryTheme {
                val systemUiController = rememberSystemUiController()
                val darkIcons = MaterialTheme.colors.isLight
                SideEffect {
                    systemUiController.setStatusBarColor(
                        Color.Transparent,
                        darkIcons = darkIcons
                    )
                }

                NavHost(
                    navController = navController,
                    startDestination = SettingsRoute.Menu.route
                ) {
                    composable(route = SettingsRoute.Menu.route) {
                        SettingsScreen(
                            onSettingClick = {
                                onSettingClick(
                                    navController = navController,
                                    id = it
                                )
                            },
                            onBackClick = { onBackPressed() }
                        )
                    }
                    composable(route = SettingsRoute.Share.route) {
                        ShareScreen(
                            navController = navController
                        )
                    }
                    composable(route = SettingsRoute.ManageTickets.route) {
                        ManageTicketsScreen(
                            navController = navController
                        )
                    }
                }
            }
        }
    }

    private fun onSettingClick(
        navController: NavController,
        id: SettingItemId
    ): Unit = when (id) {
        SettingItemId.PRIVACY -> openWebViewScreen(getString(R.string.about_screen_url_privacy))
        SettingItemId.TERMS -> openWebViewScreen(getString(R.string.about_screen_url_terms))
        SettingItemId.EMAIL_ME -> {
            val success = sendEmail(
                listOf(getString(R.string.about_screen_contact_email)).toTypedArray(),
                "${getString(R.string.app_name)} v${BuildConfig.VERSION_NAME}",
                ""
            )
            if (success.not()) {
                showEmailInfo()
            } else {
                Unit
            }
        }
        SettingItemId.SHARE -> navController.navigate(SettingsRoute.Share.route)
        SettingItemId.MANAGE_TICKETS -> navController.navigate(SettingsRoute.ManageTickets.route)
    }

    private fun showEmailInfo() {
        val alertDialog: AlertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle(getString(R.string.about_screen_item_email_me))
        alertDialog.setMessage("${getString(R.string.about_screen_item_email_me_text)} ${getString(R.string.about_screen_contact_email)}")
        alertDialog.setButton(
            AlertDialog.BUTTON_NEUTRAL,
            getString(R.string.about_screen_item_email_me_action)
        ) { dialog, _ -> dialog.dismiss() }
        alertDialog.show()
    }

    private fun openWebViewScreen(url: String) {
        loadCustomTabs(Uri.parse(url))
    }

    companion object {
        fun open(context: Context) {
            val intent = Intent(context, SettingsActivity::class.java)
            context.startActivity(intent)
        }
    }
}
