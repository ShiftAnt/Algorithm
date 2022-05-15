fun main() {
	val n = readLine()!!.toInt()
	val AB = Array(2) { readLine()!!.split(" ").map { it.toInt() }.toIntArray() }
	AB[0].sort()
	AB[1].sortDescending()
	var ret = 0
	repeat(n) {
		ret += AB[0][it] * AB[1][it]
	}
	println(ret)
}