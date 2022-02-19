import java.io.*
import kotlin.math.*

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	for (tc in 0 until br.readLine().toInt()) {
		val n = br.readLine().toInt()
		val P = br.readLine().split(" ").map { it.toInt() }.toIntArray()
		val dp = Array(n) {IntArray(n)}
		for (i in 1 until n) {
			P[i] += P[i - 1]
		}
		repeat(n) {
			dp[it].fill(Int.MAX_VALUE)
			dp[it][it] = 0
		}
		for (i in 2..n) {
			for (j in 0 until n - i + 1) {
				for (m in j until j + i - 1) {
					dp[j][j + i - 1] = min(dp[j][j + i - 1],
						dp[j][m] + dp[m + 1][j + i - 1] + P[j + i - 1] - if (j != 0) P[j - 1] else 0)
				}
			}
		}
		bw.write("${dp[0][n - 1]}\n")

	}

	bw.close()
	br.close()
}