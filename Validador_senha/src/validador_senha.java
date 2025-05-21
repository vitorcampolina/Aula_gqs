import java.util.ArrayList;
import java.util.List;

public class validador_senha {

    private List<String> erros = new ArrayList<>();

    public boolean validar(String senha) {
        return erros.isEmpty();
    }

    public List<String> getErros() {
        return erros;
    }
}