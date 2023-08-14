package com.victor.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import com.victor.entities.Enemy1;
import com.victor.entities.Entity;
import com.victor.entities.Player;

class Test {
	
	public Enemy1 test = new Enemy1(0, 0, 0, 0, 0, null);
	public Entity test2 = new Entity(0, 0, 0, 0, 0, null);
	
	/*
	@org.junit.jupiter.api.Test
	public void test() {
		assertTrue(test.tick());
	}
	*/
	
	@org.junit.jupiter.api.Test
    public void testSomar() {
        // Preparação dos dados de teste
        int a = 2;
        int b = 3;
        
        // Execução do código a ser testado
        int resultado = somar(a, b);
        
        // Verificação do resultado esperado
        int resultadoEsperado = 5;
        assertEquals(resultadoEsperado, resultado);
    }

    // Método a ser testado
    public int somar(int a, int b) {
        return a + b;
    }
}
