import java.io.*

var n = 0
var r = 0
var q = 0
var al = arrayOf<ArrayList<Int>>()
val queries = ArrayList<Int>()
var P = intArrayOf()

fun dfs(idx: Int): Int {
	P[idx] = 1

	for (nxt in al[idx]) {
		if (P[nxt] == 0) {
			P[idx] += dfs(nxt)
		}
	}
	return P[idx]
}

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	br.readLine().split(" ").map { it.toInt() }.let {
		n = it[0]
		r = it[1]
		q = it[2]
	}
	al = Array(n) {ArrayList()}
	P = IntArray(n)
	repeat(n - 1) {
		val (a, b) = br.readLine().split(" ").map { it.toInt() - 1 }
		al[a].add(b)
		al[b].add(a)
	}
	repeat(q) {
		queries.add(br.readLine().toInt() - 1)
	}

	dfs(r - 1)

	repeat(q) {
		bw.write("${P[queries[it]]}\n")
	}

	bw.close()
	br.close()
}