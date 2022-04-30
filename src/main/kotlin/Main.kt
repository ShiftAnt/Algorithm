fun main() {
	readLine()!!.let { str ->
		arrayListOf<String>().let { lst ->
			for (i in str.indices) lst += str.substring(i)
			lst.sort()
			println(lst.joinToString("\n"))
		}
	}
}