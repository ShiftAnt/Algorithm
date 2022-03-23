import java.io.*
import kotlin.math.*

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val (n, c) = br.readLine().split(" ").map { it.toInt() }
	val m = br.readLine().toInt()
	val P = IntArray(n) {c}
	var ret = 0
	Array(m) {IntArray(3)}.also {
		repeat(m) { i ->
			it[i] = br.readLine().split(" ").map { it.toInt() - 1 }.toIntArray()
			it[i][2] += 1
		}
	}.sortedWith(compareBy({it[1]}, {it[0]})).forEach{
		var mi = P[it[0]]
		for (i in it[0] until it[1]) {
			mi = min(P[i], mi)
		}
		val cur = min(mi, it[2])
		ret += cur
		for (i in it[0] until it[1]) {
			P[i] -= cur
		}
	}
	println(ret)
}