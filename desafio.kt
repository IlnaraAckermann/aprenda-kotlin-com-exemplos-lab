enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario (var nome: String, var email: String, var senha: String) {
    val formacoesInscritas = mutableSetOf<Formacao>();
    val formacoesConcluidas = mutableSetOf<Formacao>();
    fun adicionarFormacaoInscrita(vararg formacoes:Formacao) {
        for (formacao in formacoes){
            formacoesInscritas.add(formacao)
        }
    }
    fun adicionarFormacaoConcluida(vararg formacoes:Formacao) {
        for (formacao in formacoes){
            formacoesInscritas.remove(formacao)
            formacoesConcluidas.add(formacao)
        }
    }

    override fun toString(): String {
        return "----------------\n\nUsuario\n\nnome='$nome', \nemail='$email', \nsenha='$senha', \n" +
                "formacoesInscritas=$formacoesInscritas, " +
                "\nformacoesConcluidas=$formacoesConcluidas\n\n----------------"
    }
}

data class ConteudoEducacional(var nome: String, val duracao: Int = 60, val nivel: Nivel)

data class Formacao(val nome: String, var conteudos: Set<ConteudoEducacional>) {

    val inscritos = mutableSetOf<Usuario>()
    var duracaoTotal: Int = 0

    fun matricular(vararg usuario: Usuario) {
        for (user in usuario){
            user.adicionarFormacaoInscrita(this)
            inscritos.add(user)
        }
    }

    fun concluir (vararg usuario: Usuario) {
        for (user in usuario){
            user.adicionarFormacaoConcluida(this)
            inscritos.remove(user)
        }
    }

    private fun calcularDuracaoTotal(){
        duracaoTotal = conteudos.sumOf {it.duracao}
    }
}

fun main() {
    var user1 = Usuario("Ilnara Ackermann", "ilnara.ackermann@hotmail.com", "senhadailnara")
    var user2 = Usuario("Maria", "maria1987@email.com", "senhadamaria")
    var user3 = Usuario("Mario", "mario2001@email.com", "senhadomario")
    println(user1)
    println(user2)
    println(user3)
    user3.senha = "novaSenha"
    println(user3)
}
