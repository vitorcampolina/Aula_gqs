import static org.junit.Assert.*;
import org.junit.Test;

public class ValidadorSenhaComMenosDeDoisNumeraisTest {

    @Test
    public void senhaSemDoisNumerais_DeveSerInvalida() {
        validador_senha validador = new validador_senha();
        boolean valido = validador.validar("Senhaa1!");
        assertFalse(valido);
        assertTrue(validador.getErros().contains("A senha deve conter Pelomenos 2 numerais"));
    }
}