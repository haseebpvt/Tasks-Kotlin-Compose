import java.text.SimpleDateFormat
import java.util.*

fun time(): Sequence<String> = sequence {
    while (true) {
        Thread.sleep(1000)
        SimpleDateFormat("hh:mm:ss").format(Date()).also { yield(it) }
    }
}

fun main() {
    time().forEach { println(it) }
}