import java.util.ArrayList;
import java.util.List;

public class validador_senha {

    private List<String> erros = new ArrayList<>();

    public boolean validar(String senha) {
        erros.clear(); // limpa erros antigos

        if (!senha.matches(".*[A-Z].*")) {
            erros.add("A senha deve conter letra maiúscula");
        }
        if (senha == null || senha.length() < 8) {
            erros.add("A senha deve ter pelo menos 8 caracteres");
        }
        long digitos = senha.chars().filter(Character::isDigit).count();
        if (digitos < 2) {
            erros.add("A senha deve conter pelo menos 2 numerais");
        }
        if (!senha.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            erros.add("A senha deve conter pelo menos um caractere especial");
        }

        return erros.isEmpty();
    }

    public List<String> getErros() {
        return erros;
    }
}

