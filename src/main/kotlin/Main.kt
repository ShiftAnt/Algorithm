import java.io.*
const val MX = 2000000
const val NO = "NO"
const val YES = "YES"
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))
	val pn = ArrayList<Long>()
	val P = BooleanArray(MX + 1)
	for (i in 2.. MX) {
		if (!P[i]) {
			pn.add(i.toLong())
			for (j in i * 2..MX step i) {
			P[j] = true
			}
		}
	}
	for (tc in 0 until br.readLine().toInt()) {
		val s = br.readLine().split(" ").map { it.toLong() }.sum()
		var ret = YES
		if (s % 2 == 0L) {
			if (s == 2L) ret = NO
		} else {
			if (s == 3L) ret = NO
			else {
				val sub = s - 2
				for (p in pn) {
					if (p * p > sub) break
					if (sub % p == 0L) {
						ret = NO
						break
					}
				}
			}
		}
		bw.write("$ret\n")
	}
	bw.flush()
}