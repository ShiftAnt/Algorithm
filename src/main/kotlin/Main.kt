import java.io.*

const val MX = 500
val P = Array(MX * 2 + 1) {IntArray(MX * 2 + 1)}
var n = 0
var djs = arrayOf<IntArray>()

fun draw(idx: Int, a: List<Int>) {
	for (i in listOf(a[1], a[3])) {
		for (j in a[0]..a[2]) {
			if (P[i][j] == -1 || P[i][j] == idx) P[i][j] = idx
			else {
				merge(P[i][j], idx)
				P[i][j] = idx
			}
		}
	}
	for (i in a[1]..a[3]) {
		for (j in listOf(a[0], a[2])) {
			if (P[i][j] == -1 || P[i][j] == idx) P[i][j] = idx
			else {
				merge(P[i][j], idx)
				P[i][j] = idx
			}
		}
	}
}

fun find(a: Int, set: Int = -1): Int {
	var cur = a

	while (cur != djs[cur][0]) {
		val tmp = cur
		cur = djs[cur][0]
		if (set != -1) {
			djs[tmp][0] = set
		}
	}
	if (set != -1) djs[cur][0] = set
	return cur
}

fun merge(a: Int, b: Int) {
	val ra = find(a)
	val rb = find(b)
	if (ra == rb) return
	if (djs[ra][1] < djs[rb][1]) {
		djs[rb][1] += djs[ra][1]
		find(a, rb)
		find(b, rb)
	} else {
		djs[ra][1] += djs[rb][1]
		find(a, ra)
		find(b, ra)
	}
}

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	n = br.readLine().toInt()
	djs = Array(n + 1) { IntArray(2) }

	repeat(n + 1) {
		djs[it][0] = it
		djs[it][1] = 1
	}

	repeat(P.size) {
		P[it].fill(-1)
	}
	P[MX][MX] = 0

	for (T in 1..n) {
		draw(T, br.readLine().split(" ").map { it.toInt() + MX})
	}
	val vstd = BooleanArray(n + 1)
	var ret = -1

	repeat(n + 1) {
		val fnd = find(it)
		if (!vstd[fnd]) {
			++ret
			vstd[fnd] = true
		}
	}
	println(ret)
}