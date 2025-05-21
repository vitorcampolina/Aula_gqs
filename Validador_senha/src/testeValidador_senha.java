import static org.junit.Assert.*;
import org.junit.Test;

public class testeValidador_senha {


    @Test
    public void senhaValida() {
        validador_senha validador = new validador_senha();
        boolean valido = validador.validar("Senha123!");
        assertTrue(valido);
        assertTrue(validador.getErros().isEmpty());
    }
  
	    
}
