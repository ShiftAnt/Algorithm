import java.io.*
import kotlin.math.*

fun dis(a: IntArray, b: IntArray) = abs(a[0] - b[0]) + abs(a[1] - b[1])
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, k) = br.readLine().split(" ").map { it.toInt() }

    val P = Array(n) {IntArray(2)}

    val dp = Array(n) {IntArray(k + 1)}

    for (i in 0 until n) {
        for (j in 0 until k + 1) {
            dp[i][j] = Int.MAX_VALUE
        }
    }

    repeat(n) {
        P[it] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }
    dp[0][0] = 0
    dp[1][0] = dis(P[0], P[1])
    for (i in 2 until n) {
        for (j in 0 until i) {
            val cur = dis(P[i], P[j])
            for (y in 0 until k + 1) {
                if (dp[j][y] == Int.MAX_VALUE) break
                if (i - j - 1 + y <= k) {
                    dp[i][i - j - 1 + y] = min(dp[i][i - j - 1 + y], dp[j][y] + cur)
                } else break
            }
        }
    }
    bw.write("${dp[n - 1].minOf { it }}")

    bw.close()
    br.close()
}