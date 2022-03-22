import java.io.*
import java.util.StringTokenizer
import kotlin.math.*

var n = 0
fun fndIdx(siz: Int, lst: List<Int>): Int {
	var stt = 0
	var end = lst.size - 1
	var ret = -1
	while (stt <= end) {
		val mid = (stt + end) / 2
		if (lst[mid] < siz) {
			stt = mid + 1
			ret = max(mid, ret)
		} else end = mid - 1
	}
	return ret
}
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))
	val n = br.readLine().toInt()
	val cs = Array(n) {IntArray(2)}
	val C = Array(n) {ArrayList<Int>()}
	val SC = Array(n) {ArrayList<Long>()}
	val P = ArrayList<Int>()
	val SP = LongArray(n)
	repeat(n) {
		val st = StringTokenizer(br.readLine())
		val c = st.nextToken().toInt() - 1
		val s = st.nextToken().toInt()
		cs[it][0] = c
		cs[it][1] = s
		P.add(s)
		C[c] += s
	}
	P.sort()
	repeat(n) {
		C[it].sort()
		if (C[it].isNotEmpty()) SC[it].add(C[it][0].toLong())

		for (i in 1 until C[it].size) {
			SC[it] += SC[it][i - 1] + C[it][i]
		}
	}
	SP[0] = P[0].toLong()
	for (i in 1 until n) {
		SP[i] = SP[i - 1] + P[i]
	}
	repeat(n) {
		val cur = cs[it]
		val a = fndIdx(cur[1], P)
		val b = fndIdx(cur[1], C[cur[0]])
		val sub = if (a == - 1) 0 else SP[a] - if (b == -1) 0 else SC[cur[0]][b]
		bw.write("$sub\n")
	}
	bw.flush()
}