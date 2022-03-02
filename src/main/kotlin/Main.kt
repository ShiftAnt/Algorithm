const val MX = 1001
fun solution(): Int {
	val (a, b, c) = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
	if (a == b && b == c) return 1
	if ((a + b + c) % 3 != 0) return 0
	val vstd = Array(MX) {BooleanArray(MX)}

	vstd[a][b] = true

	val que = ArrayDeque<IntArray>()
	que.add(listOf(a, b, c).sorted().toIntArray())
	while (!que.isEmpty()) {
		val cur = que.removeFirst()

		for (i in 0 until 3) {
			for (j in i + 1 until 3) {
				if (cur[i] < cur[  j]) {
					val nxt = intArrayOf(cur[i] * 2, cur[j] - cur[i], cur[3 - i - j])
					nxt.sort()
					if (nxt[0] == nxt[1] && nxt[1] == nxt[2]) return 1
					if (!vstd[nxt[0]][nxt[1]]) {
						vstd[nxt[0]][nxt[1]] = true
						que.add(nxt)
					}
				}
			}
		}
	}
	return 0
}
fun main() {
	println(solution())
}