package rationals
import java.sql.DriverManager.println

import java.math.BigInteger

class Rational (n:BigInteger, d:BigInteger) : Comparable<Rational>{

    private val n: BigInteger
    private val d: BigInteger

    constructor(n: BigInteger) : this(n,BigInteger.ONE)

    init {
        require(d != BigInteger.ZERO) { "The denominator cannot be zero!" }

        val g = n.gcd(d)

        if (d < BigInteger.ZERO){
            this.n = (n.times(-BigInteger.ONE)).div(g)
            this.d = (d.times(-BigInteger.ONE)).div(g)
        }else{
            this.n = n.div(g)
            this.d = d.div(g)
        }
    }

    override fun toString(): String {
        if (this.d == BigInteger.ONE) return "$n"
        return "$n/$d"
    }

    operator fun plus(a: Rational): Rational{
        return Rational(this.n * a.d + this.d * a.n, this.d * a.d)
    }

    operator fun minus(a: Rational): Rational{
        return Rational(this.n * a.d - this.d * a.n, this.d * a.d)
    }

    operator fun times(a: Rational): Rational{
        return Rational(this.n * a.n, this.d * a.d)
    }

    operator fun div(a: Rational): Rational{
        return Rational(this.n * a.d, this.d * a.n)
    }

    operator fun unaryMinus(): Rational{
        return Rational((this.n).times(-BigInteger.ONE), this.d)
    }

    override fun equals(other: Any?): Boolean {
        if((other is Rational)
                && this.n == other.n
                && this.d == other.d)
            return true

        return false
    }

    override operator fun compareTo(a: Rational): Int{
        if ((this.d == a.d)) return this.n.compareTo(a.n)
        return (this.n * a.d).compareTo(this.d * a.n)
    }

}

infix fun Int.divBy(d: Int): Rational {
    return Rational(this.toBigInteger(), d.toBigInteger())
}

fun String.toRational(): Rational{

    require(!this.isNullOrEmpty()) { "The parameter cannot be empty or null!" }

    val str = this

    val(dig, notDig) = str.partition { it.isDigit() || it == '/' || it == '-'}
    require(!notDig.isNotEmpty()) { "The parameter is not valid!" }

    if ('/' in str) {

        val tab = str.split("/")
        //require(tab.lastIndex != 1) { "The parameter is not valid!" }

        val n1 = str.split("/")[0]
        val n2 = str.split("/")[1]


        return n1.toBigInteger() divBy n2.toBigInteger()
    }else{
        return str.toBigInteger() divBy BigInteger.ONE
    }
}

infix fun Long.divBy(d: Long): Rational{

    val a1 = this.toBigInteger()
    val a2 = d.toBigInteger()
    println("n1= $a1  n2 = $a2")

    return this.toBigInteger() divBy d.toBigInteger()
}

infix fun BigInteger.divBy(d: BigInteger): Rational{
    return Rational(this, d)
}

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}