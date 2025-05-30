/**
* https://www.hackerrank.com/challenges/new-year-chaos/
*/

private const val LIMIT = 2

fun minimumBribes(q: Array<Int>): Unit {
    var bribes = 0
    for (i in q.size.dec() downTo 0) {
        val gap = q[i] - i.inc()
        if (gap > LIMIT) {
            // means bribes > 2
            // [1 2 3 4 5] to [1 5 2 3 4]
            println("Too chaotic")
            return
        }
        // check the trail, to see if q[i] is taking bribes
        // e.g [1 2 5 3 7 8 6 4], and you are in i == 7
        // q[i] - 2 = 4 - 2 = 2, j in max(0, 2) until 7
        // means 5, 7, 8, & 6 bribes 4, or q[j] > q[i]
        // and continue
        // i = 6, j in 4 until 6. 7 & 8 bribes 6
        // and so on until 3 taking bribes for 5
        // thus the answer = 4 + 2 + 1 = 7
        for (j in  Math.max(0, q[i] - LIMIT) until i) {
            if (q[j] > q[i]) {
                bribes++
            }
        } 
    }
    println(bribes)
}
