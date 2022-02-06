import java.io.*
import kotlin.math.max

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	val (m, n) = br.readLine().split(" ").map { it.toInt() }

	val P = IntArray(2 * m - 1)
	val ret = Array(m) {IntArray(m)}
	repeat(m) {
		ret[it].fill(1)
	}
	for (i in 0 until n) {
		var idx = 0
		val inp = br.readLine().split(" ").map { it.toInt() }
		for (j in 0 until 3) {
			for (k in 0 until inp[j]) {
				P[idx++] += j
			}
		}
	}
	for (i in 0 until m) {
		ret[m - 1 - i][0] += P[i]
	}
	for (i in m until 2 * m - 1) {
		ret[0][i - m + 1] += P[i]
	}
	for (i in 1 until m) {
		for (j in 1 until m) {
			ret[i][j] = max(max(ret[i - 1][j - 1], ret[i][j - 1]), ret[i - 1][j])
		}
	}
	for (i in 0 until m) {
		for (j in 0 until m) {
			bw.write("${ret[i][j]} ")
		}
		bw.write("\n")
	}

	bw.close()
	br.close()
}