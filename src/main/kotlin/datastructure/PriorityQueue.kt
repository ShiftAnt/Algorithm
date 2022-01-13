package datastructure

class PriorityQueue<T> (
    private val comparator: Comparator<T>
    = compareBy { t: T ->
        if (t is Comparable<*>) t
        else throw ClassCastException()
    },
    initialCapacity: Int = 11
) {

    var size: Int = 0
    private var queue = Array<Any?>(initialCapacity) {null}

    fun isEmpty(): Boolean = size == 0

    fun push(element: T): Boolean {
        val i = size

        if (i >= queue.size) {
            extendCapacity()
        }
        insertQueue(i, element)

        size = i + 1
        return true
    }

    private fun extendCapacity() {
        val oldCapacity = queue.size

        val newCapacity = oldCapacity + if (oldCapacity < 64) 2 else oldCapacity / 2

        queue = queue.copyOf(newCapacity)
    }

    private fun insertQueue(i: Int, element: T) {
        queue[i] = element

        pushHeapify(i)
    }

    private fun pushHeapify(i: Int) {
        var child = i
        var parent = (i - 1) / 2

        while (parent in 0 until child) {
            val childElement = queue[child] as T
            val parentElement = queue[parent] as T
            if (comparator.compare(parentElement, childElement) > 0) {
                queue[child] = queue[parent].also { queue[parent] = queue[child] }
                child = parent
                parent = (child - 1) / 2
            } else break
        }
    }
    fun top(): T = if (isEmpty()) throw IndexOutOfBoundsException() else queue[0] as T


    fun pop(): T {
        val topElement = top()
        val i = size

        deleteQueue(0, i - 1)

        size = i - 1

        return topElement
    }

    private fun deleteQueue(i: Int, maxSize: Int) {
        queue[i] = queue[maxSize]
        queue[maxSize] = null
        popHeapify(i, maxSize)
    }
    private fun popHeapify(i: Int, maxSize: Int) {
        var current = i

        while (current < maxSize) {
            val nextChild = compareChild(current, maxSize)
            if (nextChild == -1) break
            if (comparator.compare(queue[current] as T, queue[nextChild] as T) > 0) {
                queue[current] = queue[nextChild].also { queue[nextChild] = queue[current] }
                current = nextChild
            } else break
        }
    }

    private fun compareChild(parent: Int, maxSize: Int): Int {
        val leftChild = parent * 2 + 1
        val rightChild = parent * 2 + 2
        if (rightChild >= maxSize) {
            return if (leftChild < maxSize) leftChild
            else -1
        }
        return if (queue[leftChild] == null && queue[rightChild] == null) -1
        else if (queue[leftChild] == null) rightChild
        else if (queue[rightChild] == null) leftChild
        else if (comparator.compare(queue[leftChild] as T, queue[rightChild] as T) < 0) leftChild
        else rightChild
    }
}
