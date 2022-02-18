import java.io.*
import kotlin.math.*

var n = 0L
var k = 0
var q = 0

fun parent(num: Long) = (num + k - 2) / k

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))
	br.readLine().split(" ").map { it.toLong() }.let {
		n = it[0]
		k = it[1].toInt()
		q = it[2].toInt()
	}

	for (i in 0 until q) {
		var (a, b) = br.readLine().split(" ").map { it.toLong() }
		var ret = 0
		if (k == 1) {
			bw.write("${abs(a - b)}\n")
			continue
		}
		while (a != b) {
			if (a > b) a = parent(a) else b = parent(b)
			++ret
		}
		bw.write("$ret\n")
	}
	bw.close()
}