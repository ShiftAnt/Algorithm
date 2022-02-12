import java.io.*


var n = 0
var arr = arrayOf(0, 1, 2)
var P = arrayOf<CharArray>()
val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)
fun isBlank(idx: Int): Boolean {
	return P[idx / n][idx % n] == 'X'
}

fun fill(idx: Int, c: Char) {
	P[idx / n][idx % n] = c
}

fun check(): Boolean {
	for (t in ts) {
		val y = t.first
		val x = t.second
		for (i in 0 until 4) {
			var a = y + dr[i]
			var b = x + dc[i]

			while (a in 0 until n && b in 0 until n && P[a][b] != 'O') {
				if (P[a][b] == 'S') return false
				a += dr[i]
				b += dc[i]
			}
		}
	}
	return true
}

val ts = ArrayList<Pair<Int, Int>>()
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	n = br.readLine().toInt()

	P = Array(n) { charArrayOf() }

	repeat(n) {
		P[it] = br.readLine().split(" ").map { it[0] }.toCharArray()
		for (i in 0 until n) {
			if (P[it][i] == 'T') ts.add(it to i)
		}
	}
	var ret = "NO"
	while (arr[0] != n * n - 2) {
		if (isBlank(arr[0]) && isBlank(arr[1]) && isBlank(arr[2])) {
			for (i in arr.indices) fill(arr[i], 'O')
			if (check()) {
				ret = "YES"
				break
			}
			for (i in arr.indices) fill(arr[i], 'X')
		}
		if (++arr[2] == n * n) {
			if (++arr[1] == n * n - 1) {
				++arr[0]
				arr[1] = arr[0] + 1
			}
			arr[2] = arr[1] + 1
		}
	}
	bw.write(ret)
	bw.close()
	br.close()
}