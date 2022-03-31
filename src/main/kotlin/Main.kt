import java.io.*
import java.util.StringTokenizer
import kotlin.math.*

var n = 0
var al = arrayOf<ArrayList<Int>>()
var vstd = booleanArrayOf()
var P = arrayOf<IntArray>()

fun dfs(idx: Int) {
	vstd[idx] = true
	var wbSum = 0
	val nxts = ArrayList<Int>()
	for (nxt in al[idx]) {
		if (vstd[nxt]) continue
		dfs(nxt)
		nxts += nxt
		wbSum += max(P[nxt][1], P[nxt][2])
	}
	for (nxt in nxts) {
		P[idx][0] += P[nxt][1]
		P[idx][1] = max(P[idx][1], wbSum - max(P[nxt][1], P[nxt][2]) + P[nxt][2])
		P[idx][2] += max(P[nxt][0], P[nxt][1])
	}
}
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))

	n = br.readLine().toInt()
	vstd = BooleanArray(n)
	P = Array(n) {IntArray(3)}
	var st = StringTokenizer(br.readLine())
	repeat(n) {
		P[it][2] = st.nextToken().toInt()
	}
	al = Array(n) {ArrayList()}
	repeat(n - 1) {
		st = StringTokenizer(br.readLine())
		val a = st.nextToken().toInt() - 1
		val b = st.nextToken().toInt() - 1
		al[a] += b
		al[b] += a
	}
	dfs(0)
	println(max(P[0][1], P[0][2]))
}