import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
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
    title = "Task"
) {
    val image = remember { imageFromFile(File("D:\\capture.PNG")) }
    val showDialog = remember { mutableStateOf(false) }
    val dialogText = remember { mutableStateOf(TextFieldValue("")) }
    val time = remember { mutableStateOf("00:00:00") }
    val darkMode = remember { mutableStateOf(false) }
    val listOfTask = remember { mutableStateOf(mutableListOf<String>()) }
    var darkModeText = remember { mutableStateOf("D") }
    val darkModeButtonBackgroundColor = remember { mutableStateOf(Color.Black) }
    val darkModeButtonIconColor = remember { mutableStateOf(Color.Black) }

    val appBackgroundColor = remember { mutableStateOf(Color.White) }
    val titleTextColor = remember { mutableStateOf(Color.Black) }
    val dateAndTimeBackgroundColor = remember { mutableStateOf(Color.Black) }
    val dateAndTimeTextColor = remember { mutableStateOf(Color.Black) }
    val fabBackgroundColor = remember { mutableStateOf(Color.Black) }
    val fabTextColor = remember { mutableStateOf(Color.Black) }
    val taskItemTextColor = remember { mutableStateOf(Color.Black) }

    Thread {
        time().forEach { time.value = it }
    }.start()

    MaterialTheme {
//        Image(asset = image, modifier = Modifier)

        if (!darkMode.value) {
            Crossfade(current = 0, animation = tween(1000)) {
                Box(modifier = Modifier.fillMaxSize().background(Color.White))
            }

            titleTextColor.value = black()
            dateAndTimeBackgroundColor.value = white()
            dateAndTimeTextColor.value = black()
            fabBackgroundColor.value = black()
            fabTextColor.value = white()
            taskItemTextColor.value = black()
            darkModeButtonBackgroundColor.value = black()
            darkModeButtonIconColor.value = white()
        }

        if (darkMode.value) {
            Crossfade(current = 100, animation = tween(1000)) {
                Box(modifier = Modifier.fillMaxSize().background("#181818".toColor()))
            }

            titleTextColor.value = white()
            dateAndTimeBackgroundColor.value = "#000000".toColor()
            dateAndTimeTextColor.value = white()
            fabBackgroundColor.value = white()
            fabTextColor.value = black()
            taskItemTextColor.value = white()
            darkModeButtonBackgroundColor.value = white()
            darkModeButtonIconColor.value = black()
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
                    .background(dateAndTimeBackgroundColor.value)
            ) {
                Row(modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)) {
                    Text(getTodaysDate(), modifier = Modifier.padding(end = 4.dp), color = dateAndTimeTextColor.value)
                    Text("·", fontWeight = FontWeight.ExtraBold, color = dateAndTimeTextColor.value)
                    Text(time.value, modifier = Modifier.padding(start = 4.dp), color = dateAndTimeTextColor.value)
                }
            }
            Column(modifier = Modifier.padding(top = 32.dp)) {
                CustomItem.customItem(listOfTask.value, taskItemTextColor)
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
                contentColor = fabTextColor.value,
                backgroundColor = fabBackgroundColor.value
            )
//            FloatingActionButton(
//                onClick = {
//                    println(darkMode.value)
//                },
//                icon = { Icon(Icons.Filled.Star) },
//                modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)
//            )

            Box(modifier = Modifier.padding(16.dp)) {
                Box(
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                        .background(shape = CircleShape, color = darkModeButtonBackgroundColor.value)
                        .clickable {
                            darkMode.value = !darkMode.value
                            if (darkMode.value) {
                                darkModeText.value = "N"
                            } else {
                                darkModeText.value = "D"
                            }
                        }
                ) {

                    Icon(
                        Icons.Default.Edit, modifier = Modifier
                            .align(Alignment.Center)
                            .width(12.dp)
                            .height(12.dp),
                        tint = darkModeButtonIconColor.value
                    )
                }
            }


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

fun String.toColor(): Color {
    val color = java.awt.Color.decode(this)
    return Color(color.red, color.green, color.blue)
}

fun white(): Color {
    return "#F2F2F2".toColor()
}

fun black(): Color {
    return "#181818".toColor()
}