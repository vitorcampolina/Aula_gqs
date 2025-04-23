public class Main {
    public static void main(String[] args) {
        // Criando um cliente
        Cliente cliente1 = new Cliente(1, "João Silva", 10);
        
        // Testando FaturaCliente
        FaturaCliente fatura = new FaturaCliente(101, cliente1, 1000.0);
        System.out.println(fatura);
        System.out.println("Valor com desconto: " + fatura.getValorComDesconto());
        
        // Testando ContaCliente
        ContaCliente conta = new ContaCliente(201, cliente1);
        System.out.println(conta);
        conta.addDeposito(500.0);
        conta.subSaldo(200.0);
        System.out.println(conta);
        conta.subSaldo(400.0); // Tentativa de saque maior que o saldo
    }
}