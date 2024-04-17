import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.server.SunHttp
import org.http4k.server.asServer

val app = { request: Request ->
    val number = request.query("number")!!.toInt()

    val primeFactors = primeFactors(number)

    Response(Status.OK).body(primeFactors.joinToString(" * "))
}

fun main() {
    val port = 8080

    app.asServer(SunHttp(port))
        .start()

    println("Listening on port $port")
}

fun primeFactors(number: Int): List<Int> {
    var remaining = number
    val primes = mutableListOf<Int>()
    val factors = mutableListOf<Int>()

    for (i in 2..number) {
        if (primes.any { i % it == 0 })
            continue

        primes.add(i)
        while (remaining % i == 0) {
            factors.add(i)
            remaining /= i
        }

        if (remaining == 1)
            return factors
    }

    return listOf(number)
}
