import java.io.*
import kotlin.math.*

var n = 0
var q = 0
var tn = 0
var P = arrayOf<IntArray>()

fun rotSub(y: Int, x: Int, sub: Array<IntArray>) {
	val k = sub.size
	for (i in 0 until k) {
		for (j in 0 until k) {
			sub[i][j] = P[y + i][x + j]
		}
	}
	for (i in 0 until k) {
		for (j in 0 until k) {
			P[y + j][x + k - 1 - i] = sub[i][j]
		}
	}
}

fun rot(idx: Int) {
	val k = 1.shl(idx)
	val sub = Array(k) {IntArray(k)}
	for (i in 0 until tn step k) {
		for (j in 0 until tn step k) {
			rotSub(i, j, sub)
		}
	}
}
val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)
val dels = ArrayDeque<Pair<Int, Int>>()
fun dec() {
	for (i in 0 until tn) {
		for (j in 0 until tn) {
			if (P[i][j] == 0) continue
			var sum = 0
			for (k in dr.indices) {
				val y = i + dr[k]
				val x = j + dc[k]
				if (y in 0 until tn && x in 0 until tn && P[y][x] > 0) ++sum
			}
			if (sum < 3) dels.add(i to j)
		}
	}
	while (dels.isNotEmpty()) {
		val cur = dels.removeFirst()
		--P[cur.first][cur.second]
	}
}

fun space(): Int {
	val que = ArrayDeque<Pair<Int, Int>>()
	val vstd = Array(tn) {BooleanArray(tn)}
	var ret = 0
	for (i in 0 until tn) {
		 for (j in 0 until tn) {
			 if (P[i][j] > 0 && !vstd[i][j]) {
				 vstd[i][j] = true
				 que.add(i to j)
				 var sum = 0
				 while (!que.isEmpty()) {
					 val cur = que.removeFirst()
					 ++sum
					 for (k in dr.indices) {
						 val y = cur.first + dr[k]
						 val x = cur.second + dc[k]
						 if (y in 0 until tn && x in 0 until tn && !vstd[y][x] && P[y][x] > 0) {
							 vstd[y][x] = true
							 que.add(y to x)
						 }
					 }
				 }
				 ret = max(ret, sum)
			 }
		 }
	}
	return ret
}

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	br.readLine().split(" ").map { it.toInt() }.let {
		n = it[0]; q = it[1]
	}
	tn = 1.shl(n)
	P = Array(tn) {br.readLine().split(" ").map { it.toInt() }.toIntArray()}
	br.readLine().split(" ").map { it.toInt() }.forEach {
		rot(it)
		dec()
	}
	var sum = 0

	repeat(tn) {
		sum += P[it].sum()
	}
	println(sum)
	println(space())
}