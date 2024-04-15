import org.http4k.client.JavaHttpClient
import org.http4k.core.Method
import org.http4k.core.Request

data class Benchmark(
    val id: Int,
    val warmups: Int,
    val iterations: Int,
    val range: IntRange
)

val benchmark = Benchmark(1, 10, 500, 0..100)

fun main(args: Array<String>) {
    val url = args[0]

    val times = run(benchmark, url)
    val totalTime = times.sum()
    val averageTime = times.average()
    println("total: $totalTime ms")
    println("average: $averageTime ms")
}

fun run(benchmark: Benchmark, url: String): List<Long> {
    val client = JavaHttpClient()

    println("--- benchmark ${benchmark.id} ---")

    println("warming up (${benchmark.warmups})")
    for (i in 1..benchmark.warmups) {
        val request = request(url, benchmark.range.random())
        client(request)
    }

    println("benchmark (${benchmark.iterations})")
    val times = mutableListOf<Long>()
    for (i in 1..benchmark.iterations) {
        when (i) {
            1 -> println("0%")
            benchmark.iterations / 4 -> println("25%")
            benchmark.iterations / 2 -> println("50%")
            3 * benchmark.iterations / 4 -> println("75%")
            benchmark.iterations -> println("100%")
        }

        val start = System.currentTimeMillis()

        val request = request(url, benchmark.range.random())
        client(request)

        val end = System.currentTimeMillis()
        times.add(end - start)
    }

    println()

    return times
}

fun request(url: String, number: Int) = Request(Method.GET, url)
    .query("number", number.toString())
