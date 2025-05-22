/**
* https://www.hackerrank.com/challenges/absolute-permutation/problem?isFullScreen=true
*/
fun absolutePermutation(n: Int, k: Int): Array<Int> {
    val list = Array(n) { it.inc() }
    val result = Array(n) { 0 }
    if (k == 0) return list
    // What a headache to find this formula
    // permutation requires a repeating pattern of swaps over segments of size 2 * k
    // Why 2k? because the threshold is (index + k) or (index - k)
    // Thus, if the result is not even, return -1 since the pattern will break
    if (n % (2 * k) != 0) return arrayOf(-1)

    for(i in list.indices) {
        // Alternate between +k and -k in fixed-size blocks to avoid conflicts
        // Ensures that elements are swapped in blocks of size k
        // while maintaining the condition |P(i) - i| = k.
        val isEven = i / k % 2 == 0
        result[i] = i.inc() + if (isEven) k else -k
    }
    return result
}
