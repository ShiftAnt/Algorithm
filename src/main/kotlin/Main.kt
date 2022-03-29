import java.io.*
import java.util.StringTokenizer

var P = arrayOf<StringBuilder>()
var r = 0
var c = 0

fun move(h: Int, isLeft: Boolean) {
	var tar = -1 to -1
	if (isLeft) {
		for (i in 0 until c) {
			if (P[h][i] == 'x') {
				tar = h to i
				break
			}
		}
	} else {
		for (i in c - 1 downTo 0) {
			if (P[h][i] == 'x') {
				tar = h to i
				break
			}
		}
	}
	if (tar.first == -1) return
	val vstd = Array(r) {BooleanArray(c)}
	P[tar.first][tar.second] = '.'
	val sub = ArrayList<ArrayList<Pair<Int, Int>>>()
	for (i in dr.indices) {
		val y = tar.first + dr[i]
		val x = tar.second + dc[i]
		if (y in 0 until r && x in 0 until c && P[y][x] == 'x' && !vstd[y][x]) {
			sub += bfs(y, x, vstd)
		}
	}
	for (clus in sub) {
		down(clus)
	}
}
fun draw(clus: ArrayList<Pair<Int, Int>>, c: Char, plus: Int = 0) {
	for (clu in clus) P[clu.first + plus][clu.second] = c
}
fun down(clus: ArrayList<Pair<Int, Int>>) {
	draw(clus, '.')
	var ret = 0

	loop@
	for (i in 1 until r) {
		for (clu in clus) {
			val y = clu.first + i
			val x = clu.second
			if (y >= r || P[y][x] == 'x') {
				ret = i - 1
				break@loop
			}
		}
	}
	draw(clus, 'x', ret)
}
val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)
fun bfs(y: Int, x: Int, vstd: Array<BooleanArray>) = ArrayList<Pair<Int, Int>>().also { ret ->
	if (P[y][x] == '.') return ret
	ret.add(y to x)
	vstd[y][x] = true
	var tc = -1
	while (++tc < ret.size) {
		val cur = ret[tc]
		for (i in dr.indices) {
			val a = cur.first + dr[i]
			val b = cur.second + dc[i]
			if (a in 0 until r && b in 0 until c && P[a][b] == 'x' && !vstd[a][b]) {
				ret += a to b
				vstd[a][b] = true
			}
		}
	}
}

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))
	br.readLine().split(" ").map { it.toInt() }.let {
		r = it[0]; c = it[1]
	}
	P = Array(r) { StringBuilder(br.readLine()) }
	val n = br.readLine().toInt()
	val st = StringTokenizer(br.readLine())
	repeat(n) {
		move(r - st.nextToken().toInt(), it % 2 == 0)
	}
	repeat(r) {
		bw.write("${P[it]}\n")
	}
	bw.flush()
}