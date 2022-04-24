import java.io.*

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val (m, n, l) = br.readLine().split(" ").map { it.toInt() }
    val ts = java.util.TreeSet<Int>()
	br.readLine().split(" ").map { it.toInt() }.forEach{ ts.add(it) }

	var ret = 0
	for (tc in 0 until n) {
		val (a, b) = br.readLine().split(" ").map { it.toInt() }
		if (b > l) continue
		val k = l - b
		if ((ts.ceiling(a - k) ?: Int.MAX_VALUE) <= (ts.floor(a + k) ?: 0)) ++ret
	}
	println(ret)
}