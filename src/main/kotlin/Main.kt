
fun main() {
	val n = readLine()!!.toInt()
	val P = LongArray(n + 1)
	P[0] = 1
	if (n >= 2) P[2] = 3
	for (i in 4..n step 2) {
		P[i] = P[i - 2] * 3
		for (j in i - 4 downTo 0 step 2) {
			P[i] += P[j] * 2
		}
	}
	println(P[n])
}