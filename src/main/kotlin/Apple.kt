import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

fun main() = Window {
    Box(modifier = Modifier
        .width(100.dp)
        .height(200.dp)
        .clip(RoundedCornerShape(50.dp))
        .background(Color.Red)
    )
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