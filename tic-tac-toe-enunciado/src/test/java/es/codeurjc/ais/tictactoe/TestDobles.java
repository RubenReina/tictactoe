package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import es.codeurjc.ais.tictactoe.TicTacToeGame.Event;
import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;

public class TestDobles {

	TicTacToeGame juego;
	Connection conect1;
	Connection conect2;
	Player jugador1;
	Player jugador2;

	@Before
	public void setup() {
		juego = new TicTacToeGame();
		conect1 = mock(Connection.class);
		conect2 = mock(Connection.class);
		juego.addConnection(conect1);
		juego.addConnection(conect2);
		jugador1 = new Player(0, "X", "Mario");
		jugador2 = new Player(1, "O", "Ruben");
		juego.addPlayer(jugador1);
		juego.addPlayer(jugador2);
	}

	@Test
	public void testJugador1Gana() {
		/*	X|O|O
		 * 	X| |
		 * 	X| |
		 */
		ArgumentCaptor<EventType> argumento1 = ArgumentCaptor.forClass(EventType.class);
		verify(conect1, times(2)).sendEvent(eq(EventType.JOIN_GAME), argumento1.capture());
		ArgumentCaptor<EventType> argumento2 = ArgumentCaptor.forClass(EventType.class);
		verify(conect2, times(2)).sendEvent(eq(EventType.JOIN_GAME), argumento2.capture());
		assertTrue(juego.checkTurn(0));
		juego.mark(0);
		assertTrue(juego.checkTurn(1));
		juego.mark(1);
		assertTrue(juego.checkTurn(0));
		juego.mark(3);
		assertTrue(juego.checkTurn(1));
		juego.mark(2);
		assertTrue(juego.checkTurn(0));
		juego.mark(6);
		int [] listaGanador = juego.checkWinner().pos;
		int [] listaFinal = {0,3,6};
		assertArrayEquals(listaFinal, listaGanador);
	}
	
	@Test
	public void testJugador1Pierde(){
		/*	X| |O
		 * 	 |X|O
		 * 	X| |O
		 */
		ArgumentCaptor<Event> argumento1 = ArgumentCaptor.forClass(Event.class);
		verify(conect1, times(2)).sendEvent(eq(EventType.JOIN_GAME), argumento1.capture());
		ArgumentCaptor<Event> argumento2 = ArgumentCaptor.forClass(Event.class);
		verify(conect2, times(2)).sendEvent(eq(EventType.JOIN_GAME), argumento2.capture());
		assertTrue(juego.checkTurn(0));
		juego.mark(0);
		assertTrue(juego.checkTurn(1));
		juego.mark(2);
		assertTrue(juego.checkTurn(0));
		juego.mark(4);
		assertTrue(juego.checkTurn(1));
		juego.mark(5);
		assertTrue(juego.checkTurn(0));
		juego.mark(6);
		assertTrue(juego.checkTurn(1));
		juego.mark(8);		
		int [] listaGanador = juego.checkWinner().pos;
		int [] listaFinal = {2,5,8};
		assertArrayEquals(listaFinal, listaGanador);
	}
	
	@Test
	public void testEmpate(){
		/*	X|O|X
		 * 	X|O|O
		 * 	O|X|X
		 */
		ArgumentCaptor<Event> argumento1 = ArgumentCaptor.forClass(Event.class);
		verify(conect1, times(2)).sendEvent(eq(EventType.JOIN_GAME), argumento1.capture());
		ArgumentCaptor<Event> argumento2 = ArgumentCaptor.forClass(Event.class);
		verify(conect2, times(2)).sendEvent(eq(EventType.JOIN_GAME), argumento2.capture());
		assertTrue(juego.checkTurn(0));
		juego.mark(0);
		assertTrue(juego.checkTurn(1));
		juego.mark(1);
		assertTrue(juego.checkTurn(0));
		juego.mark(2);
		assertTrue(juego.checkTurn(1));
		juego.mark(4);
		assertTrue(juego.checkTurn(0));
		juego.mark(3);
		assertTrue(juego.checkTurn(1));
		juego.mark(5);
		assertTrue(juego.checkTurn(0));
		juego.mark(7);
		assertTrue(juego.checkTurn(1));
		juego.mark(6);
		assertTrue(juego.checkTurn(0));
		juego.mark(8);
		assertTrue(juego.checkDraw());
	}
}
