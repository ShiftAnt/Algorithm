import java.io.*
import java.util.PriorityQueue

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val pq = PriorityQueue<Int>()
	repeat(br.readLine().toInt()) {
		pq.add(br.readLine().toInt())
	}
	var ret = 0
	while (pq.size > 1) {
		val sub = pq.poll() + pq.poll()
		ret += sub
		pq.add(sub)
	}
	println(ret)
}