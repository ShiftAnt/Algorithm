fun ArrayDeque<Int>.toNum(): String {
	val ret = StringBuilder()
	for (ele in this) {
		ret.append(ele)
	}
	return ret.toString()
}

fun main() {
	var n = readLine()!!.toInt()
	val num = ArrayDeque<Int>()
	num += 0

	while (n-- != 0) {
		val siz = num.size
		var idx = 0
		for (i in siz - 2 downTo 0) {
			if (num[i] - num[i + 1] != 1) {
				idx = i + 1
				break
			}
		}
		if (idx == 0 && num[idx] == 9) {
			if (num.size >= 10) {
				println("-1")
				return
			}
			num.addFirst(num.size)
		}
		else ++num[idx]
		for (i in num.size - 1 downTo idx + 1) {
			num[i] = num.size - 1 - i
		}
	}
	println(num.toNum())
}