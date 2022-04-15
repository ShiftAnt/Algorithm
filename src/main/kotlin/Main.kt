fun main() {
	while (true) {
		var (a, b) = readLine()!!.split(" ").map { it.toInt() }
		if (a == 0) break
		var isA = true
		while (true) {
			if (a < b) a = b.also { b = a }
			if (a / b > 1) break
			a -= b
			if (a == 0) break
			isA = !isA
		}
		print("${if (isA) 'A' else 'B'} wins\n")
	}
}