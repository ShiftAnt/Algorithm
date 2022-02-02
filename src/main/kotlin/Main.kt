import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, m, h) = br.readLine().split(" ").map { it.toInt() }

    val dp = IntArray(h + 1)
    dp[0] = 1

    repeat(n) {
        val cur = br.readLine().split(" ").map { it.toInt() }

        for (i in h downTo 0) {
            if (dp[i] != 0) {
                for (j in cur.indices) {
                    if (i + cur[j] <= h) {
                        dp[cur[j] + i] += dp[i]
                        dp[cur[j] + i] %= 10007
                    }
                }
            }
        }
    }

    bw.write("${dp[h]}\n")

    bw.close()
    br.close()
}