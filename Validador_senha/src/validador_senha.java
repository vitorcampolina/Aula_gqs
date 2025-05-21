import java.util.ArrayList;
import java.util.List;

public class validador_senha {

    private List<String> erros = new ArrayList<>();

    public boolean validar(String senha) {
        erros.clear(); // limpa erros antigos

        if (!senha.matches(".*[A-Z].*")) {
            erros.add("A senha deve conter letra maiúscula");
        }

        return erros.isEmpty();
    }

    public List<String> getErros() {
        return erros;
    }
}

