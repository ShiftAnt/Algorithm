import java.util.*
import kotlin.math.*

var P = LongArray(8)

fun check(): Int {
	val a = (P[0] - P[2]) * (P[5] - P[7]) - (P[1] - P[3]) * (P[4] - P[6])
	val b = (P[0] * P[3] - P[1] * P[2]) * (P[4] - P[6]) - (P[0] - P[2]) * (P[4] * P[7] - P[5] * P[6])
	val c = (P[0] * P[3] - P[1] * P[2]) * (P[5] - P[7]) - (P[1] - P[3]) * (P[4] * P[7] - P[5] * P[6])

	if (a == 0L) return 0

	repeat(8) {
		P[it] *= a
	}

	if (b == P[0] && c == P[1] || b == P[2] && c == P[3] || b == P[4] && c == P[5] || b == P[6] && c == P[7]) return 0

	val rx = min(P[0], P[2])..max(P[0], P[2])
	val ry = min(P[1], P[3])..max(P[1], P[3])

	if (b in rx && c in ry) return 1
	return 0
}

fun main() {
	val sc = Scanner(System.`in`)
	repeat(8) {
		P[it] = sc.nextLong()
	}
	print("${check()}")
}