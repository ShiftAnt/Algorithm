fun gcf(a: Long, b: Long): Long {
	if (b == 0L) return a
	return gcf(b, a % b)
}
fun lcf(a: Long, b: Long) = a / gcf(a, b) * b

class Node(
	var a: Long = 0,
	var b: Long = 0
)

fun main() {
	val n = readLine()!!.toInt()

	val al = Array(n) {ArrayList<Triple<Int, Int, Int>>()}

	repeat(n - 1) {
		readLine()!!.split(" ").map { it.toInt() }.let {
			al[it[0]] += Triple(it[1], it[2], it[3])
			al[it[1]] += Triple(it[0], it[3], it[2])
		}
	}
	val vstd = BooleanArray(n)

	val nodes = Array(n) {Node()}
	nodes[0].a = 1
	nodes[0].b = 1

	fun dfs(idx: Int) {
		vstd[idx] = true
		val cur = al[idx]

		for (nxt in cur) {
			if (vstd[nxt.first]) continue
			nodes[nxt.first].a = nodes[idx].a * nxt.third
			nodes[nxt.first].b = nodes[idx].b * nxt.second
			dfs(nxt.first)
		}
	}
	dfs(0)
	var lcf = nodes[0].b
	for (i in 0 until n) {
		lcf = lcf(lcf, nodes[i].b)
	}
	for (i in 0 until n) {
		nodes[i].a = lcf / nodes[i].b * nodes[i].a
		nodes[i].b = 1
	}
	var gcf = nodes[0].a
	for (i in 1 until n) {
		gcf = gcf(gcf, nodes[i].a)
	}
	for (i in 0 until n) {
		print("${nodes[i].a / gcf} ")
	}
}