import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static int lerInt(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número inteiro.");
            }
        }
    }

    static String lerTexto(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    static void mostrar(Contato c) {
        if (c == null) {
            System.out.println("Contato não encontrado.");
        } else {
            System.out.println("--- Contato encontrado ---");
            c.exibirDados();
        }
    }

    static Contato criarContato() {
        System.out.println("Tipo de contato: 1-Pessoal  2-Profissional  3-Emergência");
        int tipo = lerInt("Escolha: ");
        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo inválido.");
            return null;
        }

        String nome = lerTexto("Nome: ");
        String email = lerTexto("Email: ");
        String telefone = lerTexto("Telefone: ");

        switch (tipo) {
            case 1:
                String aniversario = lerTexto("Data de aniversário: ");
                String parentesco = lerTexto("Parentesco: ");
                return new ContatoPessoal(nome, email, telefone, aniversario, parentesco);
            case 2:
                String empresa = lerTexto("Empresa: ");
                String cargo = lerTexto("Cargo: ");
                return new ContatoProfissional(nome, email, telefone, empresa, cargo);
            default:
                int prioridade;
                do {
                    prioridade = lerInt("Grau de prioridade (1 a 5): ");
                    if (prioridade < 1 || prioridade > 5) {
                        System.out.println("A prioridade deve ser de 1 a 5.");
                    }
                } while (prioridade < 1 || prioridade > 5);
                String obs = lerTexto("Observação: ");
                return new ContatoEmergencia(nome, email, telefone, prioridade, obs);
        }
    }

    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        int opcao;

        do {
            System.out.println("\n===== AGENDA TELEFÔNICA =====");
            System.out.println("1 - Adicionar contato");
            System.out.println("2 - Remover contato");
            System.out.println("3 - Buscar contato por nome");
            System.out.println("4 - Buscar contato por email");
            System.out.println("5 - Buscar contato por telefone");
            System.out.println("6 - Consultar tamanho da agenda");
            System.out.println("7 - Finalizar");
            opcao = lerInt("Opção: ");

            switch (opcao) {
                case 1:
                    Contato novo = criarContato();
                    if (novo != null) {
                        agenda.adicionarContato(novo);
                        System.out.println("Contato adicionado com sucesso!");
                    }
                    break;
                case 2:
                    String nomeRemover = lerTexto("Nome do contato a remover: ");
                    if (agenda.removerContato(nomeRemover)) {
                        System.out.println("Contato removido com sucesso!");
                    } else {
                        System.out.println("Contato não encontrado.");
                    }
                    break;
                case 3:
                    mostrar(agenda.buscarPorNome(lerTexto("Nome: ")));
                    break;
                case 4:
                    mostrar(agenda.buscarPorEmail(lerTexto("Email: ")));
                    break;
                case 5:
                    mostrar(agenda.buscarPorTelefone(lerTexto("Telefone: ")));
                    break;
                case 6:
                    int q = agenda.getQuantidade();
                    if (q == 0) {
                        System.out.println("A agenda está vazia.");
                    } else {
                        System.out.println("A agenda possui " + q + " contato(s).");
                    }
                    break;
                case 7:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 7);
    }
}
