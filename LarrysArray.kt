package com.whyraya.playground.utils

/**
 * Tricky challenge where you need to sort, but only per 3 consecutive indices
 * Tips:
 * Always put the smallest value in the front, even if the sorting is wrong.
 * E.g. rotate [8, 7, 1]  to [1, 8, 7], or [6, 5, 10] to [5, 10, 6]
 * Since it can be fixed in the next iteration
 *
 * And put a break, e.g, when the result is similar or cannot be sorted
 * to prevent OOM
 *
 * Lastly, compare the result with the sorted one
 * E.g [1,2,3,5,4] vs [1,2,3,4,5] which will return 'NO'
 * */
fun larrysArray(a: Array<Int>): String {
    val maxIndex = a.size.dec()
    var changed = true
    while (changed) {
        // copy, to check if there's any changes. Else cannot be sorted & stop while
        val b = a.copyOf()
        for (i in 0..(maxIndex - 2)) {
            val s1 = a[i]
            val s2 = a[i + 1]
            val s3 = a[i + 2]
            // else, skip, already sorted, 
            if (s1 > s2 || s2 > s3) {
                val sub = arrayOf(s1, s2, s3)
                sub.sort()
                // since the format of 3 consecutive indices will result in 3 forms, ABC or:
                val first = "$s2$s3$s1" // BCA
                val second = "$s3$s1$s2" // CAB
                val subSort = sub.joinToString("")
                when {
                    subSort == first || s2 < s1 -> {
                        // match first or sorting is not match, but the 2nd value is smaller
                        a[i] = s2
                        a[i + 1] = s3
                        a[i + 2] = s1
                    }

                    subSort == second || s3 < s1 -> {
                        // match second or sorting is not match, but the 3rd value is smaller
                        a[i] = s3
                        a[i + 1] = s1
                        a[i + 2] = s2
                    }
                }
                // else, skip, cannot be sorted with rotation. e.g (3, 5, 4) 
                // 3 > 5 false but 5 > 4 true. But the smallest one is already in the front `3`
            }
        }
        changed = a.joinToString(",") != b.joinToString(",")
    }
    val b = a.copyOf()
    b.sort()
    return if (b.joinToString("") != a.joinToString("")) "NO" else "YES"
}
