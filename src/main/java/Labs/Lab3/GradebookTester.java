package Labs.Lab3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GradebookTester {

    GradeBook bookOne;
    GradeBook bookTwo;

    @Before
    public void setUp(){
        bookOne = new GradeBook(5);
        bookOne.addScore(91);
        bookOne.addScore(68);
        bookTwo = new GradeBook(5);
        bookTwo.addScore(55);
        bookTwo.addScore(85);
        bookTwo.addScore(79);
        bookTwo.addScore(89);
    }

    @After
    public void tearDown(){
        bookOne = null;
        bookTwo = null;
    }

    @Test
    public void testAddScore(){
        assertEquals(bookOne.toString(), "91.0 68.0");
        assertEquals(bookTwo.toString(), "55.0 85.0 79.0 89.0");

        assertEquals(bookOne.getScoreSize(), 2, 0);
        assertEquals(bookTwo.getScoreSize(), 4, 0);
    }

    @Test
    public void testSum(){
        assertEquals(bookOne.sum(), 159, 0);
        assertEquals(bookTwo.sum(), 308, 0);
    }

    @Test
    public void testMinimum(){
        assertEquals(bookOne.minimum(), 68, 0);
        assertEquals(bookTwo.minimum(), 55, 0);
    }

    @Test
    public void testFinalScore(){
        assertEquals(bookOne.finalScore(), 91, 0);
        assertEquals(bookTwo.finalScore(), 253, 0);
    }


}
