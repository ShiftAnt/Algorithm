package datastructure

import java.util.*

class Matrix (
    private val rowNum: Int,
    private val columnNum: Int
        ) {
    private var matrix = arrayOf(intArrayOf())
    init {
        if (rowNum <= 0 || columnNum <= 0) throw IndexOutOfBoundsException()
        matrix = Array(rowNum) {IntArray(columnNum)}
    }
    companion object {
        fun unitMatrix(rowNum: Int): Matrix {
            val ret = Matrix(rowNum, rowNum)
            for (i in 0 until rowNum) ret[i][i] = 1
            return ret
        }
    }

    private fun isSameNumRowColumn(m: Matrix) = this.rowNum == m.rowNum && this.columnNum == m.columnNum

    private fun isSameNumThisRowOpponentColumn(m: Matrix) = this.rowNum == m.columnNum

    operator fun get(rowIndex: Int) = matrix[rowIndex]

    operator fun plus(m: Matrix): Matrix {
        if (!isSameNumRowColumn(m)) throw IndexOutOfBoundsException()

        val ret = Matrix(rowNum, columnNum)
        for (i in 0 until rowNum) {
            for (j in 0 until columnNum) {
                ret[i][j] = this[i][j] + m[i][j]
            }
        }
        return ret
    }

    operator fun minus(m: Matrix): Matrix {
        if (!isSameNumRowColumn(m)) throw IndexOutOfBoundsException()

        val ret = Matrix(rowNum, columnNum)
        for (i in 0 until rowNum) {
            for (j in 0 until columnNum) {
                ret[i][j] = this[i][j] - m[i][j]
            }
        }
        return ret
    }

    operator fun times(m: Matrix): Matrix {
        if (!isSameNumThisRowOpponentColumn(m)) throw InputMismatchException()

        val ret = Matrix(rowNum, columnNum)
        for (i in 0 until rowNum) {
            for (j in 0 until columnNum) {
                for (idx in 0 until rowNum) {
                    ret[i][j] += this[i][idx] * m[idx][1]
                }
            }
        }
        return ret
    }

    override operator fun equals(other: Any?): Boolean {
        if (other is Matrix) {
            if (super.equals(other)) return true
            for (i in 0 until rowNum) {
                for (j in 0 until columnNum) {
                    if (this[i][j] != other[i][j]) return false
                }
            }
            return true
        }
        return false
    }

    fun initRightDown(vararg elements: Int) {
        for (j in 0 until columnNum) {
            for (i in 0 until rowNum) {
                val index = i * columnNum + j
                if (index >= elements.size) return
                this[i][j] = elements[index]
            }
        }
    }

    fun initDownRight(vararg elements: Int) {
        for (i in 0 until rowNum) {
            for (j in 0 until columnNum) {
                val index = i * rowNum + j
                this [i][j] = if (index >= elements.size) 0 else elements[index]
                this[i][j] = elements[index]
            }
        }
    }

    override fun toString(): String {
        val ret = StringBuilder()
        for (i in 0 until rowNum) {
            for (j in 0 until columnNum) {
                ret.append(this[i][j].toString()).append(" ")
            }
            ret.append("\n")
        }
        return ret.toString()
    }
}