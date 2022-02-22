import java.io.*
import kotlin.math.*

const val MX = 1000001
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	val (n, k) = br.readLine().split(" ").map { it.toInt() }

	val vstd = Array(MX) {BooleanArray(11)}
	val que = ArrayDeque<IntArray>()

	var ret = -1

	que.add(intArrayOf(n, k))
	vstd[n][k] = true
	while (!que.isEmpty()) {
		val a = que.first()[0]
		val b = que.removeFirst()[1]
		if (b == 0) {
			ret = max(ret, a)
			continue
		}
		val cur = StringBuilder(a.toString())
		for (i in cur.indices) {
			for (j in i + 1 until cur.length) {
				if (i == 0 && cur[j] == '0') continue
				cur[i] = cur[j].also { cur[j] = cur[i] }
				val nxt = cur.toString().toInt()
				if (!vstd[nxt][b - 1]) {
					que.add(intArrayOf(nxt, b - 1))
					vstd[nxt][b - 1] = true
				}
				cur[i] = cur[j].also { cur[j] = cur[i] }
			}
		}
	}
	bw.write("$ret\n")
	bw.close()
	br.close()
}