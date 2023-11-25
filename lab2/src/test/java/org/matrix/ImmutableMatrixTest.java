package org.matrix;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImmutableMatrixTest {

    @Test
    public void testDefaultConstructor() {
        ImmutableMatrix matrix = new ImmutableMatrix();
        assertEquals(0, matrix.getRowsNum());
        assertEquals(0, matrix.getColumnsNum());
    }

    @Test
    public void testParameterizedConstructor() {
        ImmutableMatrix matrix = new ImmutableMatrix(3, 4);
        assertEquals(3, matrix.getRowsNum());
        assertEquals(4, matrix.getColumnsNum());
    }



    @Test
    public void testImmutableSetValue() {
        ImmutableMatrix matrix = new ImmutableMatrix(2, 2);
        assertThrows(UnsupportedOperationException.class,
                () -> matrix.setValue(0, 0, 5.0));
    }

    @Test
    public void testImmutableSetRowsNum() {
        ImmutableMatrix matrix = new ImmutableMatrix(3, 3);
        assertThrows(UnsupportedOperationException.class,
                () -> matrix.setRowsNum(4));
    }

    @Test
    public void testImmutableSetColumnsNum() {
        ImmutableMatrix matrix = new ImmutableMatrix(3, 3);
        assertThrows(UnsupportedOperationException.class,
                () -> matrix.setColumnsNum(4));
    }

    @Test
    public void testCopyOfMatrix() {
        Matrix matrix = new Matrix(3, 3);
        matrix.setValue(0, 0, 1);
        matrix.setValue(0, 1, -1);
        matrix.setValue(0, 2, 1);
        matrix.setValue(1, 0, 2);
        matrix.setValue(1, 1, 2);
        matrix.setValue(1, 2, 3);
        matrix.setValue(2, 0, -2);
        matrix.setValue(2, 1, 4);
        matrix.setValue(2, 2, -1);

        ImmutableMatrix m2 = new ImmutableMatrix(matrix);

        assertEquals(1, m2.getValue(0, 0));
        assertEquals(-1, m2.getValue(0, 1));
        assertEquals(1, m2.getValue(0, 2));
        assertEquals(2, m2.getValue(1, 0));
        assertEquals(2, m2.getValue(1, 1));
        assertEquals(3, m2.getValue(1, 2));
        assertEquals(-2, m2.getValue(2, 0));
        assertEquals(4, m2.getValue(2, 1));
        assertEquals(-1, m2.getValue(2, 2));
    }

    @Test
    public void testDiagonalImmutableCreation() {
        double[] vector = {1.0, 2.0, 3.0};
        ImmutableMatrix matrix = new ImmutableMatrix(new Matrix().createDiagonalMatrix(vector));

        assertEquals(1, matrix.getValue(0, 0));
        assertEquals(0, matrix.getValue(0, 1));
        assertEquals(0, matrix.getValue(0, 2));
        assertEquals(0, matrix.getValue(1, 0));
        assertEquals(2, matrix.getValue(1, 1));
        assertEquals(0, matrix.getValue(1, 2));
        assertEquals(0, matrix.getValue(2, 0));
        assertEquals(0, matrix.getValue(2, 1));
        assertEquals(3, matrix.getValue(2, 2));
    }

}
