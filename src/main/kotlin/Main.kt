fun main() {
	val n = readLine()!!.toInt()
	val P = Array(n) { readLine()!! }.sortedByDescending { it.length }
	val hs = ArrayList<HashSet<String>>()
	repeat(n) {
		val cur = P[it]
		var sum = 0
		for (h in hs) {
			var isIn = false
			for (k in h) {
				if (k.startsWith(cur)) {
					isIn = true
					break
				}
			}
			if (isIn) {
				++sum
				h.add(cur)
			}
		}
		if (sum == 0) hs += hashSetOf(cur)
	}
	println(hs.size)
}