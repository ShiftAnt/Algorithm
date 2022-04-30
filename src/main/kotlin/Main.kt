import java.io.*

fun main() {
	val bw = BufferedWriter(OutputStreamWriter(System.out))
	val n = readLine()!!.toInt()
	val st = java.util.StringTokenizer(readLine()!!)
	val stack = java.util.Stack<Pair<Int, Int>>()
	for (i in 0 until n) {
		val cur = st.nextToken().toInt()

		while (stack.isNotEmpty()) {
			if (stack.peek().second < cur) {
				stack.pop()
			} else break
		}
		bw.write(if (stack.isEmpty()) "0 " else "${stack.peek().first + 1} ")
		stack += i to cur
	}
	bw.flush()
}