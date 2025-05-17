package com.whyraya.playground.utils

const val NO_ANSWER = "no answer"

/**
 * The solution is split into 3 parts
 * */
fun biggerIsGreater(w: String): String {
    // if word is number
    if (w.toLongOrNull() != null) return ""
    val size = w.length - 1
    var gap = 99
    var index = 0
    val word = w.toMutableList()
    // 1. Check the smallest changes, backward
    // e.g., hefg just need to swap f & g to become hegf
    // But for dkhc, it would just become kdhc & it's wrong,
    for (i in size downTo 1) {
        val s = word[i.dec()]
        val e = word[i]
        if (e > s) {
            index = i
            word[i] = s
            word[i.dec()] = e
            break
        }
    }
    // No changes, e.g ba, no changes, no index changed
    if (index == 0) return NO_ANSWER

    // 2. Check if there are any char that exist in between
    // e.g dkhc to kdhc, we need to check e in d..k
    // And then, we've found hdkc
    // And watch the gap, since it can be f or h in d..k
    val f = w[index.dec()]
    for (i in index..size) {
        val e = word[i]
        val h = word[index.dec()]

        if (e != f && e in f..h && e-f < gap) {
            gap = e-f
            word[i] = h
            word[index.dec()] = e
        }
    }

    // lastly, just sort every char after the index
    // e.g hdkc, since the index comes down 1, so we need to sort dkc
    // and the result is hcdk
    for (i in index..size.dec()) {
        for (u in i.inc()..size) {
            if (word[i] > word[u]) {
                val temp0 = word[i]
                val temp1 = word[u]
                word[i] = temp1
                word[u] = temp0
            }
        }
    }
    // Just compare the string, if equals means no changes/NO_ANSWER
    return word.joinToString("").let {
        if (!it.equals(w, true)) it else NO_ANSWER
    }
}
