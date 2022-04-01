class Trie(
	val name: String = "",
	val nxt: HashMap<String, Trie> = HashMap()
)

fun hyphen(dept: Int) = StringBuilder().also { for (i in 0 until dept) it.append("--") }.toString()

fun dfs(cur: Trie, dept: Int) {
	if (dept != -1) {
		println("${hyphen(dept)}${cur.name}")
	}
	cur.nxt.values.sortedBy { it.name }.forEach { nxt ->
		dfs(nxt, dept + 1)
	}
}

fun main() {
	val root = Trie()
	repeat(readLine()!!.toInt()) {
		val st = java.util.StringTokenizer(readLine())
		var cur = root
		repeat(st.nextToken().toInt()) {
			val str = st.nextToken()
			cur = cur.nxt[str] ?: run { cur.nxt[str] = Trie(str); cur.nxt[str]!! }
		}
	}
	dfs(root, -1)
}