import java.io.*
import kotlin.math.*

var n = 0
var m = 0
var P = arrayOf<StringBuilder>()
var cmds = ""
var cur = 0 to 0
var crz = ArrayDeque<Pair<Int, Int>>()

val dr = arrayOf(1, 1, 1, 0, 0, 0, -1, -1, -1)
val dc = arrayOf(-1, 0, 1, -1, 0, 1, -1, 0, 1)

fun dis (a: Pair<Int, Int>, b: Pair<Int, Int>) = abs(a.first - b.first) + abs(a.second - b.second)
fun move(tar: Pair<Int, Int>): Pair<Int, Int> {
	var mi = Int.MAX_VALUE
	var idx = -1
	for (i in dr.indices) {
		val y = tar.first + dr[i]
		val x = tar.second + dc[i]
		val d = dis(cur, y to x)
		if (mi > d) {
			mi = d
			idx = i
		}
	}
	return tar.first + dr[idx] to tar.second + dc[idx]
}

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	br.readLine().split(" ").map { it.toInt() }.let {
		n = it[0]
		m = it[1]
	}
	P = Array(n) { StringBuilder() }

	repeat(n) {
		P[it] = StringBuilder(br.readLine())
		for (i in 0 until m) {
			if (P[it][i] == 'I') cur = it to i
			else if (P[it][i] == 'R') crz += it to i
		}
	}

	cmds = br.readLine()
	var ret = -1
	loop@
	for (tc in cmds.indices) {
		val cmd = cmds[tc] - '0' - 1
		P[cur.first][cur.second] = '.'
		cur = cur.first + dr[cmd] to cur.second + dc[cmd]
	   if (P[cur.first][cur.second] == 'R') {
		   ret = tc + 1
		   break
	   } else P[cur.first][cur.second] = 'I'

		for (i in crz.indices) {
			P[crz[i].first][crz[i].second] = '.'
			crz[i] = move(crz[i])
		}
		val del = ArrayList<Pair<Int, Int>>()
		for (i in crz.indices) {
			val y = crz[i].first
			val x = crz[i].second
			when (P[y][x]) {
				'I' -> {
					ret = tc + 1
					break@loop
				}
				'.' -> {
					P[y][x] = 'R'
				}
				'R' -> {
					P[y][x] = 'X'
					del.add(y to x)
				}
			}
		}
		val siz = crz.size
		for (i in 0 until siz) {
			val it = crz.removeFirst()
			if (P[it.first][it.second] != 'X') {
				crz += it
			}
		}
		for (i in del.indices) {
			P[del[i].first][del[i].second] = '.'
		}
	}

	if (ret == -1) {
		repeat(n) {
			bw.write("${P[it]}\n")
		}
	}
	else bw.write("kraj $ret\n")

	bw.close()
	br.close()
}