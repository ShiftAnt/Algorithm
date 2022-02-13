import java.io.*
import java.util.TreeMap

fun find(key: Int): Int {
	if (tm[key] != null) return key
	val c = tm.ceilingKey(key) ?: Int.MAX_VALUE
	val f = tm.floorKey(key) ?: (Int.MIN_VALUE / 2)

	if (c - key < key - f) {
		if (c - key <= K) return c
	} else if (c - key > key - f) {
		if (key - f <= K) return f
	} else if (c - key <= K) return -2
	return -1
}

val tm = TreeMap<Int, Int>()
var N = 0
var M = 0
var K = 0
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	br.readLine().split(" ").map { it.toInt() }.let {
		N = it[0]
		M = it[1]
		K = it[2]
	}


	repeat(N) {
		br.readLine().split(" ").map { it.toInt() }.let {
			tm[it[0]] = it[1]
		}
	}
	repeat(M) {
		br.readLine().split(" ").map { it.toInt() }.let {
			when (it[0]) {
				1 -> tm[it[1]] = it[2]
				2 -> {
					val fnd = find(it[1])
					if (fnd >= 0) tm[fnd] = it[2]
				}
				3 -> {
					find(it[1]).apply {
						val inp = when (this) {
							-2 -> "?"
							-1 -> "-1"
							else -> "${tm[this]}"
						}
						bw.write("$inp\n")
					}
				}
			}
		}
	}

	bw.close()
	br.close()
}