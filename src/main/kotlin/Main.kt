import java.io.*
import java.util.StringTokenizer

val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val (h, w, n) = Array(3) {st.nextToken().toInt()}

    val P = Array(h) {StringBuilder(br.readLine())}
    var cur = 0 to 0

    repeat(h) {
        for (i in 0 until w) {
            if (P[it][i] == 'S') {
                cur = it to i
                P[it][i] = '.'
                return@repeat
            }
        }
    }
    fun bfs(y: Int, x: Int, fnd: Char): Triple<Int, Int, Int> {
        val que = ArrayDeque<Pair<Int, Int>>()
        val vstd = Array(h) {BooleanArray(w)}
        vstd[y][x] = true
        que.add(y to x)
        var ret = 0
        while (!que.isEmpty()) {
            val siz = que.size
            ++ret
            for (tc in 0 until siz) {
                val c = que.removeFirst()
                for (i in dr.indices) {
                    val a = c.first + dr[i]
                    val b = c.second + dc[i]
                    if (a in 0 until h && b in 0 until w && P[a][b] != 'X' && !vstd[a][b]) {
                        vstd[a][b] = true
                        if (P[a][b] == fnd) {
                            P[a][b] = '.'
                            return Triple(a, b, ret)
                        }
                        que.add(a to b)
                    }
                }
            }
        }
        return Triple(0, 0, -1)
    }
    var ret = 0
    for (tc in '1'..n.toString()[0]) {
        bfs(cur.first, cur.second, tc).let {
            cur = it.first to it.second; ret += it.third
        }
    }
    println(ret)
}