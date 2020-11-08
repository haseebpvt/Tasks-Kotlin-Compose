import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.TextUnit

fun main() = Window {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText()
    }
}

@Composable
fun CustomText() {
    val text = remember { mutableStateOf("00:00:00") }
    val currentColor = remember { mutableStateOf(Color.Red) }

    Column {
        Text(
            text.value, modifier = Modifier,
            fontSize = TextUnit.Companion.Sp(32),
            fontWeight = FontWeight.Bold
        )

        Button(onClick = {
            if (currentColor.value == Color.Red) {
                currentColor.value = Color.Blue
            } else {
                currentColor.value = Color.Red
            }
        }) {
            Text("Hello")
        }
    }


    Crossfade(current = currentColor.value, animation = tween(3000)) {
        it.printName()
        Box(modifier = Modifier.fillMaxSize().background(it))
    }

    Thread {
        time().forEach { text.value = it }
    }.start()
}

fun Color.printName() {
    if (this == Color.Red) println("RED")
    if (this == Color.Blue) println("Blue")
    else println("OTHER")
}