fun main() {
	val regex = Regex("((100+1+)|(01))+")
	repeat(readLine()!!.toInt()) {
		println(if (regex.matches(readLine()!!)) "YES" else "NO")
	}
}