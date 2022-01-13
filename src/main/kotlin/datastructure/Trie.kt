package datastructure

const val MAX_ALPA_INDEX = 26

class Trie {
    private inner class Node (
        val value: String = ""
    ) {
        val children = Array<Node?>(MAX_ALPA_INDEX) {null}
        operator fun get (index: Int): Node? {
            return this.children[index]
        }

        operator fun set(index: Int, value: Trie.Node?) {
            this.children[index] = value
        }

        fun isEmptyChild(): Boolean {
            for (child in this.children) {
                if (child != null) return false
            }
            return true
        }
    }
    private val root = Node("")

    fun add(element: String) {
        var current = root
        for (char in element) {
            val charIndex = charIndex(char)
            current[charIndex] = current[charIndex] ?: Node("${current.value}$char")
            current = current[charIndex]!!
        }
    }

    private fun charIndex(char: Char) = char - 'a'

    private fun backTrackingRemove(index: Int, node: Node, element: String): Boolean {
        if (index >= element.length) return true
        val charIndex = charIndex(element[index])
        if (node[charIndex] == null) throw NoSuchElementException()
        if (backTrackingRemove(index + 1, node[charIndex]!!, element)) node[charIndex] = null
        return node.isEmptyChild()
    }

    fun remove(element: String) {
        backTrackingRemove(0, root, element)
    }
    fun contains(element: String): Boolean {
        var current = root
        for (char in element) {
            val charIndex = charIndex(char)
            if (current[charIndex] == null) return false
            current = current[charIndex]!!
        }
        return true
    }
    operator fun get(element: String): Boolean {
        return contains(element)
    }

    operator fun plusAssign(element: String) {
        add(element)
    }

    operator fun minusAssign(element: String) {
        remove(element)
    }
}