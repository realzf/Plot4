package connectfour;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class BoardTest{
    private Board testerA;
    private Board testerB;
    private Board testerC;

    @Before
    public void setup(){
        testerA = new Board();
        testerB = new Board(1,1,1,1);
        testerC = new Board(1,1,2,1);
    }

    @Test
    public void constructorA(){
        assertNotNull(testerA);
        
    }

    @Test
    public void constructorB(){
        assertNotNull(testerA);
    }

    @Test
    public void gettersA(){
        assertEquals(testerA.getDepth(), 0);
        assertEquals(testerA.getColumnInput(), 0);
    }

    @Test
    public void gettersB(){
        assertEquals(testerB.getDepth(), 1);
        assertEquals(testerB.getColumnInput(), 1);
    }

    @Test
    public void setters1(){
        testerA.setDepth();
        assertEquals(testerA.getDepth(), 1);
    }

    @Test
    public void setters2(){
        testerA.setColumnInput(1);
        assertTrue(testerA.setBoard('R'));

        testerA.setColumnInput(2);
        assertTrue(testerA.setBoard('R'));

        testerA.setColumnInput(3);
        assertTrue(testerA.setBoard('R'));

        testerA.setColumnInput(4);
        assertTrue(testerA.setBoard('R'));

        testerA.setColumnInput(5);
        assertTrue(testerA.setBoard('R'));

        testerA.setColumnInput(6);
        assertTrue(testerA.setBoard('R'));

        testerA.setColumnInput(7);
        assertTrue(testerA.setBoard('R'));
    }

    @Test
    public void setters3(){
        assertEquals(testerB.getCurrentPlayer(), 'R');
        assertEquals(testerC.getCurrentPlayer(), 'Y');
    }

    @Test
    public void checkWinner(){
        assertFalse(testerA.checkWinner());
        assertFalse(testerB.checkWinner());
        assertFalse(testerC.checkWinner());
    }

    @Test
    public void files(){
        assertTrue(testerA.saveGame("files.txt"));
        assertFalse(testerA.loadGame("filename.txrt"));

    }
}