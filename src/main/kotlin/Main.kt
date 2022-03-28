import java.io.*
import java.util.PriorityQueue

var n = 0
var m = 0
var t = 0
var s = 0
var g = 0
var h = 0
var al = arrayOf<ArrayList<Pair<Int, Int>>>()
var dest = intArrayOf()
fun solution() {
	val vstd = IntArray(n) {Int.MAX_VALUE}
	val isC = BooleanArray(n)
	val que = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
	que.add(Pair(s, 0))
	vstd[s] = 0
	while (!que.isEmpty()) {
		val cur = que.poll()

		for (nxt in al[cur.first]) {
			val isCross = cur.first == g && nxt.first == h || cur.first == h && nxt.first == g || isC[cur.first]
			val nxtVal = vstd[cur.first] + nxt.second
			if (vstd[nxt.first] > nxtVal) {
				vstd[nxt.first] = nxtVal
				que.add(nxt.first to vstd[nxt.first])
				isC[nxt.first] = isCross
			} else if (vstd[nxt.first] == nxtVal && !isC[nxt.first] && isCross) {
				que.add(nxt.first to vstd[nxt.first])
				isC[nxt.first] = true
			}
		}
	}
	bw.write(dest.filter { isC[it] }.map { it + 1 }.joinToString(" "))
	bw.write("\n")
}
val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	repeat(br.readLine().toInt()) {
		br.readLine().split(" ").map { it.toInt() }.let {
			n = it[0]; m = it[1]; t = it[2]
		}
		br.readLine().split(" ").map { it.toInt() - 1 }.let {
			s = it[0]; g = it[1]; h = it[2]
		}
		al = Array(n) { ArrayList() }
		repeat(m) {
			br.readLine().split(" ").map { it.toInt() }.let {
				al[it[0] - 1] += it[1] - 1 to it[2]
				al[it[1] - 1] += it[0] - 1 to it[2]
			}
		}
		dest = IntArray(t)
		repeat(t) {
			dest[it] = br.readLine().toInt() - 1
		}
		dest.sort()
		solution()
	}
	bw.flush()
}