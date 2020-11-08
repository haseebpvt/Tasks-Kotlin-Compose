import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageAsset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import widgets.CustomDialog
import widgets.CustomItem
import java.awt.Container
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun main() = Window(
    size = IntSize(400, 600),
    title = "Shopping List"
) {
    val image = remember { imageFromFile(File("D:\\capture.PNG")) }
    val showDialog = remember { mutableStateOf(false) }
    val dialogText = remember { mutableStateOf(TextFieldValue("")) }
    val time = remember { mutableStateOf("00:00:00") }
    val darkMode = remember { mutableStateOf(false) }
    val listOfTask = remember { mutableStateOf(mutableListOf("Hello", "Hey")) }

    val appBackgroundColor = remember { mutableStateOf(Color.Red) }
    val titleTextColor = remember { mutableStateOf(Color.Black) }

    if (darkMode.value) { // Night mode
        titleTextColor.value = Color.Red
        appBackgroundColor.value = Color.Black
    } else { // Light mode
        titleTextColor.value = Color.Black
        appBackgroundColor.value = Color.White
    }

    Thread {
        time().forEach { time.value = it }
    }.start()

    MaterialTheme {
//        Image(asset = image, modifier = Modifier)

        Crossfade(current = Color.White, animation = tween(3000)) {
            Box(modifier = Modifier.fillMaxSize().background(it))
        }


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "TASK TODAY", modifier = Modifier
                    .padding(top = 50.dp),
                fontWeight = FontWeight.ExtraBold,
                fontSize = TextUnit.Sp(32),
                textAlign = TextAlign.Center,
                color = titleTextColor.value
            )
            val grey = 220
            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(grey, grey, grey))
            ) {
                Row(modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)) {
                    Text(getTodaysDate(), modifier = Modifier.padding(end = 4.dp), color = Color.Black)
                    Text("·", fontWeight = FontWeight.ExtraBold, color = Color.Black)
                    Text(time.value, modifier = Modifier.padding(start = 4.dp), color = Color.Black)
                }
            }
            Column(modifier = Modifier.padding(start = 24.dp, top = 32.dp)) {
                CustomItem.customItem(listOfTask.value)
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp),
                text = { Text("Add task") },
                icon = { Icon(Icons.Filled.Add) },
                onClick = {
                    showDialog.value = true
                },
                contentColor = Color.White,
                backgroundColor = Color.Black
            )
            FloatingActionButton(
                onClick = {
                    println(darkMode.value)
                    darkMode.value = !darkMode.value
                },
                icon = { Icon(Icons.Filled.Star) },
                modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)
            )

            // Alert dialog
            if (showDialog.value) {
                Crossfade(current = 100, animation = tween(500)) {
                    CustomDialog(showDialog, dialogText, listOfTask)
                }
            }
        }
    }
}

fun getTodaysDate(): String {
    return SimpleDateFormat("E M, y", Locale.US).format(Date())
}

fun imageFromFile(file: File): ImageAsset {
    return org.jetbrains.skija.Image.makeFromEncoded(file.readBytes()).asImageAsset()
}