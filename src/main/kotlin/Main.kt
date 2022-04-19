var n = 0
var m = 0
var center = 0
var P = arrayOf<IntArray>()
var route = ArrayList<Pair<Int, Int>>()
var ret = 0
fun move(d: Int, s: Int) {
	for (i in 1..s) {
		val y = center + dr[d] * i
		val x = center + dc[d] * i
		if (y !in 0 until n || x !in 0 until n) break
		P[y][x] = 0
	}
	pull()
	while (del()) {
		pull()
	}
	ext()
}
fun delMarble(y: Int, x: Int) {
	ret += P[y][x]
	P[y][x] = 0
}
val dr = arrayOf(-1, 1, 0, 0)
val dc = arrayOf(0, 0, -1, 1)
val di = arrayOf(3, 1, 2, 0)
fun initRoute() {
	val vstd = Array(n) {BooleanArray(n)}

	var d = 0
	var y = 0
	var x = 0

	while (y != center || x != center) {
		route += y to x
		vstd[y][x] = true
		val ny = y + dr[di[d]]
		val nx = x + dc[di[d]]
		if (ny !in 0 until n || nx !in 0 until n || vstd[ny][nx]) d = (d + 1) % 4
		y += dr[di[d]]
		x += dc[di[d]]
	}
	route.reverse()
}
fun del(): Boolean {
	var y = route[0].first
	var x = route[0].second
	var cur = P[y][x]
	var num = 1
	var isDel = false
	for (i in 1 until route.size) {
		y = route[i].first
		x = route[i].second
		if (cur == P[y][x]) ++num
		else {
			if (delNums(i, num)) isDel = true
			cur = P[y][x]
			num = 1
		}
		if (cur == 0) break
	}
	if (cur != 0) if (delNums(route.size, num)) isDel = true
	return isDel
}
fun delNums(idx: Int, num: Int): Boolean {
	if (num < 4) return false
	for (i in idx - num until idx) {
		delMarble(route[i].first, route[i].second)
	}
	return true
}
fun ext() {
	var y = route[0].first
	var x = route[0].second
	var cur = P[y][x]
	if (cur == 0) return
	var num = 1
	val nums = ArrayList<Int>()
	for (i in 1 until route.size) {
		y = route[i].first
		x = route[i].second
		if (cur == P[y][x]) ++num
		else {
			nums += num
			nums += cur
			cur = P[y][x]
			num = 1
		}
		if (P[y][x] == 0) break
	}
	if (cur != 0) {
		nums += num
		nums += cur
	}
	for (i in route.indices) {
		P[route[i].first][route[i].second] = if (i < nums.size) nums[i] else 0
	}
}

fun pull() {
	val nums = ArrayList<Int>()

	for (r in route) {
		if (P[r.first][r.second] != 0) nums += P[r.first][r.second]
		P[r.first][r.second] = 0
	}
	for (i in nums.indices) {
		P[route[i].first][route[i].second] = nums[i]
	}
}

fun main() {
	readLine()!!.split(" ").map { it.toInt() }.let {
		n = it[0]
		m = it[1]
	}
	center = n / 2
	P = Array(n) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }

	initRoute()
	repeat(m) {
		readLine()!!.split(" ").map { it.toInt() }.let {
			move(it[0] - 1, it[1])
		}
	}
	println(ret)
}