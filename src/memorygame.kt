const val SYMBOLS = "ඞ⛟⚠☠" // Símbolos para usar nos pares (configurável).
const val SIZE = SYMBOLS.length * 2 // Número total de posições.
const val MAX_TRIES = SIZE + 1 // Máximo de tentativas para descobrir os pares.
fun main() {
    val secretPairs = generateSecretPairs() // ex: [ඞ, ⛟, ඞ, ☠, ⚠, ⚠, ⛟, ☠]
    var showedPairs = generateAllHidden() // ex: [_, _, _, _, _, _, _, _]
    var tries = 1
    /*println(secretPairs)
    println("Apenas para teste, comentar ou descomentar se necessário")*/
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

/** Gera uma lista, ou uma String, com os pares de símbolos distribuídos aleatoriamente.
 * A val symbolList é uma lista de caracteres presentes em SYMBOLS.
 * SYMBOLS + SYMBOLS faz com que os caracteres em SYMBOLS se repitam 2 vezes (8 no total).
 * .toList() adiciona os caracteres á lista.
 * .shuffled() baralha os caracteres.
 * A lista de caracteres retornada é symbolList.**/
fun generateSecretPairs(): List<Char> {
    val symbolList: List<Char> = (SYMBOLS + SYMBOLS).toList().shuffled()
    return symbolList
}

/** Retorna uma lista, ou uma String, com SIZE caracteres '_'.
 * A val underscore ("_") é o caracter usado para a lista de caracteres que esconde os symbolos a serem descobertos.
 * .repeat(SIZE) repete o caracter SIZE vezes, pois a lista é desse comprimento.
 * A val initList vai então incluir o underscore ("________") por meio de .toList().
 * A lista de caracteres retornada é initList.**/
fun generateAllHidden(): List<Char> {
    val underscore = "_".repeat(SIZE)
    val initList: List<Char> = (underscore).toList()
    return initList
}

/** Apresenta as duas linhas da mensagem inicial.
1ª linha: Foram gerados <n> pares aleatórios de símbolos.
2ª linha: Vamos descobrir os pares em <m> tentativas!
 É usada a função println() para exibir as mensagens na consola.**/
fun startMessage() {
    println("Foram gerados ${SIZE/2} pares aleatórios de símbolos.")
    println("Vamos descobrir os pares em ${SIZE + 1} tentativas!")
}

/** Retorna uma string (texto) com a tentativa atual no formato: Tentativa <trys>: <showed>
Exemplo: "Tentativa 6: [%, %, _, _, _, X, _, X]"
      ou: "Tentativa 6: %%___X_X". a sendo a tentativa atual do utilizador e b a lista com caracteres por descobrir **/
fun textOfTry(a: Int, b: List<Char>): String {
    return "Tentativa $a: $b"
}

/** Lê e retorna a posição do símbolo a virar, introduzida pelo utilizador,
 fazendo a pergunta: "<n>ª posição a mostrar: ".
 Começa-se por usar a função print para exibir o requerimento ao utilizador na consola.
 userPosition é o leitor (readln()) que vai receber o valor introduzido pelo utilizador, em inteiro (.toInt()). Esse valor é então retornado pela função.**/
fun readPosition(position: Int): Int {
    print("$position ª posição a mostrar: ")
    val userPosition = readln().toInt()
    return userPosition
}

/** Verifica a validade das duas posições indicadas.
 Se são indices no intervalo 0..<SIZE de posições escondidas e são diferentes.
 A instrução if recebe como expressão as condições já referidas, e retorna um valor booleano (True). Todos os que não cumprem a condição são retornados como falso.**/
fun isValidPositions(position1: Int, position2: Int, secretPairs: List<Char>): Boolean {
    if (((position1 != position2) && (position1 in secretPairs.indices) && (position2 in secretPairs.indices))) {
        return true
    }
    else {
    return false }
}

/** Retorna os pares já descobertos mais os símbolos nas posições indicadas.
 * A val showList é a lista que vai exibir ao utilizador os simbolos das respetivas posições. Como será necessário mudar os elementos desta lista, o tipo de lista terá de ser MutableList, pois esta pode ser modificada.
 * a.toMutableList adiciona a lista a á lista showList (inicialmente vazia), a sendo a lista que esconde (showedpairs) e d a lista de elementos por descobrir (secretpairs).
 * Cada elemento localizado no indice da lista (showList) correspondente á posição inserida será substituido pelos elementos de secretpairs (d).
 * Retorna a lista modificada (showList).**/
fun showPositions(a: List<Char>, b: Int, c: Int, d: List<Char>): List<Char> {
    val showList: MutableList<Char> = a.toMutableList()
    showList[b] = d[b]
    showList[c] = d[c]
    return showList
}

/** Verifica se todos os pares já foram descobertos
 * O ciclo for percorre todos os indices em showedPairs e verifica se cada respetivo caracter é diferente de underscore ("_"). Retorna true se todos os elementos forem diferentes de underscore, e retorna falso se pelo menos um índice estiver por ser descoberto ("_").**/
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

/** Apresenta as duas linhas da mensagem final.
 1ª linha: "Esgotou as tentativas." ou "Parabéns, descobriu em <trys> tentativas."
 2ª linha:  "Tentativa <trys>: <showed>"
 A instrução if-else verifica qual mensagem será exibida na consola conforme o jogo.**/
fun endMessage(t: Int, s: List<Char>) {
    if (t > MAX_TRIES) {
        println("Esgotou as tentativas.")
    }
    else {
        println("Parabéns, descobriu em $t tentativas.")
        println("Tentativa $t: $s")
    }
}

