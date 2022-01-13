package datastructure

object Sort {
    fun selectionSort(arr: IntArray) {
        for (i in 1 until arr.size) {
            val tmp = arr[i]
            for (j in i - 1 downTo -1) {
                if (j != -1 && arr[j] > tmp) arr[j + 1] = arr[j]
                else {
                    arr[j + 1] = tmp
                    break
                }
            }
        }
    }

    fun mergeSort(arr: IntArray, stt: Int = 0, end: Int = arr.size, tmpArr: IntArray = IntArray(arr.size)) {
        if (end - stt <= 1) return
        val mid = (stt + end) / 2

        mergeSort(arr, stt, mid)
        mergeSort(arr, mid, end)
        merge(arr, stt, end, tmpArr)
    }
    private fun merge(arr: IntArray, stt: Int, end: Int, tmpArr: IntArray) {
        val mid = (stt + end) / 2
        var idxA = stt
        var idxB = mid
        var idx = stt

        while (idxA < mid && idxB < end) {
            tmpArr[idx++] = if (arr[idxA] < arr[idxB]) arr[idxA++] else arr[idxB++]
        }
        while (idxA < mid) {
            tmpArr[idx++] = arr[idxA++]
        }
        while (idxB < end) {
            tmpArr[idx++] = arr[idxB++]
        }
        for (i in stt until end) {
            arr[i] = tmpArr[i]
        }
    }
}