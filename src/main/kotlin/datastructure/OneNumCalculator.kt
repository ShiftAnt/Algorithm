package datastructure

fun solution (str: String): Int {
    var ret = 0
    var tmp = 0
    var oper = 'N'
    var i = 0
    while (i < str.length) {
        if (str[i] in '0'..'9') {
            tmp = str[i] - '0'
        } else {
            when(str[i]) {
                '+', '-' -> {
                    if (oper != 'N') {
                        if (oper == '+') ret += tmp
                        else ret -= tmp
                    } else {
                        ret = tmp
                    }
                    tmp = 0
                    oper = str[i]
                }
                '*' -> {
                    tmp *= str[i + 1] - '0'
                    ++i
                }
                '/' -> {
                    tmp /= str[i + 1] - '0'
                    ++i
                }
            }
        }
        ++i
    }
    if (oper == '+' || oper == 'N') ret += tmp
    else ret -= tmp

    return ret
}