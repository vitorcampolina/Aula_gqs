public class ContaCliente {
    private int id;
    private Cliente cliente;
    private double saldo;

    public ContaCliente(int id, Cliente cliente, double saldo) {
        this.id = id;
        this.cliente = cliente;
        this.saldo = saldo;
    }

    public ContaCliente(int id, Cliente cliente) {
        this(id, cliente, 0.0);
    }

    public ContaCliente(int id) {
        this(id, null);
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double addDeposito(double valor) {
        saldo += valor;
        System.out.printf("Depósito realizado. Novo saldo: R$%.2f%n", saldo);
        return saldo;
    }

    public double subSaldo(double valor) {
        if (valor > saldo) {
            System.out.printf("Saldo insuficiente. Saldo atual: R$%.2f%n", saldo);
            return saldo;
        }
        saldo -= valor;
        return saldo;
    }

    @Override
    public String toString() {
        return String.format("%s(%d) saldo=R$%.2f", cliente.getNome(), id, saldo);
    }
}
