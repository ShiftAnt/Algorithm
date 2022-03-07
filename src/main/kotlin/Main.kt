import java.io.*
import kotlin.math.*

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val n = br.readLine().toInt()
	br.readLine().split(" ").map { it.toInt() }.let {
		var a = 0
		var b = n - 1
		var ret = 0
		while (a < b) {
			ret = max((b - a - 1) * min(it[a], it[b]), ret)
			if (it[a] <= it[b]) ++a
			else --b
		}
		println(ret)
	}
}