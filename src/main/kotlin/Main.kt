import java.io.*

const val YES = "YES"
const val NO = "NO"
fun isSlump(s: String): Boolean {
	if (s.length < 3 || s[0] != 'D' && s[0] != 'E' || s[1] != 'F') return false
	var fIdx = 2
	for (i in 2 until s.length) {
		if (s[i] == 'F') fIdx = i + 1
		else break
	}
	return s.substring(fIdx, s.length).let {
		it == "G" || isSlump(it)
	}
}
fun isSlimp(s: String): Boolean {
	if (s.length < 2 || s[0] != 'A') return false
	if (s.length == 2) return s[1] == 'H'
	if (s.last() != 'C') return false
	if (s[1] == 'B') return isSlimp(s.substring(2, s.length - 1))
	return isSlump(s.substring(1, s.length - 1))
}
fun solution(str: String): String {
	var ret = NO
	for (i in 1 until str.length) {
		if (isSlimp(str.substring(0, i)) && isSlump(str.substring(i))) {
			ret = YES
			break
		}
	}
	return ret
}
fun main() {
	val bw = BufferedWriter(OutputStreamWriter(System.out))
	bw.write("SLURPYS OUTPUT\n")
	repeat(readLine()!!.toInt()) {
		bw.write(solution(readLine()!!) + "\n")
	}
	bw.write("END OF OUTPUT\n")
	bw.flush()
}