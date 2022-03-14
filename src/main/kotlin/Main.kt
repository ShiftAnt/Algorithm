import java.io.*

var r = 0
var c = 0
var k = 0
var ret = 0
var P = arrayOf<StringBuilder>()
val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)
fun dfs(y: Int, x: Int, mv: Int) {
    if (y == 0 && x == c - 1) {
        if (mv == k) ++ret
        return
    }
    if (mv == k) return
    P[y][x] = 'T'
    for (i in dr.indices) {
        val a = y + dr[i]
        val b = x + dc[i]
        if (a in 0 until r && b in 0 until c && P[a][b] == '.') {
            dfs(a, b, mv + 1)
        }
    }
    P[y][x] = '.'
}
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    br.readLine().split(" ").map { it.toInt() }.let {
        r = it[0]; c = it[1]; k = it[2]
    }
    P = Array(r) {StringBuilder(br.readLine())}
    dfs(r - 1, 0, 1)
    println(ret)
}