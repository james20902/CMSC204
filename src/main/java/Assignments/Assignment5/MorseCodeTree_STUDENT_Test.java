package Assignments.Assignment5;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class MorseCodeTree_STUDENT_Test {

    ArrayList<String> list = new ArrayList<>(Arrays.asList("h", "s", "v", "i", "f", "u", "e", "l", "r", "a", "p", "w", "j", "", "b", "d", "x", "n", "c", "k", "y", "t", "z", "g", "q", "m", "o"));
    MorseCodeTree tree;

    @Before
    public void setUp() throws Exception {
        tree = new MorseCodeTree();
    }

    @After
    public void tearDown() throws Exception {
        tree = null;
    }

    @Test
    public void testToArrayList() {
        assertEquals(list, tree.toArrayList());
    }

    @Test
    public void testInsertion(){
        tree.insert("--.-.", "left insert");
        tree.insert("--.--", "right insert");
        list.add(24, "left insert");
        list.add(26, "right insert");
        assertEquals(list, tree.toArrayList());
    }

    @Test
    public void testFetch(){
        assertEquals("e", tree.fetch("."));
        assertEquals("t", tree.fetch("-"));

        assertEquals("p", tree.fetch(".--."));
        assertEquals("j", tree.fetch(".---"));
    }

    @Test
    public void testUnsupportedOperations(){
        assertThrows(UnsupportedOperationException.class, () -> {tree.delete("fail");});
        assertThrows(UnsupportedOperationException.class, tree::update);
    }



}
