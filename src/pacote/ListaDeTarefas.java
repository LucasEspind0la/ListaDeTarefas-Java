
package pacote;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Classe principal que implementa uma aplicação de lista de tarefas simples via console.
 * Permite ao usuário adicionar, listar, remover tarefas e sair do programa.
 * Utiliza um menu interativo com entrada via teclado.
 */
public class ListaDeTarefas {

    // Scanner compartilhado para leitura de entradas do usuário via console.
    private static final Scanner scan = new Scanner(System.in);
    
    // Lista que armazena as tarefas inseridas pelo usuário. Persiste durante a execução do programa.
    private static final ArrayList<String> tarefas = new ArrayList<>();

    /**
     * Método principal que inicia a aplicação.
     * Chama o método responsável por exibir o menu interativo.
     */
    public static void main(String[] args) {
        exibirMenu();
    }
    /**
     * Exibe o menu principal em loop até que o usuário escolha a opção de sair.
     * Lê a opção selecionada e delega a execução para os métodos correspondentes.
     */
    private static void exibirMenu() {
        while (true) {
            System.out.println("""
                === Lista de Tarefas ===
                [1] Adicionar tarefa
                [2] Listar tarefas
                [3] Remover tarefa
                [4] Sair
                """);
            System.out.print("Escolha uma opção: ");

            int opcao = lerInteiro();
            System.out.println();

            switch (opcao) {
                case 1 -> adicionarTarefa();
                case 2 -> listarTarefas();
                case 3 -> removerTarefa();
                case 4 -> {
                    System.out.println("Saindo do sistema...");
                    // Libera o recurso do Scanner antes de encerrar.
                    scan.close(); 
                    return;
                }
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
            System.out.println();
        }
    }
    /**
     * Solicita ao usuário uma nova tarefa, remove espaços em branco extras e,
     * se a entrada não for vazia, adiciona à lista de tarefas.
     */
    private static void adicionarTarefa() {
        System.out.println("Adicionar tarefa selecionado.");
        System.out.print("Digite uma tarefa: ");
        // remove espaços em branco no início e no fim da string.
        String novaTarefa = scan.nextLine().trim(); 

        if (!novaTarefa.isEmpty()) {
            tarefas.add(novaTarefa);
            System.out.println("Tarefa adicionada com sucesso!");
        } else {
            System.out.println("Tarefa vazia! Nada foi adicionado.");
        }
    }
    /**
     * Lista todas as tarefas armazenadas, numeradas sequencialmente.
     * Informa ao usuário caso a lista esteja vazia.
     */
    private static void listarTarefas() {
        System.out.println("Listar tarefas selecionado.");
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada!");
        } else {
            System.out.println("Suas tarefas: \n");
            for (int i = 0; i < tarefas.size(); i++) {
                System.out.println((i + 1) + ". " + tarefas.get(i));
            }
        }
    }

    /**
     * Permite ao usuário remover uma tarefa existente informando seu número na lista.
     * Valida se o número está dentro do intervalo válido antes de remover.
     */
    private static void removerTarefa() {
        System.out.println("Remover tarefa selecionado.");
        if (tarefas.isEmpty()) {
            System.out.println("Não há tarefas para remover!");
            return;
        }

        listarTarefasNumeradas();
        System.out.print("Digite o número da tarefa a remover: ");
        int numero = lerInteiro();

        if (numero >= 1 && numero <= tarefas.size()) {
            tarefas.remove(numero - 1); // ajusta índice (lista é base 0, interface é base 1)
            System.out.println("Tarefa removida com sucesso!");
        } else {
            System.out.println("Número inválido. Nenhuma tarefa foi removida!");
        }
    }
    /**
     * Exibe a lista de tarefas com numeração sequencial (1, 2, 3...),
     * utilizado principalmente durante a remoção de tarefas.
     */
    private static void listarTarefasNumeradas() {
        for (int i = 0; i < tarefas.size(); i++) {
            System.out.println((i + 1) + ". " + tarefas.get(i));
        }
    }
    /**
     * Lê um número inteiro da entrada do usuário com tratamento de erro.
     * Se a entrada não for um inteiro válido, solicita repetidamente até obter uma entrada correta.
     * Após ler o inteiro, consome a quebra de linha restante para evitar problemas com nextLine().
     */
    private static int lerInteiro() {
        while (!scan.hasNextInt()) {
            System.out.print("Entrada inválida! Digite um número inteiro: ");
            scan.next(); // descarta entrada inválida
        }
        int valor = scan.nextInt();
        scan.nextLine(); // consome a quebra de linha restante
        return valor;
    }
}