import java.io.*

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))
	for (T in 0 until br.readLine().toInt()) {
		val n = br.readLine().toInt()
		val par = IntArray(n)
		par.fill(-1)
		val ch = Array(n) {ArrayList<Int>()}
		repeat(n - 1) {
			val (a, b) = br.readLine().split(" ").map { it.toInt() - 1 }
			par[b] = a
			ch[a].add(b)
		}
		var (a, b) = br.readLine().split(" ").map { it.toInt() - 1 }

		var rot = -1
		repeat(n) {
			if (par[it] == -1) {
				rot = it
				return@repeat
			}
		}
		val dept = IntArray(n)
		dept.fill(-1)
		dept[rot] = 0
		val que = ArrayDeque<Int>()
		que.add(rot)
		while (!que.isEmpty()) {
			val cur = que.removeFirst()
			for (nxt in ch[cur]) {
				if (dept[nxt] == -1) {
					dept[nxt] = dept[cur] + 1
					que.add(nxt)
				}
			}
		}
		while (a != b) {
			if (dept[a] > dept[b]) a = par[a]
			else b = par[b]
		}
		bw.write("${a + 1}\n")
	}
	bw.flush()
}