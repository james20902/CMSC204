package Assignments.Assignment3;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class BasicDoubleLinkedList_STUDENT_Test {

	BasicDoubleLinkedList<Matrix> linkedList;

	Matrix a, b, c, d, e;
	MatrixComparator comparator;

	ArrayList<Matrix> correctList;

	@Before
	public void setUp() throws Exception {
		linkedList = new BasicDoubleLinkedList<>();
		a = new Matrix(1,1);
		b = new Matrix(4,5);
		c = new Matrix(8);
		d = new Matrix(4);
		e = new Matrix(5, 4);

		linkedList.addToEnd(a);
		linkedList.addToEnd(b);

		comparator = new MatrixComparator();

		correctList = new ArrayList<>(Arrays.asList(a, b, c, d, e));
	}

	@After
	public void tearDown() throws Exception {
		linkedList = null;
		correctList = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(linkedList.getSize(), 2, 0);
	}
	
	@Test
	public void testAddToEnd() {
		assertEquals(b, linkedList.getLast());
		linkedList.addToEnd(c);
		linkedList.addToEnd(d);
		assertEquals(d, linkedList.getLast());
	}
	
	@Test
	public void testAddToFront() {
		assertEquals(a, linkedList.getFirst());
		linkedList.addToFront(e);
		assertEquals(e, linkedList.getFirst());
	}
	
	@Test
	public void testGetFirst() {
		assertEquals(a, linkedList.getFirst());
	}

	@Test
	public void testGetLast() {
		assertEquals(b, linkedList.getLast());
	}
	
	@Test
	public void testToArrayList() {
		linkedList.addToEnd(c);
		linkedList.addToEnd(d);
		linkedList.addToEnd(e);
		assertEquals(linkedList.toArrayList(), correctList);
	}
	
	@Test
	public void testIteratorSuccessfulNext() {
		linkedList.addToEnd(c);
		linkedList.addToEnd(d);
		linkedList.addToEnd(e);
		ListIterator<Matrix> iterator = linkedList.iterator();
		assertTrue(iterator.hasNext());
		assertEquals(a, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(c, iterator.next());
		assertTrue(iterator.hasNext());
		assertEquals(d, iterator.next());
		assertEquals(e, iterator.next());
		assertFalse(iterator.hasNext());
	}
	
	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedList.addToEnd(c);
		linkedList.addToEnd(d);
		linkedList.addToEnd(e);
		ListIterator<Matrix> iterator = linkedList.iterator();
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.next();
		assertTrue(iterator.hasPrevious());
		assertEquals(e, iterator.previous());
		assertEquals(d, iterator.previous());
		assertEquals(c, iterator.previous());
		assertEquals(b, iterator.previous());
		assertEquals(a, iterator.previous());
		assertFalse(iterator.hasPrevious());
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedList.addToEnd(c);
		ListIterator<Matrix> iterator = linkedList.iterator();
		iterator.next();
		iterator.next();
		iterator.next();
		assertThrows(NoSuchElementException.class, iterator::next);
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionPrevious() {
		ListIterator<Matrix> iterator = linkedList.iterator();
		assertThrows(NoSuchElementException.class, iterator::previous);
	}
	
	@Test
	public void testIteratorUnsupportedOperationException() {
		ListIterator<Matrix> iterator = linkedList.iterator();
		assertThrows(UnsupportedOperationException.class, iterator::nextIndex);
	}
	
	@Test
	public void testRemove() {
		linkedList.addToEnd(c);
		assertEquals(a, linkedList.getFirst());
		assertEquals(c, linkedList.getLast());
		linkedList.addToFront(d);
		assertEquals(d, linkedList.getFirst());
		linkedList.remove(d, comparator);
		assertEquals(a, linkedList.getFirst());
	}

	@Test
	public void testRetrieveFirstElement() {
		linkedList.addToEnd(c);
		assertEquals(a, linkedList.retrieveFirstElement());
		assertEquals(b, linkedList.getFirst());
	}

	@Test
	public void testRetrieveLastElement() {
		linkedList.addToEnd(c);
		assertEquals(c, linkedList.retrieveLastElement());
		assertEquals(b, linkedList.getLast());
	}

	class MatrixComparator implements Comparator<Matrix>{
		@Override
		public int compare(Matrix o1, Matrix o2) {
			return (int)Math.signum((o1.row*o1.column) - (o2.row*o2.column));
		}
	}

	class Matrix{
		int row, column;

		public Matrix(int row, int column){
			this.row = row;
			this.column = column;
		}

		public Matrix(int square){
			this(square, square);
		}
	}

}
