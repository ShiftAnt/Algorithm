import java.io.*

class Node(
	val y: Int,
	val x: Int,
	val k: Int,
	val isNight: Int
)
val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	val (n, m, k) = br.readLine().split(" ").map { it.toInt() }

	val P = Array(n) {br.readLine()}

	val vstd = Array(n) {Array(m) {Array(k + 1) {BooleanArray(2)} } }

	val que = ArrayDeque<Node>()

	vstd[0][0][k][0] = true

	que.add(Node(0, 0, k, 0))
	var step = 0
	var ret = -1
	loop@
	while (!que.isEmpty()) {
		++step
		for (tc in que.indices) {
			val cur = que.removeFirst()
			if (cur.y == n - 1 && cur.x == m - 1) {
				ret = step
				break@loop
			}
			for (i in dr.indices) {
				val y = cur.y + dr[i]
				val x = cur.x + dc[i]

				if (y in 0 until n && x in 0 until m) {
                    if (y == n - 1 && x == m - 1) {
                        ret = step + 1
                        break@loop
                    }
					if (P[y][x] == '0' && !vstd[y][x][cur.k][1 - cur.isNight]) {
						vstd[y][x][cur.k][1 - cur.isNight] = true
						que.add(Node(y, x, cur.k, 1 - cur.isNight))
					}
					else if (cur.isNight == 0 && P[y][x] == '1' && cur.k > 0 && !vstd[y][x][cur.k - 1][1]) {
						vstd[y][x][cur.k - 1][1] = true
						que.add(Node(y, x, cur.k - 1, 1))
					}
				}
			}
			if (!vstd[cur.y][cur.x][cur.k][1 - cur.isNight]) {
				vstd[cur.y][cur.x][cur.k][1 - cur.isNight] = true
				que.add(Node(cur.y, cur.x, cur.k, 1 - cur.isNight))
			}
		}
	}
	bw.write("$ret")
	bw.flush()
}