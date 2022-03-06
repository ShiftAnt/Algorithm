import java.util.Scanner
const val MOD = 1000000003
fun main() {
	val sc = Scanner(System.`in`)
	val n = sc.nextInt()
	val k = sc.nextInt()
	if (n / 2 < k) {
		println(0)
		return
	}
	val dp = Array(n + 1) {IntArray(k + 1) }

	for (i in 2..n) {
		for (j in 1..k) {
			dp[i][j] = if (j == 1) i else (dp[i - 2][j - 1] + dp[i - 1][j]) % MOD
		}
	}
	println(dp[n][k])
}