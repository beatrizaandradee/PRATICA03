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

    static double lerDouble(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número (ex: 120.50).");
            }
        }
    }

    static String lerTexto(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    static void cadastrar(Bilheteria bilheteria) {
        System.out.println("Tipo de ingresso: 1-Comum  2-Estudante  3-VIP");
        int tipo = lerInt("Escolha: ");
        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo inválido.");
            return;
        }

        int codigo = lerInt("Código: ");
        String evento = lerTexto("Nome do evento: ");
        String setor = lerTexto("Setor: ");
        double valor = lerDouble("Valor base: R$ ");

        Ingresso ingresso;
        if (tipo == 1) {
            ingresso = new IngressoComum(codigo, evento, setor, valor);
        } else if (tipo == 2) {
            String inst = lerTexto("Instituição de ensino: ");
            ingresso = new IngressoEstudante(codigo, evento, setor, valor, inst);
        } else {
            String resp = lerTexto("Possui acesso ao backstage? (s/n): ");
            boolean backstage = resp.equalsIgnoreCase("s");
            int lounge = lerInt("Número do lounge: ");
            ingresso = new IngressoVIP(codigo, evento, setor, valor, backstage, lounge);
        }

        if (bilheteria.adicionarIngresso(ingresso)) {
            System.out.println("Ingresso cadastrado com sucesso!");
        } else {
            System.out.println("Já existe um ingresso com esse código.");
        }
    }

    public static void main(String[] args) {
        Bilheteria bilheteria = new Bilheteria();
        int opcao;

        do {
            System.out.println("\n===== BILHETERIA =====");
            System.out.println("1 - Cadastrar ingresso");
            System.out.println("2 - Remover ingresso");
            System.out.println("3 - Buscar ingresso por código");
            System.out.println("4 - Listar todos os ingressos");
            System.out.println("5 - Calcular arrecadação total");
            System.out.println("6 - Listar benefícios dos ingressos");
            System.out.println("7 - Finalizar");
            opcao = lerInt("Opção: ");

            switch (opcao) {
                case 1:
                    cadastrar(bilheteria);
                    break;
                case 2:
                    int codRemover = lerInt("Código do ingresso a remover: ");
                    if (bilheteria.removerIngresso(codRemover)) {
                        System.out.println("Ingresso removido com sucesso!");
                    } else {
                        System.out.println("Código não encontrado.");
                    }
                    break;
                case 3:
                    Ingresso i = bilheteria.buscarIngresso(lerInt("Código: "));
                    if (i == null) {
                        System.out.println("Código não encontrado.");
                    } else {
                        i.exibirInformacoes();
                        System.out.println("Benefícios: " + i.obterBeneficios());
                    }
                    break;
                case 4:
                    bilheteria.listarIngressos();
                    break;
                case 5:
                    System.out.printf("Arrecadação total: R$ %.2f%n", bilheteria.calcularArrecadacaoTotal());
                    break;
                case 6:
                    bilheteria.listarBeneficios();
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
