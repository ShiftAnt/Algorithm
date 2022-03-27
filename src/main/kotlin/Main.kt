import java.util.StringTokenizer
import kotlin.math.*

var n = 0
var al = arrayOf<ArrayList<Int>>()
var P = intArrayOf()
fun dfs(idx: Int) {
	if (al[idx].isEmpty()) return
	for (nxt in al[idx]) {
		dfs(nxt)
	}
	var mx = 0
    val prev = ArrayList<Int>()
	al[idx].forEach {prev.add(P[it])}
    prev.sortDescending()

    for (i in prev.indices) {
        mx = max(i + 1 + prev[i], mx)
    }
    P[idx] = mx
}

fun main() {
	n = readLine()!!.toInt()
	val st = StringTokenizer(readLine())
	st.nextToken()
	al = Array(n) {ArrayList()}
	P = IntArray(n)
	for (i in 1 until n) {
		al[st.nextToken().toInt()].add(i)
	}
	dfs(0)
	println(P[0])
}