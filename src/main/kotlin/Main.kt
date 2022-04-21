import kotlin.math.abs

var r = 0
var c = 0
var k = 0
val heaters = ArrayList<Triple<Int, Int, Int>>()
val chks = ArrayList<Pair<Int, Int>>()
val dr = arrayOf(0, 0, -1, 1)
val dc = arrayOf(1, -1, 0, 0)
var wall = arrayOf<Array<BooleanArray>>()
var ret = 0
var P = arrayOf<IntArray>()

fun chk(): Boolean {
	if (++ret > 100) return true
	for (b in chks) {
		if (P[b.first][b.second] < k) return false
	}
	return true
}

fun move() {
	for (i in heaters.indices) spreadHeater(i)
	spread()
	edge()
}

fun edge() {
	for (i in 0 until r) {
		for (j in 0 until c) {
			if (i == 0 || i == r - 1 || j == 0 || j == c - 1) if (P[i][j] > 0) --P[i][j]
		}
	}
}
val sr = arrayOf(
	arrayOf(-1, 0, 1),
	arrayOf(-1, 0, 1),
	arrayOf(-1, -1, -1),
	arrayOf(1, 1, 1)
)
val sc = arrayOf(
	arrayOf(1, 1, 1),
	arrayOf(-1, -1, -1),
	arrayOf(-1, 0, 1),
	arrayOf(-1, 0, 1)
)
val cw = arrayOf(
	arrayOf(
		arrayOf(2, 1),
		arrayOf(0, 1),
		arrayOf(3, 1)
	),
	arrayOf(
		arrayOf(2, 0),
		arrayOf(1, 0),
		arrayOf(3, 0),
	),
	arrayOf(
		arrayOf(1, 3),
		arrayOf(2, 3),
		arrayOf(0, 3)
	),
	arrayOf(
		arrayOf(1, 2),
		arrayOf(3, 2),
		arrayOf(0, 2)
	)

)
val heaterRoutes = ArrayList<ArrayList<Triple<Int, Int, Int>>>()
fun initHeaderRoute(idx: Int) {
	val heater = heaters[idx]
	val d = heater.third
	val y = heater.first + dr[d]
	val x = heater.second + dc[d]
	val vstd = Array(r) {BooleanArray(c)}
	val que = heaterRoutes[idx]
	que += Triple(y, x, 5)
	vstd[y][x] = true
	var tc = -1
	while (++tc < que.size) {
		val cur = que[tc]
		if (cur.third == 1) continue
		for (i in 0 until 3) {
			val a = cur.first + sr[d][i]
			val b = cur.second + sc[d][i]
			if (a !in 0 until r || b !in 0 until c || vstd[a][b]) continue
			if (wall[cur.first][cur.second][cw[d][i][0]] || wall[a][b][cw[d][i][1]]) continue
			vstd[a][b] = true
			que += Triple(a, b, cur.third - 1)
		}
	}
}
fun spread() {
	val temp = Array(r) {IntArray(c)}
	for (i in 0 until r) {
		for (j in 0 until c) {
			for (z in dr.indices) {
				val y = i + dr[z]
				val x = j + dc[z]
				if (y !in 0 until r || x !in 0 until c || wall[i][j][z]) continue
				val tmp = abs(P[i][j] - P[y][x]) / 4
				temp[i][j] += if (P[i][j] > P[y][x]) -tmp else tmp
			}
		}
	}
	for (i in 0 until r) {
		for (j in 0 until c) {
			P[i][j] += temp[i][j]
		}
	}
}

fun spreadHeater(idx: Int) {
	if (heaterRoutes[idx].isEmpty()) initHeaderRoute(idx)

	for (route in heaterRoutes[idx]) {
		P[route.first][route.second] += route.third
	}
}

fun main() {
	readLine()!!.split(" ").map { it.toInt() }.let {
		r = it[0]; c = it[1]; k = it[2]
	}
	for (i in 0 until r) {
		val st = java.util.StringTokenizer(readLine())
		for (j in 0 until c) {
			val t = st.nextToken().toInt()
			if (t in 1..4) {
				heaters += Triple(i, j, t - 1)
				heaterRoutes += ArrayList<Triple<Int, Int, Int>>()
			} else if (t == 5) {
				chks += i to j
			}
		}
	}
	P = Array(r) { IntArray(c) }
	wall = Array(r) {Array(c) { BooleanArray(4) } }
	repeat(readLine()!!.toInt()) {
		readLine()!!.split(" ").map { it.toInt() }.let {
			val y = it[0] - 1
			val x = it[1] - 1
			if (it[2] == 0) {
				wall[y][x][2] = true
				wall[y - 1][x][3] = true
			}
			else {
				wall[y][x][0] = true
				wall[y][x + 1][1] = true
			}
		}
	}
	do {
		move()
	} while (!chk())
	println(ret)
}