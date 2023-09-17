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

data class ConteudoEducacional(val nome: String, val duracao: Int = 60, val nivel: Nivel)

data class Formacao(val nome: String, val conteudos:Set<ConteudoEducacional>) {
    val inscritos = mutableSetOf<Usuario>()
    var duracaoTotal: Int = conteudos.sumOf {it.duracao}

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

}

fun main() {
    //criacao de usuários
    var user1 = Usuario("Ilnara Ackermann", "ilnara.ackermann@hotmail.com", "senhadailnara")
    var user2 = Usuario("Maria", "maria1987@email.com", "senhadamaria")
    var user3 = Usuario("Mario", "mario2001@email.com", "senhadomario")
    println(user1)
    println(user2)
    println(user3)
    //editando usuario
    user3.senha = "novaSenha"
    println(user3)
    //criacao de conteudo
    val conteudo1 = ConteudoEducacional("conteudo1",  10, Nivel.BASICO)
    val conteudo2 = ConteudoEducacional("conteudo2",  10, Nivel.BASICO)
    val conteudo3 = ConteudoEducacional("conteudo3",  10, Nivel.INTERMEDIARIO)
    val conteudo4 = ConteudoEducacional("conteudo4",  10, Nivel.INTERMEDIARIO)
    val conteudo5 = ConteudoEducacional("conteudo5",  10, Nivel.DIFICIL)
    val conteudo6 = ConteudoEducacional("conteudo6",  10, Nivel.DIFICIL)
    println(conteudo1)
    println(conteudo4)
    println(conteudo6)

    //criando formações
    val formacao1 = Formacao("Formação 1", setOf(conteudo1, conteudo2))
    val formacao2 = Formacao("Formação 2", setOf(conteudo1,conteudo3,conteudo5))
    val formacao3 = Formacao("Formação 3", setOf(conteudo1,conteudo2,conteudo3,conteudo4,conteudo5,conteudo6))
    println(formacao1)
    println(formacao2)
    println(formacao3)


    //matriculando alunos
    formacao1.matricular(user1)
    println(formacao1.inscritos)
    formacao1.matricular(user2)
    println(formacao1.inscritos)
    formacao2.matricular(user1)
    println(formacao2.inscritos)
    formacao3.matricular(user3)
    println(formacao3.inscritos)


    //concluindo

    println(user1.formacoesConcluidas)
    println(user1.formacoesInscritas)
    println(formacao1.inscritos)
    formacao1.concluir(user1)
    println(formacao1.inscritos)
    println(user1.formacoesConcluidas)
    println(user1.formacoesInscritas)
    println(formacao1.duracaoTotal)

}
