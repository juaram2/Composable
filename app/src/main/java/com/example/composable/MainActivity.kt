package com.example.composable

import CloudHospitalApi.models.MarketingType
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.composable.ui.theme.ComposableTheme
import com.example.composable.utils.PrefUtil
import com.example.composable.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PrefUtil.init(this)

        setContent {
            ComposableTheme {
                MainContent()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposableTheme {
        MainContent()
    }
}