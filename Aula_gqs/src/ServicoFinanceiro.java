import java.util.ArrayList;
import java.util.List;

public class ServicoFinanceiro {
    private List<ContaCliente> contas;
    private List<FaturaCliente> faturas;
    
    public ServicoFinanceiro() {
        this.contas = new ArrayList<>();
        this.faturas = new ArrayList<>();
    }
    
    // Métodos para gestão de contas
    public void adicionarConta(ContaCliente conta) {
        contas.add(conta);
        System.out.println("Conta " + conta.getId() + " adicionada com sucesso.");
    }
    
    public ContaCliente buscarContaPorId(int id) {
        for (ContaCliente conta : contas) {
            if (conta.getId() == id) {
                return conta;
            }
        }
        return null;
    }
    
    // Métodos para gestão de faturas
    public void registrarFatura(FaturaCliente fatura) {
        faturas.add(fatura);
        System.out.println("Fatura " + fatura.getId() + " registrada para o cliente " + 
                          fatura.getCliente().getNome());
    }
    
    public List<FaturaCliente> listarFaturasPorCliente(int idCliente) {
        List<FaturaCliente> resultado = new ArrayList<>();
        for (FaturaCliente fatura : faturas) {
            if (fatura.getCliente().getId() == idCliente) {
                resultado.add(fatura);
            }
        }
        return resultado;
    }
    
    // Método para pagamento de fatura
    public boolean pagarFatura(int idFatura, int idConta) {
        FaturaCliente fatura = null;
        ContaCliente conta = buscarContaPorId(idConta);
        
        // Localiza a fatura
        for (FaturaCliente f : faturas) {
            if (f.getId() == idFatura) {
                fatura = f;
                break;
            }
        }
        
        if (fatura == null || conta == null) {
            System.out.println("Fatura ou conta não encontrada.");
            return false;
        }
        
        // Verifica se o cliente da conta é o mesmo da fatura
        if (conta.getCliente().getId() != fatura.getCliente().getId()) {
            System.out.println("A conta não pertence ao cliente da fatura.");
            return false;
        }
        
        double valorComDesconto = fatura.getValorComDesconto();
        if (conta.getSaldo() >= valorComDesconto) {
            conta.subSaldo(valorComDesconto);
            faturas.remove(fatura);
            System.out.println("Fatura " + idFatura + " paga com sucesso da conta " + idConta);
            return true;
        } else {
            System.out.println("Saldo insuficiente na conta " + idConta + 
                             " para pagar a fatura " + idFatura);
            return false;
        }
    }
    
    // Método para transferência entre contas
    public boolean transferir(int idContaOrigem, int idContaDestino, double valor) {
        ContaCliente origem = buscarContaPorId(idContaOrigem);
        ContaCliente destino = buscarContaPorId(idContaDestino);
        
        if (origem == null || destino == null) {
            System.out.println("Conta de origem ou destino não encontrada.");
            return false;
        }
        
        if (origem.subSaldo(valor) != origem.getSaldo()) {
            // Se o subSaldo não foi efetivado (saldo insuficiente)
            return false;
        }
        
        destino.addDeposito(valor);
        System.out.println("Transferência de R$" + valor + " realizada da conta " + 
                          idContaOrigem + " para " + idContaDestino);
        return true;
    }
    
    // Métodos de relatório
    public void gerarRelatorioClientes() {
        System.out.println("\n=== RELATÓRIO DE CLIENTES ===");
        for (ContaCliente conta : contas) {
            Cliente cliente = conta.getCliente();
            System.out.println(cliente.getNome() + " (ID: " + cliente.getId() + 
                             ") - Saldo: R$" + conta.getSaldo());
        }
    }
    
    public void gerarRelatorioFaturas() {
        System.out.println("\n=== RELATÓRIO DE FATURAS ===");
        for (FaturaCliente fatura : faturas) {
            System.out.println("Fatura ID: " + fatura.getId() + 
                             " - Cliente: " + fatura.getCliente().getNome() +
                             " - Valor: R$" + fatura.getValor() +
                             " - Valor c/ desconto: R$" + fatura.getValorComDesconto());
        }
    }
}