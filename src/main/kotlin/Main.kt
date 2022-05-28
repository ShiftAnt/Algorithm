import kotlin.math.*

fun main() {
	val n = readLine()!!.toInt()
	val P = IntArray(n) { readLine()!!.toInt() }
	val dp = IntArray(n) {1}
	for (i in P.size - 1 downTo 0) {
		for (j in i + 1 until  P.size) {
			if (P[i] < P[j]) {
				dp[i] = max(dp[i], dp[j] + 1)
			}
		}
	}
	println(P.size - dp.maxOf { it })
}