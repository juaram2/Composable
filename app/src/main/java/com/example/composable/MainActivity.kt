package com.example.composable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composable.ui.theme.ComposableTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposableTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        modifier = Modifier
                            .height(180.dp)
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(14.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.height(16.dp))

                    Surface(color = MaterialTheme.colors.background) {
                        Greeting("Android")

                    }

                    Text("A day wandering through the sandhills " +
                        "in Shark Fin Cove, and a few of the " +
                                "sights I saw",
                        style = typography.h6)
//                        maxLines = 2,
//                        overflow = TextOverflow.Ellipsis)

                    Row() {
                        Image(
                            painter = painterResource(R.drawable.ic_launcher_foreground),
                            contentDescription = null,
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp),
                            contentScale = ContentScale.Crop
                        )
                        Text("Davenport, California", color = Color.Gray)
                    }

                    Row() {
                        Image(
                            painter = painterResource(R.drawable.ic_launcher_foreground),
                            contentDescription = null,
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp),
                            contentScale = ContentScale.Crop
                        )
                        Text("December 2018", color = Color.Gray)
                    }

                    Spacer(Modifier.height(16.dp))
                    Divider(color = Color.LightGray)
                    Spacer(Modifier.height(16.dp))

                    Text("A day wandering through the sandhills " +
                            "in Shark Fin Cove, and a few of the " +
                            "A day wandering through the sandhills " +
                            "in Shark Fin Cove, and a few of the " +
                            "A day wandering through the sandhills " +
                            "in Shark Fin Cove, and a few of the "
                    )

                    Spacer(Modifier.height(16.dp))

                    Text("A day wandering through the sandhills " +
                            "in Shark Fin Cove, and a few of the " +
                            "A day wandering through the sandhills " +
                            "in Shark Fin Cove, and a few of the " +
                            "A day wandering through the sandhills " +
                            "in Shark Fin Cove, and a few of the " +
                            "A day wandering through the sandhills in Shark Fin Cove, and a few of the in"
                    )

                    Spacer(Modifier.height(16.dp))

                    Text("sights I saw A day wandering through the sandhills A day wandering through the sandhills A day wandering through the sandhills A day wandering through the sandhills " +
                            "in Shark Fin Cove, and a few of the sights I saw A day wandering through the sandhills in Shark Fin Cove, and a few of the sights I saw A day wandering through the sandhills in Shark Fin Cove, and a few of the sights I saw A day wandering through the sandhills in Shark Fin Cove, and a few of the sights I saw A day wandering through the sandhills"
                    )

                }
            }

        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", style = typography.body2, color = Color.Gray)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposableTheme {
        Greeting("Android")
    }
}