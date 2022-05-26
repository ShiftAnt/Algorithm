fun main() {
	val (a, b) = readLine()!!.split(" ")
	val stt = StringBuilder()
	repeat(b.length - a.length) {
		stt.append("0")
	}
	stt.append(a)
	var ret = 0
	for (i in b.indices) {
		if (stt[i] == b[i]) {
			if (b[i] == '8') ++ret
		}
		else break
	}
	println(ret)
}