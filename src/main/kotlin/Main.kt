import java.io.*

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	while (true) {
		val n = br.readLine().toInt()
		if (n == 0) break
		val P = ArrayList<Pair<String, Int>>()
		val nxts = Array(n) {ArrayList<Int>()}
		repeat(n) { tc ->
			br.readLine().split(" ").let {
				P += it[0] to it[1].toInt()
				for (i in 2 until it.size - 1) {
					nxts[tc] += it[i].toInt() - 1
				}
			}
		}
		val vstd = IntArray(n)
		vstd.fill(-1)
		var ret = "No"
		val que = ArrayDeque<Pair<Int, Int>>()
		que += 0 to 0
		vstd[0] = 0
		while (!que.isEmpty()) {
			val cur = que.removeFirst()
			val money = when (P[cur.first].first) {
				"L" -> if (cur.second < P[cur.first].second) P[cur.first].second else cur.second
				"T" -> cur.second - P[cur.first].second
				else -> cur.second
			}
			if (money < 0) continue
			if (cur.first == n - 1) {
				ret = "Yes"
				break
			}
			for (nxt in nxts[cur.first]) {
				if (vstd[nxt] < money) {
					vstd[nxt] = money
					que += nxt to money
				}
			}
		}
		bw.write("$ret\n")
	}

	bw.close()
	br.close()
}