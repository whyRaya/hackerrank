/**
 * https://www.hackerrank.com/challenges/3d-surface-area/problem?isFullScreen=true
 * The function is expected to return an INTEGER.
 * The function accepts 2D_INTEGER_ARRAY A as parameter.
 * e.g
 * | 1 |
 * or
 * | 1 3 4 |
 * | 2 2 3 |
 * | 1 2 4 |
 */

fun surfaceArea(a: Array<Array<Int>>): Int {
    var counter = 0
    for (i in a.indices) {
        for (j in 0 until a[i].size) {
            val v = a[i][j]
            // the magic to count the cube side
            // 1 cube will have 6 sides, while 3 cubes will have 14 sides
            val r = v * 4 + 2
            // Then count the cube side, and remove the intersecting part
            // 1 cube and then 3 cubes, will have 1 side intersect, thus use min
            // calculate with prev i or prev j
            counter += when {
                i == 0 && j == 0 -> r
                i == 0 -> r - v.coerceAtMost((a[i][j.dec()])) * 2
                j == 0 -> r - v.coerceAtMost((a[i.dec()][j])) * 2
                else -> {
                    val prevH = v.coerceAtMost((a[i.dec()][j])) * 2
                    val prevV = v.coerceAtMost((a[i][j.dec()])) * 2
                    r - prevH - prevV
                }
            }
        }
    }
    return counter
}
