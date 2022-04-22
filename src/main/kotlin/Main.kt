import java.io.*
import kotlin.math.*

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val (d, n) = br.readLine().split(" ").map { it.toInt() }
	val oven = br.readLine().split(" ").map { it.toInt() }.toIntArray()
	val pz = br.readLine().split(" ").map { it.toInt() }
	var idx = 0

	for (i in 1 until d) {
		oven[i] = min(oven[i - 1], oven[i])
	}
	for (i in d - 1 downTo 0) {
		if (pz[idx] <= oven[i]) {
			if (idx == n - 1) {
				println(i + 1)
				return
			}
			++idx
		}
	}
	println(0)
}