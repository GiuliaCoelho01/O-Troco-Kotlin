import kotlin.system.exitProcess
var nomeUsuario = ""
var saldo = 100.5 // Float
//Sempre que o usuário for acessar o saldo, sacar, retirar o extrato ou transferir dinheiro é necessário que ele informe uma senha.
// Essa senha deve ser validada com uma condicional. A senha é 3589.

fun main() {
    nome()
    inicio()


}

fun senha(): Boolean {
    println("Digite sua senha:")
    val senha = readln()

    if (senha == "3589") {
        return true
    }  else {
        println("Senha incorreta! Digite sua senha:")
        return senha()
    }

}

//A transferência consiste em você informar o número de uma conta
// (pode ser qualquer número, mas obrigatoriamente um número, ou seja, nenhum outro caractere deve ser aceito),
val extrato = mutableListOf(
    "Depósito: +R$ 100,50",
    "Compra no mercado: -R$ 35,00",
    "Compra de lanche: -R$ 15,00"
)
//Ao acessar o sistema, pergunte o nome do usuário e diga "Olá {Nome} é um prazer ter você por aqui!"
fun nome(){
    println("Primeiro nos diga seu nome:")
    nomeUsuario = readln()
    println("Olá $nomeUsuario, é um prazer te você por aqui!")
}
//Na função "inicio", utilize escolha/caso (switch/case) ou when (quando), se possível, para validar a opção escolhida pelo usuário.
fun inicio() {
    println("Escolha uma opção:")
    println("1 - Ver saldo")
    println("2 - Ver Extrato")
    println("3 - Fazer saque")
    println("4 - Fazer depósito")
    println("5 - Transferência")
    println("6 - Sair")

    val escolha = readln().toIntOrNull()

    if (escolha in 1..3 || escolha == 5) {
        if (senha()) {
            when (escolha) {
                1 -> verSaldo()
                2 -> verExtrato()
                3 -> fazerSaque()
                5 -> fazerTransferencia()
            }
        } else {
            inicio()
        }
    } else {
        when (escolha) {
            4 -> fazerDeposito()
            6 -> sair()
            else -> erro()
        }
    }
 //Adicionar a opção para ver o extrato
    // (coloque algumas compras ou depósitos fictícios).
    when (escolha) {
        1 -> verSaldo()
        2 -> verExtrato()
        3 -> fazerSaque()
        4 -> fazerDeposito()
        5 -> fazerTransferencia()
        6 -> sair()
        else -> erro()
    }
}


fun verSaldo() {
    println("Seu saldo atual é: $saldo")
    inicio()
}
//Caso o usuário informe um valor para depósito igual ou menor que zero, não deixe a operação ocorrer.
// Exiba uma mensagem de "Operação não autorizada".
fun fazerDeposito() {
    print("Qual o valor para depósito? ")
    val deposito = readln().toFloatOrNull()


    if (deposito == null || deposito <= 0) {
        println("Por favor, informe um número válido.")
        fazerDeposito()
    } else {
        saldo += deposito
        verSaldo()
    }
}
//Sempre que o usuário for sacar dinheiro, o valor restante não pode ser negativo, ou seja, caso o usuário tente sacar mais do que tem em saldo, a ação não deve ocorrer.
//Exiba uma mensagem de "Operação não autorizada".
//Sempre que o usuário for sacar dinheiro, o valor a ser sacado não pode ser igual ou menor que zero. Exiba uma mensagem de "Operação não autorizada".
fun fazerSaque() {

    print("Qual o valor para saque? ")
    val saque = readln().toFloatOrNull()

    if (saque == null) {
        println("Por favor, informe um número válido.")
        fazerSaque()
    } else if (saque > saldo || saque <= 0) {
        println("Operação não autorizada.")
        fazerSaque()
    } else {
        saldo -= saque
        verSaldo()
    }
}
//Ajustar a ortografia da frase 'Por favor, informe um número entre 1 e 4' para 'Por favor, informe um número entre 1 a 4'.
fun erro() {
    println("Por favor, informe um número entre 1 a 6.")
    inicio()
}
fun verExtrato() {
    println("\n===== EXTRATO =====")

    if (extrato.isEmpty()) {
        println("Nenhuma movimentação encontrada.")
    } else {
        for (movimentacao in extrato) {
            println(movimentacao)
        }
    }

    println("===================")
    inicio()
}
//perguntar o valor da transferência e remover o valor da conta da mesma forma como na ação do saldo.
// Caso o usuário tente transferir mais do que tem em saldo, a ação não deve ocorrer. Exiba uma mensagem de "Operação não autorizada".
fun fazerTransferencia() {
    print("Digite o número da conta: ")

    val conta = readln().toIntOrNull()

    if (conta == null) {
        println("Número de conta inválido. Digite apenas números.")
        fazerTransferencia()
        return
    } else {
        println("Conta $conta informada com sucesso!")
        // continua a transferência...
    }
    println("Informe o valor da transferência:")
    val valor = readln().toFloatOrNull()
    if (valor == null || valor <= 0) {
        println("Por favor, informe um número válido.")
        fazerTransferencia()
    } else if (valor > saldo) {
        println("Operação não autorizada. Saldo insuficiente.")
        fazerTransferencia()
    } else {
        saldo -= valor
        println("Transferência realizada com sucesso!")

        verSaldo()
    }
}

//Quando o usuário escolher sair do sistema, exiba uma mensagem agradecendo por utilizar os serviços do banco:
// "{Nome}, foi um prazer ter você por aqui!"
fun sair() {
    print("Você deseja sair? (S/N)")

    val confirma = readln().uppercase()


    when (confirma) {
        "S" -> {
            println("$nomeUsuario, foi um prazer ter você por aqui!")
            exitProcess(0)
        }
        "N" -> inicio()
        else -> sair()
    }
}