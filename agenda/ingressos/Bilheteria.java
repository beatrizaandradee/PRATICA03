import java.util.ArrayList;

public class Bilheteria {
    private ArrayList<Ingresso> ingressos;

    public Bilheteria() {
        this.ingressos = new ArrayList<>();
    }

    public ArrayList<Ingresso> getIngressos() { return ingressos; }
    public void setIngressos(ArrayList<Ingresso> ingressos) { this.ingressos = ingressos; }

    // Retorna false se já existir ingresso com o mesmo código
    public boolean adicionarIngresso(Ingresso ingresso) {
        if (buscarIngresso(ingresso.getCodigo()) != null) {
            return false;
        }
        ingressos.add(ingresso);
        return true;
    }

    public boolean removerIngresso(int codigo) {
        Ingresso i = buscarIngresso(codigo);
        if (i == null) {
            return false;
        }
        ingressos.remove(i);
        return true;
    }

    public Ingresso buscarIngresso(int codigo) {
        for (Ingresso i : ingressos) {
            if (i.getCodigo() == codigo) {
                return i;
            }
        }
        return null;
    }

    // Polimorfismo: cada subclasse exibe suas próprias informações
    public void listarIngressos() {
        if (ingressos.isEmpty()) {
            System.out.println("Nenhum ingresso cadastrado.");
            return;
        }
        for (Ingresso i : ingressos) {
            System.out.println("------------------------------");
            i.exibirInformacoes();
        }
    }

    public double calcularArrecadacaoTotal() {
        double total = 0;
        for (Ingresso i : ingressos) {
            total += i.calcularValorFinal();
        }
        return total;
    }

    public void listarBeneficios() {
        if (ingressos.isEmpty()) {
            System.out.println("Nenhum ingresso cadastrado.");
            return;
        }
        for (Ingresso i : ingressos) {
            System.out.println("Ingresso " + i.getCodigo() + " (" + i.getNomeEvento() + "): "
                    + i.obterBeneficios());
        }
    }
}
