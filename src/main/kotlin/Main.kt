import java.io.*
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))
	val lPq = java.util.PriorityQueue<Int>(compareByDescending { it })
	val rPq = java.util.PriorityQueue<Int>()

	for (tc in 0 until br.readLine().toInt()) {
		val num = br.readLine().toInt()
		val ls = lPq.size
		val rs = rPq.size
		if (lPq.isEmpty()) {
			lPq += num
		} else {
			val lt = lPq.peek()
			if (ls == rs) {
				val rt = rPq.peek()
				if (num <= rt) lPq += num
				else {
					lPq += rPq.poll()
					rPq += num
				}
			} else {
				if (num >= lt) {
					rPq += num
				} else {
					rPq += lPq.poll()
					lPq += num
				}
			}
		}
		bw.write("${lPq.peek()}\n")
	}
	bw.flush()
}