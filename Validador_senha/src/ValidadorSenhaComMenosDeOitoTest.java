import static org.junit.Assert.*;
import org.junit.Test;

public class ValidadorSenhaComMenosDeOitoTest {

    @Test
    public void senhaSemMaiuscula_DeveSerInvalida() {
        validador_senha validador = new validador_senha();
        boolean valido = validador.validar("Senh12!");
        assertFalse(valido);
        assertTrue(validador.getErros().contains("A senha deve ter pelo menos 8 caracteres"));
    }
}


