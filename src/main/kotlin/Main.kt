import java.util.Stack

const val NULL = -2
class Solution {
	fun solution(n: Int, k: Int, cmd: Array<String>): String {
		val P = Array(n) {IntArray(2)}
		repeat(n) {
			P[it][0] = it - 1
			P[it][1] = it + 1
		}
		val ret = BooleanArray(n)
		ret.fill(true)
		P[0][0] = NULL
		P[n - 1][1] = NULL
		var cur = k
		val stack = Stack<Int>()
		for (c in cmd) {
			c.split(" ").let {

				when (it[0]) {
					"U" -> {
						repeat(it[1].toInt()) {
							cur = P[cur][0]
						}
					}
					"D" -> {
						repeat(it[1].toInt()) {
							cur = P[cur][1]
						}
					}
					"C" -> {
						stack.push(cur)
						val prv = P[cur][0]
						val nxt = P[cur][1]
						if (prv != NULL) {
							P[prv][1] = P[cur][1]
						}
						if (nxt != NULL) {
							P[nxt][0] = P[cur][0]
						}
						ret[cur] = false
						cur = P[cur][1].let { if (it == NULL) P[cur][0] else it }
					}
					"Z" -> {
						val tar = stack.pop()
						val prv = P[tar][0]
						val nxt = P[tar][1]
						if (prv != NULL) P[prv][1] = tar
						if (nxt != NULL) P[nxt][0] = tar
						ret[tar] = true
					}
				}
			}
		}
		return StringBuilder().also {
			for (i in 0 until n) {
				it.append(if (ret[i]) "O" else "X")
			}
		}.toString()
	}
}