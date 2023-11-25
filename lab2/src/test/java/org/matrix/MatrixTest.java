package org.matrix;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    @Test
    public void testDefaultConstructor() {
        Matrix matrix = new Matrix();
        assertEquals(0, matrix.getRowsNum());
        assertEquals(0, matrix.getColumnsNum());
    }

    @Test
    public void testMatrixInitialization() {
        Matrix matrix = new Matrix(3, 4);
        assertEquals(3, matrix.getRowsNum());
        assertEquals(4, matrix.getColumnsNum());
    }

    @Test
    public void testGetValue() {
        Matrix matrix = new Matrix(2, 2);
        matrix.setValue(0, 0, 1);
        matrix.setValue(0, 1, 2);
        matrix.setValue(1, 0, 3);
        matrix.setValue(1, 1, 4);

        assertEquals(1, matrix.getValue(0, 0));
        assertEquals(2, matrix.getValue(0, 1));
        assertEquals(3, matrix.getValue(1, 0));
        assertEquals(4, matrix.getValue(1, 1));
    }

    @Test
    public void testGetRowElements() {
        Matrix matrix = new Matrix(2,2);
        matrix.setValue(0, 0, 28);
        matrix.setValue(0, 1, 2);
        matrix.setValue(1, 0, 22);
        matrix.setValue(1, 1, 60);

        double[] rowElements = matrix.getRowElements(0);
        assertEquals(28, rowElements[0]);
        assertEquals(2, rowElements[1]);

    }

    @Test
    public void testGetColumnElements() {
        Matrix matrix = new Matrix(2, 2);
        matrix.setValue(0, 0, 28);
        matrix.setValue(0, 1, 2);
        matrix.setValue(1, 0, 22);
        matrix.setValue(1, 1, 60);

        double[] columnElements = matrix.getColumnElements(1);
        assertEquals(2, columnElements[0]);
        assertEquals(60, columnElements[1]);
    }

    @Test
    public void testGetMatrixDimension() {
        Matrix matrix = new Matrix(2, 3);
        matrix.setValue(0, 0, 1);
        matrix.setValue(0, 1, 2);
        matrix.setValue(0, 2, 2);
        matrix.setValue(1, 0, 2);
        matrix.setValue(1, 1, 3);
        matrix.setValue(1, 2, 4);

        Dimension dimension = matrix.getMatrixDimension();

        assertEquals(2, dimension.rowsNum);
        assertEquals(3, dimension.columnsNum);

    }

    @Test
    public void testMatrixAddition() {
        Matrix matrix1 = new Matrix(2, 2);
        matrix1.setValue(0, 0, 1);
        matrix1.setValue(0, 1, 2);
        matrix1.setValue(1, 0, 3);
        matrix1.setValue(1, 1, 4);

        Matrix matrix2 = new Matrix(2, 2);
        matrix2.setValue(0, 0, 5);
        matrix2.setValue(0, 1, 6);
        matrix2.setValue(1, 0, 7);
        matrix2.setValue(1, 1, 8);

        Matrix result = matrix1.add(matrix2);

        assertEquals(6, result.getValue(0, 0));
        assertEquals(8, result.getValue(0, 1));
        assertEquals(10, result.getValue(1, 0));
        assertEquals(12, result.getValue(1, 1));
    }

    @Test
    public void testMatrixScalarMultiplication() {
        Matrix matrix = new Matrix(2, 2);
        matrix.setValue(0, 0, 1);
        matrix.setValue(0, 1, 2);
        matrix.setValue(1, 0, 3);
        matrix.setValue(1, 1, 4);

        Matrix result = matrix.multiplyScalar(2);

        assertEquals(2, result.getValue(0, 0));
        assertEquals(4, result.getValue(0, 1));
        assertEquals(6, result.getValue(1, 0));
        assertEquals(8, result.getValue(1, 1));
    }

    @Test
    public void testMatrixMultiplication() {
        Matrix matrix1 = new Matrix(2, 2);
        matrix1.setValue(0, 0, 4);
        matrix1.setValue(0, 1, 2);
        matrix1.setValue(1, 0, 9);
        matrix1.setValue(1, 1, 0);

        Matrix matrix2 = new Matrix(2, 2);
        matrix2.setValue(0, 0, 3);
        matrix2.setValue(0, 1, 1);
        matrix2.setValue(1, 0, -3);
        matrix2.setValue(1, 1, 4);

        Matrix result = matrix1.multiply(matrix2);

        assertEquals(6, result.getValue(0, 0));
        assertEquals(12, result.getValue(0, 1));
        assertEquals(27, result.getValue(1, 0));
        assertEquals(9, result.getValue(1, 1));
    }

    @Test
    public void testMatrixEquality() {
        Matrix matrix1 = new Matrix(2, 2);
        matrix1.setValue(0, 0, 1);
        matrix1.setValue(0, 1, 2);
        matrix1.setValue(1, 0, 3);
        matrix1.setValue(1, 1, 4);

        Matrix matrix2 = new Matrix(2, 2);
        matrix2.setValue(0, 0, 1);
        matrix2.setValue(0, 1, 2);
        matrix2.setValue(1, 0, 3);
        matrix2.setValue(1, 1, 4);

        assertEquals(matrix1, matrix2);
    }

    @Test
    public void testMatrixInequality() {
        Matrix matrix1 = new Matrix(2, 2);
        matrix1.setValue(0, 0, 1);
        matrix1.setValue(0, 1, 2);
        matrix1.setValue(1, 0, 3);
        matrix1.setValue(1, 1, 4);

        Matrix matrix2 = new Matrix(2, 2);
        matrix2.setValue(0, 0, 5);
        matrix2.setValue(0, 1, 6);
        matrix2.setValue(1, 0, 7);
        matrix2.setValue(1, 1, 8);

        assertNotEquals(matrix1, matrix2);
    }

    @Test
    public void testMatrixTranspose() {
        Matrix matrix = new Matrix(2, 2);
        matrix.setValue(0, 0, 3);
        matrix.setValue(0, 1, 4);
        matrix.setValue(1, 0, 5);
        matrix.setValue(1, 1, 7);

        Matrix transposedMatrix = matrix.transpose();

        assertEquals(3, transposedMatrix.getValue(0, 0));
        assertEquals(4, transposedMatrix.getValue(1, 0));
        assertEquals(5, transposedMatrix.getValue(0, 1));
        assertEquals(7, transposedMatrix.getValue(1, 1));
    }

    @Test
    public void testDiagonalMatrix() {
        double[] vector = {-5, 2.2, 128};

        Matrix matrix = new Matrix(new Matrix().createDiagonalMatrix(vector));

        assertEquals(-5, matrix.getValue(0, 0));
        assertEquals(0, matrix.getValue(0, 1));
        assertEquals(0, matrix.getValue(0, 2));
        assertEquals(0, matrix.getValue(1, 0));
        assertEquals(2.2, matrix.getValue(1, 1));
        assertEquals(0, matrix.getValue(1, 2));
        assertEquals(0, matrix.getValue(2, 0));
        assertEquals(0, matrix.getValue(2, 1));
        assertEquals(128, matrix.getValue(2, 2));
    }
    @Test
    public void testIdentityMatrix() {
        Matrix matrix = Matrix.createIdentityMatrix(2);

        assertEquals(1, matrix.getValue(0, 0));
        assertEquals(0, matrix.getValue(1, 0));
        assertEquals(0, matrix.getValue(0, 1));
        assertEquals(1, matrix.getValue(1, 1));
    }

    @Test
    public void testIdentityMatrixUsingNegativeDim() {
        assertThrows(IllegalArgumentException.class,
                () -> Matrix.createIdentityMatrix(-1));
    }


    @Test
    public void testMatrixInverse() {
        Matrix m1 = new Matrix(3, 3);
        m1.setValue(0, 0, 1);
        m1.setValue(0, 1, -1);
        m1.setValue(0, 2, 1);
        m1.setValue(1, 0, 2);
        m1.setValue(1, 1, 2);
        m1.setValue(1, 2, 3);
        m1.setValue(2, 0, -2);
        m1.setValue(2, 1, 4);
        m1.setValue(2, 2, -1);

        Matrix inversedMatrix = m1.inverse();

        assertEquals(-7, inversedMatrix.getValue(0, 0));
        assertEquals(1.5, inversedMatrix.getValue(0, 1));
        assertEquals(-2.5, inversedMatrix.getValue(0, 2));
        assertEquals(-2, inversedMatrix.getValue(1, 0));
        assertEquals(0.5, inversedMatrix.getValue(1, 1));
        assertEquals(-0.5, inversedMatrix.getValue(1, 2));
        assertEquals(6, inversedMatrix.getValue(2, 0));
        assertEquals(-1, inversedMatrix.getValue(2, 1));
        assertEquals(2, inversedMatrix.getValue(2, 2));

    }

}
