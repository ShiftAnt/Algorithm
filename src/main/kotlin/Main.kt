import java.io.*
var n = 0
var m = 0
var P = arrayOf<IntArray>()

fun djs(a: Int, set: Int = -1): Int {
	var cur = a

	while (cur != P[cur][0]) {
		cur = P[cur][0].also { if (set != -1) P[cur][0] = set}
	}
	if (set != -1) P[cur][0] = set
	return cur
}

fun merge(a: Int, b: Int) {
	val da = djs(a)
	val db = djs(b)
	val set = if (P[da][1] < P[db][1]) db else da
	djs(a, set)
	djs(b, set)
}
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))
	br.readLine().split(" ").map { it.toInt() }.let {
		n = it[0]; m = it[1]
	}
	P = Array(n + 1) { IntArray(2) }
	repeat(n + 1) {
		P[it][0] = it
		P[it][1] = 1
	}
	repeat(m) {
		val it = br.readLine().split(" ").map { it.toInt() }
		if (it[0] == 0) merge(it[1], it[2])
		else bw.write(if (djs(it[1]) == djs(it[2])) "YES\n" else "NO\n")
	}
	bw.flush()
}