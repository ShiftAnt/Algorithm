import kotlin.math.*

fun main() {
	val (n, m) = readLine()!!.split(" ").map { it.toInt() }

	val P = Array(n) { readLine()!! }

	for (siz in min(n, m) downTo 1) {
		for (i in 0..n - siz) {
			for (j in 0..m - siz) {
				if (P[i][j] == P[i][j + siz - 1] && P[i][j] == P[i + siz - 1][j] && P[i][j] == P[i + siz - 1][j + siz - 1]) {
					println(siz * siz)
					return
				}
			}
		}
	}
}