import java.io.*
fun atob(num: Int, a: Int, b: Int) {
	if (num == 1) {
		bw.write("$a $b\n")
		return
	}
	atob(num - 1, a, 6 - a - b)
	bw.write("$a $b\n")
	atob(num - 1, 6 - a - b, b)
}
val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
	val n = readLine()!!.toInt()
	bw.write("${1.shl(n) - 1}\n")
	atob(n, 1, 3)
	bw.flush()
}