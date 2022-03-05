import java.io.*
import java.util.Scanner
import kotlin.math.*

fun main() {
	val sc = Scanner(System.`in`)
	val bw = BufferedWriter(OutputStreamWriter(System.out))
	val n = sc.nextInt()
	val P = IntArray(n)
	repeat(n) {
		P[it] = sc.nextInt()
	}
	val al = ArrayList<Pair<Int, Int>>()

	for (tc in 0 until sc.nextInt()) {
		var a = sc.nextInt()
		var b = sc.nextInt()
		if (a <= b) a = b
		var cur = a to b
		while (al.isNotEmpty()) {
			val lst = al.last()
			if (max(cur.first, cur.second) >= max(lst.first, lst.second)) al.removeLast()
			else if (cur.first >= lst.second || cur.second >= lst.second) {
				cur = lst.first to cur.second
				al.removeLast()
			} else break
		}
		al.add(cur)
	}
	val que = ArrayDeque<Int>()
	repeat(al[0].first) {
		que.add(P[it])
	}
	que.sort()
	val ret = ArrayDeque<Int>()
	var cur = al[0].first
	for (nxt in al) {
		for (i in nxt.first until cur) ret.addFirst(que.removeFirst())
		cur = nxt.first
		for (i in nxt.second until cur) ret.addFirst(que.removeLast())
		cur = nxt.second
	}
	for (i in 0 until cur) ret.addFirst(que.removeFirst())

	for (i in al[0].first until n) {
		ret.addLast(P[i])
	}
	repeat(n) {
		bw.write("${ret[it]} ")
	}
	bw.flush()
}