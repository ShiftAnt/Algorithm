import java.io.*

fun solution(): List<Int> {
	val (n, k) = readLine()!!.split(" ").map { it.toInt()}
	val ret = ArrayList<Int>()
	if (n == k) {
		ret.add(n)
		return ret
	}
	val mx = 200001
	val P = IntArray(mx)
	P.fill(-1)
	val que = ArrayDeque<Int>()
	que.add(n)

	P[n] = n
	loop@
	while (!que.isEmpty()) {
		val cur = que.removeFirst()
		val nxts = arrayOf(cur - 1, cur + 1 , cur * 2)

		for (nxt in nxts) {
			if (nxt in 0 until mx && P[nxt] == -1) {
				P[nxt] = cur
				if (nxt == k) break@loop
				que.add(nxt)
			}
		}
	}
	var cur = k
	while (cur != n) {
		ret.add(cur)
		cur = P[cur]
	}
	ret.add(cur)
	return ret.reversed()
}

fun main() {
	val bw = BufferedWriter(OutputStreamWriter(System.out))
	solution().let {
		bw.write("${it.size - 1}\n")
		it.forEach { ele ->
			bw.write("$ele ")
		}
	}
	bw.flush()
}