import kotlin.math.*

fun dfs(n: Int, tmp: Int): Int {
	val k = if (tmp > n) n else tmp

	if (P[n][k] != Int.MAX_VALUE) return P[n][k]
	if (n == 0) return 0
	if (k == 1) return n

	for (i in 1..n) P[n][k] = min(P[n][k], max(dfs(n - i, k), dfs(i - 1, k - 1)) + 1)
	return P[n][k]
}
var P = arrayOf<IntArray>()
fun main() {
	val (n, k) = readLine()!!.split(" ").map { it.toInt() }
	P = Array(n + 1) { IntArray(it + 1) {Int.MAX_VALUE} }

	println(dfs(n, k))
}