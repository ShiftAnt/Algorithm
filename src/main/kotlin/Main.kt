var n = 0
var m = 0
var k = 0
var P = arrayOf<IntArray>()
var dir = 0
val dr = arrayOf(0, 1, 0, -1)
val dc = arrayOf(1, 0, -1, 0)
val dice = IntArray(6) {it + 1}
val lr = intArrayOf(0, 2, 5, 3)
val ud = intArrayOf(0, 4, 5, 1)
val cur = intArrayOf(0, 0)
var dp = arrayOf<IntArray>()
var ret = 0
fun move() {
	val ny = cur[0] + dr[dir]
	val nx = cur[1] + dc[dir]
	if (ny !in 0 until n || nx !in 0 until m) dir = (dir + 2) % 4
	cur[0] += dr[dir]
	cur[1] += dc[dir]

	when (dir) {
		0 -> moveDice(lr)
		1 -> moveDice(ud)
		2 -> moveDice(lr, true)
		3 -> moveDice(ud, true)
	}
	val sub = dice[5] - P[cur[0]][cur[1]]

	if (sub > 0) {
		dir = (dir + 1) % 4
	}
	else if (sub < 0) {
		dir = (dir + 3) % 4
	}
	ret += dp[cur[0]][cur[1]]
}


fun moveDice(type: IntArray, isR: Boolean = false) {
	val ts = type.size
	val tmp = ArrayDeque<Int>()
	repeat(ts) {tmp.addLast(dice[type[it]])}
	if (!isR) tmp.addFirst(tmp.removeLast())
	else tmp.addLast(tmp.removeFirst())

	for (i in 0 until ts) {
		dice[type[i]] = tmp[i]
	}
}
fun bfs(y: Int, x: Int) {
	dp[y][x] = 0
	val que = ArrayList<Pair<Int, Int>>()
	que += y to x
	var idx = -1

	while (++idx < que.size) {
		val cur = que[idx]

		for (i in dr.indices) {
			val a = cur.first + dr[i]
			val b = cur.second + dc[i]
			if (a in 0 until n && b in 0 until m && dp[a][b] == -1 && P[a][b] == P[y][x]) {
				dp[a][b] = 0
				que += a to b
			}
		}
	}
	val siz = que.size
	for (ele in que) {
		dp[ele.first][ele.second] = P[y][x] * siz
	}
}

fun initScore() {
	dp = Array(n) {IntArray(m) {-1} }

	for (i in 0 until n) {
		for (j in 0 until m) {
			if (dp[i][j] == -1) bfs(i, j)
		}
	}
}

fun main() {
	readLine()!!.split(" ").map { it.toInt() }.let {
		n = it[0]
		m = it[1]
		k = it[2]
	}
	P = Array(n) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }
	initScore()
	repeat(k) {
		move()
	}
	println(ret)
}