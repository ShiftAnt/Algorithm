import java.io.*

var n = 0
var m = 0
var P = arrayOf<IntArray>()
var al = arrayOf<Array<ArrayList<Pair<Int, Int>>>>()
val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)
var ret = 1
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	br.readLine().split(" ").map { it.toInt() }.let {
		n = it[0]
		m = it[1]
	}
	P = Array(n) { IntArray(n) }
	al = Array(n) {Array(n) { ArrayList() } }
	P[0][0] = 2
	repeat(m) {
		br.readLine().split(" ").map { it.toInt() - 1 }.let {
			al[it[0]][it[1]] += it[2] to it[3]
		}
	}
	val que = ArrayDeque<Pair<Int, Int>>()
	que += 0 to 0

	while (!que.isEmpty()) {
		val cur = que.removeFirst()

		for (light in al[cur.first][cur.second]) {
			if (P[light.first][light.second] == 0) {
				++ret
				P[light.first][light.second] = 1
				for (i in dr.indices) {
					val y = light.first + dr[i]
					val x = light.second + dc[i]
					if (y in 0 until n && x in 0 until n && P[y][x] == 2) {
						que.add(light)
						P[light.first][light.second] = 2
						break
					}
				}
			}
		}

		for (i in dr.indices) {
			val y = cur.first + dr[i]
			val x = cur.second + dc[i]
			if (y in 0 until n && x in 0 until n && P[y][x] == 1) {
				P[y][x] = 2
				que.add(y to x)
			}
		}
	}

	bw.write("$ret")
	bw.close()
	br.close()
}