import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import javax.sound.sampled.AudioSystem
import javax.swing.Box.Filler
import kotlin.coroutines.*

suspend fun playBeats(beats: String, file: String) {
    val parts = beats.split("x")
    var count = 0
    for (part in parts) {
        count += part.length + 1
        if (part == "") {
            playSound(file)
        } else {
            delay(100 * (part.length + 1L))
            if (count < beats.length) {
                playSound(file)
            }
        }
    }
}
fun playSound(file: String){
    val clip = AudioSystem.getClip()
    val audioInputSteam = AudioSystem.getAudioInputStream(
        File(file)
    )
    clip.open(audioInputSteam)
    clip.start()
}
suspend fun main(){
    runBlocking {
    launch { playBeats("x-x-x-x-x-x-", "toms.aiff") }
    playBeats("x-----x-----", "crash_cymbal.aiff")
}
}
//438