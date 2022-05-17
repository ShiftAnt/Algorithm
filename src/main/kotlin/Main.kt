fun main() {
	val n = readLine()!!.toInt()

	val P = Array(n) { readLine()!! }

	val ret = StringBuilder()

	loop@
	for (i in P[0].indices) {
		val char = P[0][i]
		for (j in 1 until n) {
			if (char != P[j][i]) {
				ret.append("?")
				continue@loop
			}
		}
		ret.append(char)
	}
	println(ret)
}