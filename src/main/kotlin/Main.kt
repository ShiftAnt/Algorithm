import java.io.*

var n = 0
var m = 0
var k = 0
var P = arrayOf<String>()
var tar = ""
var len = 0
var dp = arrayOf<Array<IntArray>>()

val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)
fun dfs(y: Int, x: Int, idx: Int): Int {
	if (dp[y][x][idx] != -1) return dp[y][x][idx]
	if (idx == len - 1) {
		dp[y][x][idx] = 1
		return 1
	}
	dp[y][x][idx] = 0
	for (i in dr.indices) {
		var a = y + dr[i]
		var b = x + dc[i]
		var step = 0
		while (a in 0 until n && b in 0 until m && step++ < k) {
			if (P[a][b] == tar[idx + 1]) dp[y][x][idx] += dfs(a, b, idx + 1)
			a += dr[i]
			b += dc[i]
		}
	}
	return dp[y][x][idx]
}

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	br.readLine().split(" ").map { it.toInt() }.let {
		n = it[0]
		m = it[1]
		k = it[2]
	}
	P = Array(n) {""}
	repeat(n) {
		P[it] = br.readLine()
	}

	tar = br.readLine()
	len = tar.length

	dp = Array(n) {Array(m) { IntArray(len) } }
	repeat(n) { i ->
		repeat(m) { j ->
			dp[i][j].fill(-1)
		}
	}
	var ret = 0
	for (i in 0 until n) {
		for (j in 0 until m) {
			if (P[i][j] == tar[0]) {
				ret += dfs(i, j, 0)
			}
		}
	}

	bw.write("$ret")
	bw.close()
	br.close()
}