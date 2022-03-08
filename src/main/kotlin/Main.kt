import java.io.*
import kotlin.math.*

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	for (tc in 1..br.readLine().toInt()) {
		val (A, B, C) = br.readLine().split(" ")
		val a = A.length
		val b = B.length
		val c = C.length
		val P = Array(a + 1) {IntArray(b + 1)}

		for (i in 0..a) {
			for (j in 0..b) {
				if (i != 0 && A[i - 1] == C[i + j - 1]) P[i][j] = max(P[i][j], P[i - 1][j] + 1)
				if (j != 0 && B[j - 1] == C[i + j - 1]) P[i][j] = max(P[i][j], P[i][j - 1] + 1)
			}
		}
		bw.write("Data set $tc: ${if (P[a][b] == c) "yes" else "no"}\n")
	}
	bw.flush()
}