fun main() {
	val n = readLine()!!.toInt()

	val oils = ArrayList<Pair<Int, Int>>()

	repeat(n) {
		oils += readLine()!!.split(" ").map { it.toInt() }.let {
			it[0] to it[1]
		}
	}
	val (l, p) = readLine()!!.split(" ").map { it.toInt() }
	oils += l to 0
	oils.sortBy { it.first }

	val pq = java.util.PriorityQueue<Int>(compareByDescending { it })
	var ret = -1
	var sub = 0
	var a = 0
	var b = p
	for (i in oils.indices) {
		val cur = oils[i]

		while (cur.first - a > b && pq.isNotEmpty()) {
			b += pq.poll()
			++sub
		}
		if (cur.first - a > b) break

		pq += cur.second
		b -= (cur.first - a)
		a = cur.first
		if (a == l) {
			ret = sub
			break
		}
	}

	println(ret)
}