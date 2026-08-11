package com.talp.smartteacher

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.talp.smartteacher.navigation.TalpNavigation
import com.talp.smartteacher.ui.theme.TalpSmartTeacherTheme
import com.talp.smartteacher.utils.NotificationHelper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        // Request notification permission for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                // Permission handling
            }.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }

        // Initialize notification channels
        NotificationHelper.createNotificationChannels(this)

        setContent {
            TalpSmartTeacherTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TalpNavigation()
                }
            }
        }
    }
}
