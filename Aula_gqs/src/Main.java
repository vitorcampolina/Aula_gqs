import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Criando o serviço financeiro e dados iniciais
        ServicoFinanceiro servico = new ServicoFinanceiro();
        
        // Criando clientes
        Cliente cliente1 = new Cliente(1, "João Silva", 10);
        Cliente cliente2 = new Cliente(2, "Maria Souza", 5);
        
        // Criando contas
        ContaCliente conta1 = new ContaCliente(101, cliente1, 1000.0);
        ContaCliente conta2 = new ContaCliente(102, cliente2, 500.0);
        
        FaturaCliente fatura1 = new FaturaCliente(201, cliente1, 300.0);
        FaturaCliente fatura2 = new FaturaCliente(202, cliente2, 200.0);
        
        
        // Adicionando contas ao serviço
        servico.adicionarConta(conta1);
        servico.adicionarConta(conta2);
        
        servico.registrarFatura(fatura1);
        servico.registrarFatura(fatura2);
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== BANCO DIGITAL ===");
        
        while (true) {
            // Menu principal - seleção de conta
            System.out.println("\nCONTAS DISPONÍVEIS:");
            System.out.println("1 - Conta 101 | João Silva");
            System.out.println("2 - Conta 102 | Maria Souza");
            System.out.println("0 - Sair do sistema");
            System.out.print("Escolha a conta (1-2) ou 0 para sair: ");
            
            int opcaoConta = scanner.nextInt();
            
            if (opcaoConta == 0) {
                System.out.println("Saindo do sistema...");
                break;
            }
            
            if (opcaoConta < 1 || opcaoConta > 2) {
                System.out.println("Opção inválida!");
                continue;
            }
            
            int numeroConta = (opcaoConta == 1) ? 101 : 102;
            ContaCliente conta = servico.buscarContaPorId(numeroConta);
            
            // Menu de operações para a conta selecionada
            while (true) {
                System.out.println("\nConta " + numeroConta + " | " + conta.getCliente().getNome());
                System.out.printf("Saldo atual: R$%.2f\n", conta.getSaldo());
                System.out.println("\nOPERAÇÕES:");
                System.out.println("1 - Sacar");
                System.out.println("2 - Depositar");
                System.out.println("3 - Transferir");
                System.out.println("4 - Pagar fatura");
                System.out.println("5 - Ver extrato");
                System.out.println("0 - Voltar para seleção de contas");
                System.out.print("Escolha uma operação: ");
                
                int opcaoOperacao = scanner.nextInt();
                
                if (opcaoOperacao == 0) {
                    break; // Volta para o menu de contas
                }
                
                switch (opcaoOperacao) {
                    case 1: // Saque
                        System.out.print("Digite o valor para sacar: ");
                        double valorSaque = scanner.nextDouble();
                        try {
                            servico.sacar(numeroConta, valorSaque);
                            System.out.printf("Saque realizado. Novo saldo: R$%.2f\n", conta.getSaldo());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;
                        
                    case 2: // Depósito
                        System.out.print("Digite o valor para depositar: ");
                        double valorDeposito = scanner.nextDouble();
                        try {
                            servico.depositar(numeroConta, valorDeposito);
                            System.out.printf("Depósito realizado. Novo saldo: R$%.2f\n", conta.getSaldo());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;
                        
                    case 3: // Transferência
                        System.out.print("Digite o número da conta destino (101 ou 102): ");
                        int contaDestino = scanner.nextInt();
                        System.out.print("Digite o valor para transferir: ");
                        double valorTransferencia = scanner.nextDouble();
                        try {
                            servico.transferir(numeroConta, contaDestino, valorTransferencia);
                            System.out.printf("Transferência realizada. Novo saldo: R$%.2f\n", conta.getSaldo());
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;
                   
                    case 4: // Pagar fatura
                        List<FaturaCliente> faturas = servico.listarFaturasPorCliente(conta.getCliente().getId());
                        
                        if (faturas.isEmpty()) {
                            System.out.println("Nenhuma fatura pendente para esta conta.");
                            break;
                        }
                        
                        System.out.println("\nFATURAS PENDENTES:");
                        for (FaturaCliente fatura : faturas) {
                            System.out.printf("ID: %d | Valor: R$%.2f | Valor c/ desconto: R$%.2f\n",
                                            fatura.getId(), fatura.getValor(), fatura.getValorComDesconto());
                        }
                        
                        System.out.print("\nDigite o ID da fatura a pagar: ");
                        int idFatura = scanner.nextInt();
                        
                        boolean pagamentoEfetuado = servico.pagarFatura(idFatura, numeroConta);
                        if (pagamentoEfetuado) {
                            System.out.println("Pagamento realizado com sucesso!");
                            System.out.printf("Novo saldo: R$%.2f\n", conta.getSaldo());
                        } else {
                            System.out.println("Falha ao processar pagamento.");
                        }
                        break;
                        
                    case 5: // Extrato
                        System.out.println("\n=== EXTRATO ===");
                        System.out.println("Titular: " + conta.getCliente().getNome());
                        System.out.printf("Saldo atual: R$%.2f\n", conta.getSaldo());
                        System.out.println("=================");
                        break;
                        
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        }
        scanner.close();
    }
}
