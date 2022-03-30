import java.io.*
var n = 0
var P = arrayListOf<IntArray>()
var map = hashMapOf<String, Int>()

fun djs(a: Int, set: Int = -1): Int {
	var cur = a
	while (cur != P[cur][0]) {
		val tmp = cur
		cur = P[cur][0]
		if (set != -1) P[tmp][0] = set
	}
	if (set != -1) P[cur][0] = set
	return cur
}

fun merge(a: Int, b: Int): Int {
	val ta = djs(a)
	val tb = djs(b)
	if (ta == tb) return P[ta][1]
	val f: Int
	val t: Int
	if (P[ta][1] < P[tb][1]) {
		f = ta
		t = tb
	} else {
		f = tb
		t = ta
	}
	P[t][1] += P[f][1]
	djs(a, t)
	djs(b, t)
	return P[t][1]
}

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))
	repeat(br.readLine().toInt()) {
		P = ArrayList()
		map = HashMap()
		var idx = 0
		for (i in 0 until br.readLine().toInt()) {
			br.readLine().split(" ").let {
				for (j in 0 until 2) {
					if (map[it[j]] == null) {
						P += intArrayOf(idx, 1)
						map[it[j]] = idx++
					}
				}
				bw.write("${merge(map[it[0]]!!, map[it[1]]!!)}\n")
			}
		}
	}
	bw.flush()
}