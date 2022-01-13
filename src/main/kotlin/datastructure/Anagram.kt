package datastructure

class Anagram {
    fun dpArray(encoding: Encoding): IntArray {
        return when (encoding) {
            Encoding.ASCII -> IntArray(1.shl(7))
            Encoding.UNICODE -> IntArray(1.shl(16))
        }
    }
    enum class Encoding {
        ASCII, UNICODE
    }

    fun checkEncoding(str: String): Encoding {
        for (char in str) {
            if (char >= 128.toChar()) return Encoding.UNICODE
        }
        return Encoding.ASCII
    }

    fun anagram(str1: String, str2: String): Boolean {
        val str1Encoding = checkEncoding(str1)
        val str2Encoding = checkEncoding(str2)
        val encoding = if (str1Encoding == str2Encoding) str1Encoding else Encoding.UNICODE

        val dpStr1 = dpArray(encoding)
        val dpStr2 = dpArray(encoding)

        for (char in str1) {
            ++dpStr1[char.code]
        }
        for (char in str2) {
            ++dpStr2[char.code]
        }
        for (i in dpStr1.indices) {
            if (dpStr1[i] != dpStr2[i]) return false
        }
        return true
    }
}