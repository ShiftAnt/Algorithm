fun main() {
	val (n) = readLine()!!.split(" ").map { it.toInt() }

	val P = readLine()!!.split(" ").map { it.toInt() }

	val que = ArrayDeque<Int>()
	repeat(n) {que += it + 1}
	var ret = 0
	for (p in P) {
		for (r in que.indices) {
			if (que[r] != p) continue
			val l = que.size - r
			val sub = if (l < r) l else r
			for (i in 0 until sub) if (l < r) que.addFirst(que.removeLast()) else que.addLast(que.removeFirst())
			ret += sub
			break
		}
		que.removeFirst()
	}
	println(ret)
}