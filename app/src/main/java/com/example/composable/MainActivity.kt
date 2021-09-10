package com.example.composable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
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

                    MessageCard(Message("Android", "Jetpack Compose"))

                    Divider(color = Color.LightGray)
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

                    Spacer(Modifier.height(16.dp))

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

                    Text("A day wandering through the sandhills " +
                            "in Shark Fin Cove, and a few of the " +
                            "A day wandering through the sandhills " +
                            "in Shark Fin Cove, and a few of the " +
                            "A day wandering through the sandhills " +
                            "in Shark Fin Cove, and a few of the "
                    )

                    Spacer(Modifier.height(16.dp))

                    Text("sights I saw A day wandering through the sandhills A day wandering through the sandhills A day wandering through the sandhills A day wandering through the sandhills " +
                            "in Shark Fin Cove, and a few of the sights I saw A day wandering through the sandhills in Shark Fin Cove, and a few of the sights I saw A day wandering through the sandhills in Shark Fin Cove, and a few of the sights I saw A day wandering through the sandhills in Shark Fin Cove, and a few of the sights I saw A day wandering through the sandhills"
                        , color = Color.DarkGray
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

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor: Color by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )

        // We toggle the isExpanded variable when we click on this Column
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = typography.subtitle2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = typography.body2
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposableTheme {
        Greeting("Android")
    }
}