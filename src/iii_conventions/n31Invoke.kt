package iii_conventions

import util.TODO


class Invokable{
    var x: Int = 0

    operator fun invoke():Invokable{
        x += 1
        return this
    }

    fun getNumberOfInvocations(): Int = x
}

fun todoTask31(): Nothing = TODO(
    """
        Task 31.
        Change the class 'Invokable' to count the number of invocations:
        for 'invokable()()()()' it should be 4.
    """,
    references = { invokable: Invokable -> })

fun task31(invokable: Invokable): Int {
//    todoTask31()
    return invokable()()()().getNumberOfInvocations()
}
