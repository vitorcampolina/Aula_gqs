public class Cliente {
    private int id;
    private String nome;
    private int desconto;
    private String genero; // 'm', 'f', 'nd'

    public Cliente(int id, String nome, int desconto) {
        this.id = id;
        this.nome = nome;
        this.desconto = desconto;
        this.genero = "nd"; // Valor padrão
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public int getDesconto() {
        return desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    @Override
    public String toString() {
        return String.format("%s(%d)(%d%%)", nome, id, desconto);
    }
}
