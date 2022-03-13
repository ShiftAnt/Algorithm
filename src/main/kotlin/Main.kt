import java.io.*

var n = 0
var al = arrayOf<ArrayList<Int>>()
var vstd = booleanArrayOf()

var ret = 0
var early = booleanArrayOf()
fun dfs(idx: Int) {
    vstd[idx] = true
    var childIdea = false
    for (nxt in al[idx]) {
        if (!vstd[nxt]) {
            dfs(nxt)
            if (!early[nxt]) {
                childIdea = true
            }
        }
    }
    if (childIdea) {
        early[idx] = true
        ++ret
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    n = br.readLine().toInt()
    al = Array(n) {ArrayList()}
    repeat(n - 1) {
        br.readLine().split(" ").map { it.toInt() - 1 }.let {
            al[it[0]].add(it[1])
            al[it[1]].add(it[0])
        }
    }
    early = BooleanArray(n)
    vstd = BooleanArray(n)
    dfs(0)
    println(ret)
}