package widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

data class TaskItem(var checked: Boolean, var title: String)

class CustomItem {
    companion object {

        @Composable
        fun customItem(list: MutableList<String>) {
            list.forEach {
                item(it)
            }
        }

        fun getFakeTasks(): List<TaskItem> {
            return listOf(
                TaskItem(false, "Hello"),
                TaskItem(false, "Hey"),
                TaskItem(false, "Howdy"),
            )
        }

        @Composable
        private fun item(text: String) {
            val checked = remember { mutableStateOf(false) }
            Column {
                Row(modifier = Modifier.padding(top = 8.dp)) {
                    Checkbox(checked.value, onCheckedChange = {
                        checked.value = it
                    }, colors = CheckboxConstants.defaultColors(checkedColor = Color.Black))
                    if (checked.value) {
                        Text(
                            text,
                            modifier = Modifier.padding(start = 8.dp),
                            textDecoration = TextDecoration.LineThrough,
                            color = Color(128, 128, 128)
                        )
                    } else {
                        Text(text, modifier = Modifier.padding(start = 8.dp))
                    }
                }
                Divider(modifier = Modifier.padding(top = 8.dp, end = 16.dp))
            }
        }
    }
}