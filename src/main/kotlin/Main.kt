class Trie(
	val nxt: Array<Trie?> = Array(10) {null},
	var isEnd: Boolean = false
)

fun solution() {
	var ret = "YES"
	val root = Trie()
	val n = readLine()!!.toInt()
	val P = Array(n) { readLine()!! }.sortedBy { it.length }
	for (nums in P) {
		var cur = root
		if (ret == "NO") continue
		for (j in nums.indices) {
			val num = nums[j] - '0'
			cur = cur.nxt[num] ?: Trie().also { cur.nxt[num] = it }
			if (cur.isEnd) {
				ret = "NO"
				break
			}
			if (j == nums.lastIndex) cur.isEnd = true
		}
	}
	println(ret)
}

fun main() {
	repeat(readLine()!!.toInt()) {
		solution()
	}
}