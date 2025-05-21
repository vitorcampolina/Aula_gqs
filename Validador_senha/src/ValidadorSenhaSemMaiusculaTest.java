import static org.junit.Assert.*;
import org.junit.Test;

public class ValidadorSenhaSemMaiusculaTest {

    @Test
    public void senhaSemMaiuscula_DeveSerInvalida() {
        validador_senha validador = new validador_senha();
        boolean valido = validador.validar("senha123!");
        assertFalse(valido);
        assertTrue(validador.getErros().contains("A senha deve conter letra maiúscula"));
    }
}
