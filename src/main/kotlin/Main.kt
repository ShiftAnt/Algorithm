import java.io.*

var n = 0
var m = 0
var P = arrayOf<IntArray>()
var clds = arrayListOf<Node>()

class Node(
	var y: Int,
	var x: Int,
)
val dr = arrayOf(0, -1, -1, -1, 0, 1, 1, 1)
val dc = arrayOf(-1, -1, 0, 1, 1, 1, 0, -1)
fun makePos(y: Int): Int {
	var ret = y
	while (ret < 0) ret += n
	return ret % n
}
fun move(d: Int, s: Int) {
	for (i in clds.indices) {
		clds[i].y += dr[d] * s
		clds[i].x += dc[d] * s
		clds[i].y = makePos(clds[i].y)
		clds[i].x = makePos(clds[i].x)
	}
	for (cld in clds) {
		++P[cld.y][cld.x]
	}
	for (cld in clds) {
		for (i in 1 until dr.size step 2) {
			val y = cld.y + dr[i]
			val x = cld.x + dc[i]
			if (y in 0 until n && x in 0 until n && P[y][x] > 0) {
				++P[cld.y][cld.x]
			}
		}
	}
	for (cld in clds) {
		P[cld.y][cld.x] = -P[cld.y][cld.x]
	}
	val newClds = ArrayList<Node>()

	for (i in 0 until n) {
		for (j in 0 until n) {
			if (P[i][j] >= 2) {
				newClds.add(Node(i, j))
				P[i][j] -= 2
			}
		}
	}
	for (cld in clds) {
		P[cld.y][cld.x] = -P[cld.y][cld.x]
	}
	clds = newClds
}

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))

	br.readLine().split(" ").map { it.toInt() }.let {
		n = it[0]; m = it[1]
	}
	P = Array(n) {br.readLine().split(" ").map { it.toInt() }.toIntArray()}
	clds = arrayListOf(Node(n - 1, 0), Node(n - 1, 1), Node(n - 2, 0), Node(n - 2, 1))
	repeat(m) {
		br.readLine().split(" ").map { it.toInt() }.let {
			move(it[0] - 1, it[1])
		}
	}
	var ret = 0
	for (i in 0 until n) {
		for (j in 0 until n) {
			ret += P[i][j]
		}
	}
	println(ret)
}