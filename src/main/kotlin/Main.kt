import java.io.*
import java.util.PriorityQueue

class Node(
	val y: Int,
	val x: Int,
	val k: Int,
	val isNight: Int,
    val ret: Int
)
val dr = arrayOf(-1, 0, 0, 1)
val dc = arrayOf(0, -1, 1, 0)
fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	val (n, m, k) = br.readLine().split(" ").map { it.toInt() }

	val P = Array(n) {br.readLine()}

	val vstd = Array(n) {IntArray(m)}
    repeat(n) {
        vstd[it].fill(-1)
    }

	val que = PriorityQueue<Node>(compareBy { it.ret })

	vstd[0][0] = k

	que.add(Node(0, 0, k, 0, 1))
	que.add(Node(0, 0, k, 1, 2))
	var ret = -1
    if (n == 1 && m == 1) ret = 1
	loop@
	while (!que.isEmpty()) {
        val cur = que.poll()
        for (i in dr.indices) {
            val y = cur.y + dr[i]
            val x = cur.x + dc[i]
            if (y in 0 until n && x in 0 until m) {
                if (y == n - 1 && x == m - 1) {
                    ret = cur.ret + 1
                    break@loop
                }
                if (P[y][x] == '0' && vstd[y][x] < cur.k) {
                    vstd[y][x] = cur.k
                    que.add(Node(y, x, cur.k, 1 - cur.isNight, cur.ret + 1))
                    que.add(Node(y, x, cur.k, cur.isNight, cur.ret + 2))
                }
                else if (cur.isNight == 0 && P[y][x] == '1' && cur.k > 0 && vstd[y][x] < cur.k - 1) {
                    vstd[y][x] = cur.k - 1
                    que.add(Node(y, x, cur.k - 1, 1, cur.ret + 1))
                    que.add(Node(y, x, cur.k - 1, 0, cur.ret + 2))
                }
            }
        }

	}
    bw.write("$ret")
	bw.flush()
}