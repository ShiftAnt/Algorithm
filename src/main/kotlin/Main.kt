import java.io.*
import kotlin.math.*

const val INF = Long.MAX_VALUE / 2
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	val (n, m) = br.readLine().split(" ").map { it.toInt() }

	val al = Array(m) { intArrayOf() }

	repeat(m) {
		al[it] = br.readLine().split(" ").mapIndexed {index, s ->
			if (index < 2) s.toInt() - 1 else s.toInt()
		}.toIntArray()
	}

	val P = LongArray(n)
	P.fill(INF)
	P[0] = 0

	for (i in 0 until n) {
		for (cur in al) {
			if (P[cur[0]] != INF) {
				P[cur[1]] = min(P[cur[1]], P[cur[0]] + cur[2])
			}
		}
	}
	var isINF = false
	for (cur in al) {
		if (P[cur[0]] != INF && P[cur[1]] > P[cur[0]] + cur[2]) {
			isINF = true
			break
		}
	}
	if (isINF) {
		bw.write("-1\n")
	} else {
		for (i in 1 until n) {
			bw.write("${if (P[i] == INF) -1 else P[i]}\n")
		}
	}

	bw.close()
	br.close()
}