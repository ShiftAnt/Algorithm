import kotlin.math.*

fun gcf(a: Int, b: Int): Int {
	if (b == 0) return a
	return gcf(b, a % b)
}
fun main() {
	val n = readLine()!!.toInt()
	val st = java.util.StringTokenizer(readLine())
	val P = IntArray(n) {st.nextToken().toInt()}
	var ret = P[0] - P[1]

	for (i in 1 until n - 1) {
		ret = gcf(ret, P[i] - P[i + 1])
	}
	println(abs(ret))
}