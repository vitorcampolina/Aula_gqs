import static org.junit.Assert.*; 
import org.junit.Test;  

public class ValidadorSenhaSemCaractereEspecialTest {  
	
	@Test 
	public void senhaSemCaracterEspecial_DeveSerInvalida() {   
		validador_senha validador = new validador_senha();         
		boolean valido = validador.validar("Senha123");
		assertFalse(valido);         
		assertTrue(validador.getErros().contains("A senha deve conter pelo menos um caractere especial"));     
	} 
}