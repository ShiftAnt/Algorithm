import java.io.*
import kotlin.math.*

const val INF = Int.MAX_VALUE / 2
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	val (v, e) = br.readLine().split(" ").map { it.toInt() }

	val P = Array(v) {Array(v) {INF} }
	repeat(v) {
		P[it][it] = 0
	}
	repeat(e) {
		br.readLine().split(" ").map { it.toInt() - 1 }.let {
			P[it[0]][it[1]] = it[2] + 1
		}
	}

	for (i in 0 until v) {
		for (j in 0 until v) {
			for (m in 0 until v) {
				P[i][j] = min(P[i][j], P[i][m] + P[m][j])
			}
		}
	}
	var ret = INF

	for (i in 0 until v) {
		for (j in 0 until v) {
			if (i != j) ret = min(ret, P[i][j] + P[j][i])
		}
	}
	bw.write("${if (ret == INF) -1 else ret}")

	bw.close()
	br.close()
}