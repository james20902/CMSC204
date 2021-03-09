package Assignments.Assignment3;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;



public class SortedDoubleLinkedList_STUDENT_Test {

	SortedDoubleLinkedList<Matrix> linkedList;

	Matrix a, b, c, d, e;
	MatrixComparator comparator;

	ArrayList<Matrix> correctList;

	@Before
	public void setUp() throws Exception {
		comparator = new MatrixComparator();

		linkedList = new SortedDoubleLinkedList<>(comparator);
		a = new Matrix(1,1);
		b = new Matrix(4,5);
		c = new Matrix(8);
		d = new Matrix(4);
		e = new Matrix(5, 4);

		linkedList.add(a);
		linkedList.add(b);

		correctList = new ArrayList<>(Arrays.asList(a, d, e, b, c));
	}

	@After
	public void tearDown() throws Exception {
		linkedList = null;
		correctList = null;
	}

	@Test
	public void testAddToEnd() {
		assertThrows(UnsupportedOperationException.class, () -> linkedList.addToEnd(c));
	}

	@Test
	public void testAddToFront() {
		assertThrows(UnsupportedOperationException.class, () -> linkedList.addToFront(c));
	}

	@Test
	public void testIteratorSuccessfulNext() {
		linkedList.add(c);
		linkedList.add(d);
		linkedList.add(e);
		ListIterator<Matrix> iterator = linkedList.iterator();
		assertEquals(iterator.next(), a);
		assertEquals(iterator.next(), d);
		assertEquals(iterator.next(), e);
		assertEquals(iterator.next(), b);
		assertEquals(iterator.next(), c);
	}

	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedList.add(c);
		linkedList.add(d);
		linkedList.add(e);
		ListIterator<Matrix> iterator = linkedList.iterator();
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.next();
		assertEquals(iterator.previous(), c);
		assertEquals(iterator.previous(), b);
		assertEquals(iterator.previous(), e);
		assertEquals(iterator.previous(), d);
		assertEquals(iterator.previous(), a);
	}

	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedList.add(c);
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
	public void testAdd() {
		linkedList.add(c);
		linkedList.add(d);
		linkedList.add(e);

		assertEquals(linkedList.toArrayList(), correctList);
	}

	@Test
	public void testRemoveFirst() {
		linkedList.add(c);
		linkedList.add(d);
		linkedList.remove(a, comparator);
		assertEquals(linkedList.getFirst(), d);
	}
	
	@Test
	public void testRemoveEnd() {
		linkedList.add(c);
		linkedList.add(e);
		linkedList.remove(c, comparator);
		assertEquals(linkedList.getLast(), b);
	}

	@Test
	public void testRemoveMiddle() {
		linkedList.add(c);
		linkedList.add(d);
		linkedList.add(e);
		linkedList.remove(e, comparator);
		assertEquals(linkedList.getFirst(), a);
		assertEquals(linkedList.getLast(), c);
		assertEquals(linkedList.getSize(), 4, 0);
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
