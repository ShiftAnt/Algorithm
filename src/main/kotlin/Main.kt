import java.io.*
const val MOD = 1000000
fun times(a: Array<LongArray>, b: Array<LongArray>): Array<LongArray> {
	val ret = Array(2) {LongArray(2)}

	for (i in 0 until 2) {
		for (j in 0 until 2) {
			for (k in 0 until 2) {
				ret[i][j] += a[i][k] * b[k][j]
			}
			ret[i][j] = ret[i][j] % MOD
		}
	}
	return ret
}
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))

	var n = br.readLine().toLong()
	var cur = arrayOf(longArrayOf(1, 0), longArrayOf(0, 1))
	var time = arrayOf(longArrayOf(1, 1), longArrayOf(1, 0))

	while (n != 0L) {
		if (n % 2 == 1L) cur = times(time, cur)
		n /= 2
		time = times(time, time)
	}
	println("${cur[1][0]}")
}