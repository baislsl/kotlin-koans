package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)
    : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        val v1 = dayOfMonth + 100 * (month + year * 100)
        val v2 = other.dayOfMonth + 100 * (other.month + other.year * 100)
        return if (v1 < v2) -1 else if (v1 == v2) 0 else 1
    }

    operator fun plus(other: TimeInterval): MyDate {
        return plus(TimeIntervalMul(other, 1))
    }

    operator fun plus(other: TimeIntervalMul): MyDate {
        return this.addTimeIntervals(other.timeInterval, other.i)
    }

}

//
operator fun MyDate.rangeTo(other: MyDate): DateRange {
    return DateRange(this, other)
}

data class TimeIntervalMul(val timeInterval: TimeInterval, val i: Int)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    operator fun times(i: Int): TimeIntervalMul = TimeIntervalMul(this, i)
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate)
    : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> = object : Iterator<MyDate> {
        private var cur = start
        override fun next(): MyDate {
            val ret = cur
            cur = cur.nextDay()
            return ret
        }

        override fun hasNext(): Boolean = (cur.compareTo(endInclusive) <= 0)
    }

//    private data class MyDateIterator(val start: MyDate, val end: MyDate) : Iterator<MyDate> {
//        private val cur = start
//        override fun next(): MyDate {
//            return cur.nextDay()
//        }
//
//        override fun hasNext(): Boolean {
//            return cur.compareTo(end) == 0
//        }
//
//    }
}


