package dk.kalhauge.numbers

data class Rational(val numerator: Int, val denominator: Int)

operator fun Rational.times(other: Rational) =
    Rational(this.numerator*other.numerator, this.denominator*other.denominator)

operator fun Int.times(other: Rational) =
        Rational(this*other.numerator, other.denominator)

fun main(args: Array<String>) {
    val x = Rational(2, 5)
    val pi = Rational(22, 7)
    println("$x * $pi = ${x*pi}")
    val r = 34*pi*x
    val q = 34.times(pi).times(x)
    println("34*pi = ${34*pi}")
    }

