import java.util.*
import kotlin.math.*

fun include(tx: Long, ty: Long, rx1: Long, ry1: Long, rx2: Long, ry2: Long)
    = tx in min(rx1, rx2)..max(rx1, rx2) && ty in min(ry1, ry2)..max(ry1, ry2)

fun solution(): Int {
    val sc = Scanner(System.`in`)

    val x = LongArray(4)
    val y = LongArray(4)
    repeat(8) {
        if (it % 2 == 0) x[it / 2] = sc.nextLong()
        else y[it / 2] = sc.nextLong()
    }
    val a = (x[0] - x[1]) * (y[2] - y[3]) - (y[0] - y[1]) * (x[2] - x[3])
    val b = (x[0] * y[1] - y[0] * x[1]) * (x[2] - x[3]) - (x[0] - x[1]) * (x[2] * y[3] - y[2] * x[3])
    val c = (x[0] * y[1] - y[0] * x[1]) * (y[2] - y[3]) - (y[0] - y[1]) * (x[2] * y[3] - y[2] * x[3])
    if (a == 0L) {
        if ((y[2] - y[0]) * (x[3] - x[0]) == (x[2] - x[0]) * (y[3] - y[0])) {
            var ret = false
            repeat(2) {
                ret = ret.or(include(x[it], y[it], x[2], y[2], x[3], y[3]))
                ret = ret.or(include(x[it + 2], y[it + 2], x[0], y[0], x[1], y[1]))
            }
            return if (ret) 1 else 0
        }
        return 0
    }
    repeat(4) {
        x[it] *= a
        y[it] *= a
    }
    return if (include(b, c, x[0], y[0], x[1], y[1]) && include(b, c, x[2], y[2], x[3], y[3])) 1 else 0
}

fun main() {
    println(solution())

}