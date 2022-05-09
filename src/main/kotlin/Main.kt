import java.io.*

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))
	val (n, l) = br.readLine().split(" ").map { it.toInt() }
	val st = java.util.StringTokenizer(br.readLine())
	val deq = ArrayDeque<Pair<Int, Int>>()

	for (i in 0 until n) {
		val cur = st.nextToken().toInt()
		if (deq.isNotEmpty() && deq.first().second <= i - l) deq.removeFirst()

		while (deq.isNotEmpty() && deq.last().first > cur) deq.removeLast()

		deq.addLast(cur to i)
		bw.write("${deq.first().first} ")
	}
	bw.flush()
}