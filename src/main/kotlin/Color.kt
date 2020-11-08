import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.desktop.Window
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Window
import androidx.compose.ui.unit.dp


enum class MyColors(val color: Color) {
    Red(Color.Red), Green(Color.Green), Blue(Color.Blue)
}

fun main() = Window {
    var currentColor by remember { mutableStateOf(MyColors.Red) }
    Column {
        Row {
            MyColors.values().forEach { myColors ->
                Button(
                    onClick = { currentColor = myColors },
                    Modifier.weight(1f, true)
                        .preferredHeight(48.dp), colors = ButtonConstants.defaultButtonColors(
                        backgroundColor = myColors.color
                    )
                ) {
                    Text(myColors.name)
                }
            }
        }
        Crossfade(current = currentColor, animation = tween(1000)) { selectedColor ->
            Box(modifier = Modifier.fillMaxSize().background(selectedColor.color))
        }
    }
}