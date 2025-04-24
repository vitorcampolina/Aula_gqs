public class Desconto {

    public static double aplicarDesconto(double valor, int percentualDesconto) {
        return valor * (1 - percentualDesconto / 100.0);
    }
}
