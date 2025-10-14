const val SYMBOLS = "ඞ⛟⚠☠" // Símbolos para usar nos pares (configurável).
const val SIZE = SYMBOLS.length * 2 // Número total de posições.
const val MAX_TRIES = SIZE + 1 // Máximo de tentativas para descobrir os pares.
fun main() {
    val secretPairs = generateSecretPairs() // ex: [%, %, #, O, O, X, #, X]
    var showedPairs = generateAllHidden() // ex: [_, _, _, _, _, _, _, _]
    var tries = 1
    println(secretPairs)
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
            else printTemporarily(textOfTry(tries, view).toString(), 4)
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


//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
//Código implementado


// Gera uma lista, ou uma String, com os pares de símbolos distribuídos aleatoriamente.
fun generateSecretPairs(): List<Char> {
    val symbolList: List<Char> = (SYMBOLS + SYMBOLS).toList().shuffled()
    return symbolList
}

// Retorna uma lista, ou uma String, com SIZE caracteres '_'
fun generateAllHidden(): List<Char> {
    val underscore = "_".repeat(SIZE)
    val initList: List<Char> = (underscore).toList()
    return initList
}

// Apresenta as duas linhas da mensagem inicial.
// 1ª linha: Foram gerados <n> pares aleatórios de símbolos.
// 2ª linha: Vamos descobrir os pares em <m> tentativas!
fun startMessage() {
    println("Foram gerados ${SIZE/2} pares aleatórios de símbolos.")
    println("Vamos descobrir os pares em ${SIZE + 1} tentativas!")
}

// Retorna o texto com a tentativa atual no formato: Tentativa <trys>: <showed>
// Exemplo: "Tentativa 6: [%, %, _, _, _, X, _, X]"
//      ou: "Tentativa 6: %%___X_X"
fun textOfTry(a: Int, b: List<Char>) {
    print("Tentativa $a: $b")
}

// Lê e retorna a posição do símbolo a virar, introduzida pelo utilizador,
// fazendo a pergunta: "<n>ª posição a mostrar: "
fun readPosition(position: Int): Int {
    print("$position ª posição a mostrar: ")
    val userPosition = readln().toInt()
    return userPosition
}

// Verifica a validade das duas posições indicadas.
// Se são indices no intervalo 0..<SIZE de posições escondidas e são diferentes.
fun isValidPositions(position1: Int, position2: Int, secretPairs: List<Char>): Boolean {
    if (((position1 != position2) && (position1 in secretPairs.indices) && (position2 in secretPairs.indices))) {
        return true
    }
    else {
    return false }
}

// Retorna os pares já descobertos mais os símbolos nas posições indicadas.
fun showPositions(a: List<Char>, b: Int, c: Int, d: List<Char>): List<Char> {
    val showList: MutableList<Char> = a.toMutableList()
    showList[b] = d[b]
    showList[c] = d[c]
    return showList
}

// Verifica se todos os pares já foram descobertos
fun allReveal(showedPairs: List<Char>): Boolean {
    for (i in 0..<SIZE - 1) {
        if (showedPairs[i] != '_') {
            continue
        } else {
            return false
        }
    }
    return true
}

// Apresenta as duas linhas da mensagem final.
// 1ª linha: "Esgotou as tentativas." ou "Parabéns, descobriu em <trys> tentativas."
// 2ª linha:  "Tentativa <trys>: <showed>"
fun endMessage(t: Int, s: List<Char>) {
    if (t > MAX_TRIES) {
        println("Esgotou as tentativas.")
    }
    else {
        println("Parabéns, descobriu em $t tentativas.")
        println("Tentativa $t: $s")
    }
}

