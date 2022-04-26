import java.math.*

fun main() {
	var a: BigDecimal
	var b: Int
	readLine()!!.split(" ").let {
		a = BigDecimal(it[0])
		b = it[1].toInt()
	}
	var sub = a
	var ret = BigDecimal(1.0)
	while (b != 0) {
		if (b % 2 != 0) ret *= sub
		sub *= sub
		b /= 2
	}
	println(ret.toPlainString())
}