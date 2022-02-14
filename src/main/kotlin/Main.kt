import java.util.*
import kotlin.math.*

fun main() {
	val sc = Scanner(System.`in`)
	val n = sc.nextInt()
	val P = IntArray(n)

	repeat(n) {
		P[it] = sc.nextInt()
	}

	val dp = Array(n) {IntArray(2)}
	dp[0][0] = 1

	for (i in 1 until n) {
		dp[i][0] = 1
		for (j in i - 1 downTo 0) {
			if (P[i] < P[j]) dp[i][0] = max(dp[i][0], dp[j][0] + 1)
		}
	}

	for (i in n - 2 downTo 0) {
		for (j in i + 1 until n) {
			if (P[i] < P[j]) dp[i][1] = max(dp[i][1], dp[j][1] + 1)
		}
	}
	var ret = 0

	for (i in 0 until n) {
		ret = max(ret, dp[i][0] + dp[i][1])
	}
	print("$ret\n")
}