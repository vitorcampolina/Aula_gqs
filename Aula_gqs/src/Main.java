public class Main {
    public static void main(String[] args) {
        // Criando clientes
        Cliente cliente1 = new Cliente(1, "João Silva", 10);
        Cliente cliente2 = new Cliente(2, "Maria Souza", 5);
        
        // Criando contas
        ContaCliente conta1 = new ContaCliente(101, cliente1, 1000.0);
        ContaCliente conta2 = new ContaCliente(102, cliente2, 500.0);
        
        // Criando faturas
        FaturaCliente fatura1 = new FaturaCliente(201, cliente1, 300.0);
        FaturaCliente fatura2 = new FaturaCliente(202, cliente2, 200.0);
        
        // Criando o serviço financeiro
        ServicoFinanceiro servico = new ServicoFinanceiro();
        
        // Adicionando contas ao serviço
        servico.adicionarConta(conta1);
        servico.adicionarConta(conta2);
        
        // Registrando faturas
        servico.registrarFatura(fatura1);
        servico.registrarFatura(fatura2);
        
        // Realizando operações financeiras
        servico.pagarFatura(201, 101); // João paga sua fatura
        servico.transferir(101, 102, 150.0); // João transfere para Maria
        
        // Gerando relatórios
        servico.gerarRelatorioClientes();
        servico.gerarRelatorioFaturas();
    }
}