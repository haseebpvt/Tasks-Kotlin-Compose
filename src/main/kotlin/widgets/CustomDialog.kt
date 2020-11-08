package widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun CustomDialog(showDialog: MutableState<Boolean>, text: MutableState<TextFieldValue>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0f, 0f, 0f, alpha = .4f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(.7f)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .align(Alignment.Center)
                .padding(16.dp),
        ) {
            Column {
                Text(
                    "Add Task", modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    fontSize = TextUnit.Companion.Sp(18),
                    fontWeight = FontWeight.Bold
                )
                TextField(
                    value = TextFieldValue(""),
                    onValueChange = {
                        text.value = it
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp),
                )

                Button(onClick = {
                    showDialog.value = false
                }, modifier = Modifier.padding(top = 8.dp).fillMaxWidth()) {
                    Text("Add")
                }
            }
        }
    }
}