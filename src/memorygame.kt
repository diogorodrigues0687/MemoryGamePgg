const val SYMBOLS = "0123" // Símbolos para usar nos pares (configurável).
const val SIZE = SYMBOLS.length * 2 // Número total de posições.
const val MAX_TRIES = SIZE + 1 // Máximo de tentativas para descobrir os pares.
fun main() {
    val secretPairs = generateSecretPairs() // ex: [%, %, #, O, O, X, #, X]
    var showedPairs = generateAllHidden() // ex: [_, _, _, _, _, _, _, _]
    var tries = 1
    startMessage()
    do {
        println(textOfTry(tries, showedPairs))
        val p1: Int = readPosition(1)
        val p2: Int = readPosition(2)
        if ( isValidPositions(p1, p2, showedPairs) ) {
            val view = showPositions(showedPairs, p1, p2, secretPairs)
            if (view[p1] == view[p2]) {
                showedPairs = view
                if (allReveal(showedPairs)) break
            }
            else printTemporarily(textOfTry(tries, view), 4)
            tries++
        } else println("Posições inválidas")
    } while( tries <= MAX_TRIES )
    endMessage(tries, secretPairs)
}
fun printTemporarily(text: String, seconds: Int) {
    print(text) // Mostra o texto
    Thread.sleep(seconds * 1000L) // Espera o tempo
    print("\r" + " ".repeat(text.length) + "\r") // Apaga o texto
}

// Gera uma lista, ou uma String, com os pares de símbolos distribuídos aleatoriamente.
//fun generateSecretPairs() ...

// Retorna uma lista, ou uma String, com SIZE caracteres '_'
//fun generateAllHidden() ...

// Apresenta as duas linhas da mensagem inicial.
// 1ª linha: Foram gerados <n> pares aleatórios de símbolos.
// 2ª linha: Vamos descobrir os pares em <m> tentativas!
//fun startMessage() ...

// Retorna o texto com a tentativa atual no formato: Tentativa <trys>: <showed>
// Exemplo: "Tentativa 6: [%, %, _, _, _, X, _, X]"
//      ou: "Tentativa 6: %%___X_X"
//fun textOfTry(...) ...

// Lê e retorna a posição do símbolo a virar, introduzida pelo utilizador,
// fazendo a pergunta: "<n>ª posição a mostrar: "
//fun readPosition(...) ...

// Verifica a validade das duas posições indicadas.
// Se são indices no intervalo 0..<SIZE de posições escondidas e são diferentes.
//fun isValidPositions(...) ...

// Retorna os pares já descobertos mais os símbolos nas posições indicadas.
//fun showPositions(...) ...

// Verifica se todos os pares já foram descobertos.
//fun allReveal(...) ...

// Apresenta as duas linhas da mensagem final.
// 1ª linha: "Esgotou as tentativas." ou "Parabéns, descobriu em <trys> tentativas."
// 2ª linha:  "Tentativa <trys>: <showed>"
//fun endMessage(...) ...

fun generateSecretPairs(): List {}

fun generateAllHidden(): List<Pair<Int, Int>> {}

fun startMessage() {}

fun textOfTry(tries: Int, showedPairs: Int, secretPairs: List<Pair<Int, Int>>): Boolean {}

fun readPosition(position: Int): Pair<Int, Int> {}

fun isValidPositions(position1: Int, position2: Int, secretPairs: List<Pair<Int, Int>>): Boolean {}

fun showPositions(showedPairs: Int, secretPairs: List<Pair<Int, Int>>): List<Pair<Int, Int>> {}

fun allReveal(showedPairs: Int): Boolean {}

fun endMessage(tries: Int, secretPairs: List<Pair<Int, Int>>) {}
