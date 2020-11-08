package widgets

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class CustomButton {

    companion object {
        @Composable
        fun AddButton() {
            Button(modifier = Modifier,
                onClick = {

            }) {
                Text("Add")
            }
        }
    }
}