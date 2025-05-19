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
        System.out.printf("Conta %d adicionada com sucesso.%n", conta.getId());
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
        System.out.printf("Fatura %d registrada para o cliente %s%n",
                          fatura.getId(), fatura.getCliente().getNome());
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
            System.out.printf("Fatura ou conta não encontrada.%n");
            return false;
        }
        
        // Verifica se o cliente da conta é o mesmo da fatura
        if (conta.getCliente().getId() != fatura.getCliente().getId()) {
            System.out.printf("A conta não pertence ao cliente da fatura.%n");
            return false;
        }
        
        double valorComDesconto = fatura.getValorComDesconto();
        if (conta.getSaldo() >= valorComDesconto) {
            conta.subSaldo(valorComDesconto);
            faturas.remove(fatura);
            System.out.printf("Fatura %d paga com sucesso da conta %d%n", idFatura, idConta);
            return true;
        } else {
            System.out.printf("Saldo insuficiente na conta %d para pagar a fatura %d%n",
                              idConta, idFatura);
            return false;
        }
    }
    
    // Método para transferência entre contas
    public boolean transferir(int idContaOrigem, int idContaDestino, double valor) {
        ContaCliente origem = buscarContaPorId(idContaOrigem);
        ContaCliente destino = buscarContaPorId(idContaDestino);
        
        if (origem == null || destino == null) {
            System.out.printf("Conta de origem ou destino não encontrada.%n");
            return false;
        }
        
        if (origem.subSaldo(valor) != origem.getSaldo()) {
          // Se o subSaldo não foi efetivado (saldo insuficiente)
          return false;
        }
        
        destino.addDeposito(valor);
        System.out.printf("Transferência de R$%.2f realizada da conta %d para %d%n",
                          valor, idContaOrigem, idContaDestino);
        return true;
    }
    
    
    //Método para saques
    public boolean sacar(int idConta, double valor) {
        // Validação do valor
        if (valor <= 0) {
            System.out.println("Valor do saque deve ser positivo.");
            return false;
        }
        
        // Localiza a conta
        ContaCliente conta = buscarContaPorId(idConta);
        if (conta == null) {
            System.out.printf("Conta %d não encontrada.%n", idConta);
            return false;
        }
        
        // Verifica saldo suficiente
        if (conta.getSaldo() < valor) {
            System.out.printf("Saldo insuficiente na conta %d para realizar o saque.%n", idConta);
            return false;
        }
        
        // Efetua o saque
        conta.subSaldo(valor);
        System.out.printf("Saque de R$%.2f realizado na conta %d. Novo saldo: R$%.2f%n",
                         valor, idConta, conta.getSaldo());
        return true;
    }
    
  //Método para depositos
    public boolean depositar(int idConta, double valor) {
        // Validação do valor
        if (valor <= 0) {
            System.out.println("Valor do depósito deve ser positivo.");
            return false;
        }
        
        // Localiza a conta
        ContaCliente conta = buscarContaPorId(idConta);
        if (conta == null) {
            System.out.printf("Conta %d não encontrada.%n", idConta);
            return false;
        }
        
        // Efetua o depósito
        conta.addDeposito(valor);
        System.out.printf("Depósito de R$%.2f realizado na conta %d. Novo saldo: R$%.2f%n",
                         valor, idConta, conta.getSaldo());
        return true;
    }
    
    // Métodos de relatório
    public void gerarRelatorioClientes() {
        System.out.printf("%n=== RELATÓRIO DE CLIENTES ===%n");
        for (ContaCliente conta : contas) {
            Cliente cliente = conta.getCliente();
            System.out.printf("%s (ID: %d) - Saldo: R$%.2f%n",
                              cliente.getNome(), cliente.getId(), conta.getSaldo());
        }
    }
    
    public void gerarRelatorioFaturas() {
        System.out.printf("%n=== RELATÓRIO DE FATURAS ===%n");
        for (FaturaCliente fatura : faturas) {
            System.out.printf("Fatura ID: %d - Cliente: %s - Valor: R$%.2f - Valor c/ desconto: R$%.2f%n",
                              fatura.getId(), fatura.getCliente().getNome(),
                              fatura.getValor(), fatura.getValorComDesconto());
        }
    }
}
