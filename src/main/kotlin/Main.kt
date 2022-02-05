import java.io.*

var n = 0
var m = 0
var djs = arrayOf<IntArray>()
var Q = intArrayOf()
var P = arrayOf<IntArray>()

fun rot(a: Int, set: Int = -1): Int {
    var cur = a

    while (djs[cur][0] != cur) {
        val tmp = cur
        cur = djs[cur][0]
        if (set != -1) djs[tmp][0] = set
    }
    if (set != -1) djs[cur][0] = set
    return cur
}

fun merge(a: Int, b: Int) {
    var y = rot(a)
    var x = rot(b)
    if (djs[y][1] < djs[x][1]) {
        y = x.also { x = y }
    }
    rot(a, y)
    rot(b, y)
    djs[y][1] += djs[x][1]
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    n = br.readLine().toInt()
    m = br.readLine().toInt()
    djs = Array(n) {IntArray(2)}
    P = Array(n) { IntArray(n) }
    repeat(n) {
        djs[it][0] = it
        djs[it][1] = 1
        P[it] = br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }

    Q = br.readLine().split(" ").map { it.toInt() - 1 }.toIntArray()

    for (i in 0 until n) {
        for (j in i + 1 until n) {
            if (P[i][j] == 1) {
                merge(i, j)
            }
        }
    }
    val stt = rot(Q[0])
    var isC = true
    for (i in 1 until m) {
        if (stt != rot(Q[i])) {
            isC = false
            break
        }
    }
    bw.write(if (isC) "YES" else "NO")
    bw.close()
    br.close()
}