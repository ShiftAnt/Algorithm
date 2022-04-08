import java.io.*
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val s = br.readLine().toInt()
	val (m, n) = br.readLine().split(" ").map { it.toInt() }
	val A = arrayOf(IntArray(m) {br.readLine().toInt()}, IntArray(n) {br.readLine().toInt()})

	val P = Array(2) {IntArray(s + 1)}
	P[0][0] = 1
	P[1][0] = 1
	repeat(2) { tc ->
		A[tc].sum().let { if (it <= s) ++P[tc][it] }
		for (i in A[tc].indices) {
			var cur = i
			val end = if (i == 0) A[tc].size - 1 else i - 1
			var num = 0
			while (cur != end) {
				num += A[tc][cur]
				if (num > s) break
				++P[tc][num]
				if (++cur == A[tc].size) cur = 0
			}
		}
	}
	var ret = 0L
	for (i in 0..s) {
		ret += P[0][i] * P[1][s - i]
	}
	println(ret)
}