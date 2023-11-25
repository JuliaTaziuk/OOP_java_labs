package org.matrix;

import java.util.Arrays;

public class Main {
    public static void main(String[] arg) {
        try {
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

            ImmutableMatrix m2 = new ImmutableMatrix(m1);
            Matrix m3 = new Matrix(m1);

            System.out.println("m1:");
            printMatrix(m1);

            System.out.println("m2:");
            printMatrix(m2);

            System.out.println("m3:");
            printMatrix(m3);

            //double[] vector = {1.0, 2.0, 3.0};

            //ImmutableMatrix diagonalMatrix = new ImmutableMatrix(new Matrix().createDiagonalMatrix(vector));
            // System.out.println("Diagonal Matrix:");
            // printMatrix(diagonalMatrix);

            m3 = m3.inverse();
            //printMatrix(m3);

            m1 = m1.multiply(m3);
            //printMatrix(m1);

            Matrix matrix = new ImmutableMatrix(Matrix.createIdentityMatrix(3));
            Matrix result = matrix.multiplyScalar(4);
            printMatrix(result);

            //m1.setValue(2, 2, 3);
            /*
            double[] rowElements = m1.getRowElements(0);
            double[] columnElements = m1.getColumnElements(0);
            System.out.println("Elements of the first row: " + Arrays.toString(rowElements));
            System.out.println("Elements of the first column: " + Arrays.toString(columnElements));
            System.out.println("First element: " + m1.getValue(0, 0));
            */
            /*
            Dimension dimension = m1.getMatrixDimension();
            System.out.println("The size of matrix is %d x %d " dimension.rowsNum, dimension.columnsNum);

            */
            /*
            double[] vector = {1.0, 2.0, 3.0};

            Matrix diagonalMatrix = Matrix.createDiagonalMatrix(vector);

            System.out.println("Diagonal Matrix:");
            printMatrix(diagonalMatrix);
            */

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printMatrix(Matrix matrix) {
        for (int i = 0; i < matrix.getRowsNum(); i++) {
            for (int j = 0; j < matrix.getColumnsNum(); j++) {
                System.out.print(matrix.getValue(i, j) + " ");
            }
            System.out.println();
        }

        System.out.println("\n");


    }
}
