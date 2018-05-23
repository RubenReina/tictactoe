package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UnitariosTest {

	private Board tablon;

	@Before
	public void setUp() {
		tablon = new Board();
		tablon.enableAll();
	}
	
	public void marcar(String label, int numero){
		
		this.tablon.getCell(numero).active = false;
		this.tablon.getCell(numero).value = label;
	}
	@Test
	public void testGetCellsIfWinner() {
		/*	X|O|O
		 * 	 |X|
		 * 	 | |x
		 */
		marcar("X",0);
		marcar("O",1);
		marcar("X",4);
		marcar("O",2);
		marcar("X",8);
				
		int [] listaGanador = this.tablon.getCellsIfWinner("X");
		int [] listaFinal = {0 ,4,8};
		assertArrayEquals(listaFinal, listaGanador);
		int [] listaPerdedor = this.tablon.getCellsIfWinner("O");
		assertNull(listaPerdedor);

	}
	
	@Test
	public void testGetCellsIfNotWinner() {
		/*	X|O|
		 * 	 |O|X
		 * 	X|O|
		 */
		marcar("X",0);
		marcar("O",1);
		marcar("X",5);
		marcar("O",4);
		marcar("X",6);
		marcar("O",7);
		int [] listaOcupadas = this.tablon.getCellsIfWinner("X");
		assertNull(listaOcupadas);
		int [] listaGanador = this.tablon.getCellsIfWinner("O");
		int [] listaFinal = {1,4,7};
		assertArrayEquals(listaFinal, listaGanador);
	}
	
	@Test
	public void testCheckDraw(){
		/*	X|O|X
		 * 	X|O|O
		 * 	O|X|X
		 */
		marcar("X",0);
		marcar("O",1);
		marcar("X",2);
		marcar("O",4);
		marcar("X",3);
		marcar("O",5);
		marcar("X",7);
		marcar("O",6);
		marcar("X",8);
		int [] listaOcupadas = this.tablon.getCellsIfWinner("X");
		assertNull(listaOcupadas);
		int [] listaGanador = this.tablon.getCellsIfWinner("O");
		assertNull(listaGanador);
		assertTrue(tablon.checkDraw());
		
		
	}
}
