fun main() {
	val w = readLine()!!.split(" ").map { it.toInt() }[1]
	val blo = readLine()!!.split(" ").map { it.toInt() }
	var ret = 0
	for (i in 1..blo.maxOf { it }) {
		var b = 0
		var e = 0
		for (j in 0 until w) {
			if (blo[j] >= i) {
				if (++b == 2) {
					ret += e
					b = 1
				}
				e = 0
			} else ++e
		}
	}
	println(ret)
}