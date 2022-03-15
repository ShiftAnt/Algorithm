import java.io.*

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	val n = br.readLine().toInt()
	val P = Array(n) {IntArray(n)}

	repeat(br.readLine().toInt()) {
		br.readLine().split(" ").map { it.toInt() - 1 }.let {
			P[it[0]][it[1]] = 1
			P[it[1]][it[0]] = -1
		}
	}
	for (k in 0 until n) {
		for (i in 0 until n) {
			for (j in 0 until n) {
				if (P[i][k] != 0 && P[i][k] == P[k][j]) P[i][j] = P[i][k]
			}
		}
	}
	repeat(n) {
		bw.write("${P[it].count { it == 0 } - 1}\n")
	}
	bw.flush()
}