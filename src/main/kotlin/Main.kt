const val INF =  Int.MAX_VALUE
const val LINF = Long.MAX_VALUE
class Node(
	var a: Int,
	var b: Int,
	var siz: Long,
) {
	val oa = a
	val ob = b
}
fun main() {
	val pq = java.util.PriorityQueue<Node>(compareBy({it.siz}, {it.a}))
	val l = readLine()!!.toInt()
	val P = readLine()!!.split(" ").map { it.toInt() }.sorted()
	var prv = 0
	for (i in 0..l) {
		if (i == l) {
			pq += Node(prv + 1, INF, LINF)
			continue
		}
		val cur = P[i]
		if (i == 0) pq += Node(1, cur - 1, cur.toLong() - 2)
		else if (prv + 1 != cur) pq += Node(prv + 1, cur - 1, cur.toLong() - prv - 2)
		pq += Node(cur, cur, 0)
		prv = cur
	}
	var n = readLine()!!.toInt()
	while (pq.isNotEmpty()) {
		val cur = pq.poll()
		if (cur.siz == LINF) {
			while (--n >= 0) {
				print("${cur.a++} ")
			}
			break
		}
		if (cur.a > cur.b) continue
		if (cur.a == cur.b) {
			if (--n >= 0) print("${cur.a} ")
			else break
			continue
		}
		if (--n >= 0) print("${cur.a} ")
		else break
		if (--n >= 0) print("${cur.b} ")
		else break
		++cur.a
		--cur.b
		if (cur.siz != LINF) cur.siz = (cur.a - cur.oa).toLong() * (cur.ob - cur.a + 1) + cur.ob - cur.a
		pq += cur
	}
}