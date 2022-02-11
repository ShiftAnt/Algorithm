import java.io.*

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	val (n, w, L) = br.readLine().split(" ").map { it.toInt() }

	val ts = br.readLine().split(" ").map { it.toInt() }

	val que = ArrayDeque<Int>()

	var time = 0
	var sum = 0
	var idx = 0
	while (true) {
		if (idx == n) {
			time += w
			break
		}
		++time
		if (que.size < w) {
			if (sum + ts[idx] <= L) {
				que += ts[idx]
				sum += ts[idx++]
			} else que += 0
		} else {
			sum -= que.removeFirst()
			if (sum + ts[idx] <= L) {
				que += ts[idx]
				sum += ts[idx++]

			} else que += 0
		}
	}

	bw.write("$time")
	bw.close()
	br.close()
}