package org.matrix;

import java.util.Arrays;

public class Matrix {

    private int rows, columns;
    double[][] elements;

    public Matrix() {
        this(0, 0);
    }

    public Matrix(int rows, int columns) {
        if (rows < 0 || columns < 0) {
            throw new IllegalArgumentException("The size of matrix cannot be negative.");
        }

        this.rows = rows;
        this.columns = columns;

        elements = new double[rows][columns];
    }

    public int getRowsNum() {
        return rows;
    }

    public void setRowsNum(int rows) {
        if (rows < 0) {
            throw new IllegalArgumentException("The number of rows cannot be negative");
        }
        this.rows = rows;
    }

    public double[] getRowElements(int rowI) {
        checkIndex(rowI, 0);
        return elements[rowI];
    }

    public int getColumnsNum() {
        return columns;
    }

    public void setColumnsNum(int columns) {
        if (columns < 0) {
            throw new IllegalArgumentException("The number of columns cannot be negative.");
        }

        this.columns = columns;
    }

    public double[] getColumnElements(int columnIndex) {
        checkIndex(0, columnIndex);

        double[] columnElements = new double[getRowsNum()];
        for (int rowI = 0; rowI < getRowsNum(); rowI++) {
            columnElements[rowI] = elements[rowI][columnIndex];
        }

        return columnElements;
    }

    public void setValue(int rowI, int columnI, double element) {
        if (rowI < 0 || rowI >= rows || columnI < 0 || columnI >= columns) {
            throw new IllegalArgumentException("Invalid row or column index");
        }

        elements[rowI][columnI] = element;
    }

    public double getValue(int rowI, int columnI) {
        if (rowI < 0 || rowI >= rows || columnI < 0 || columnI >= columns) {
            throw new IllegalArgumentException("Invalid row or column index");
        }

        return elements[rowI][columnI];
    }

    private void checkIndex(int rowI, int columnI) {
        if (rowI < 0 || rowI >= getRowsNum() || columnI < 0 || columnI >= getColumnsNum()) {
            throw new IllegalArgumentException();
        }
    }

    public Matrix(Matrix matrix) {
        this(matrix.getRowsNum(), matrix.getColumnsNum());
        for (int rowI = 0; rowI < getRowsNum(); rowI++) {
            System.arraycopy(matrix.elements[rowI], 0, elements[rowI], 0, getColumnsNum());
        }
    }

    public Dimension getMatrixDimension() {
        return new Dimension(getRowsNum(), getColumnsNum());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Matrix otherMatrix = (Matrix) obj;
        if (getRowsNum() != otherMatrix.getRowsNum() || getColumnsNum() != otherMatrix.getColumnsNum()) {
            return false;
        }

        for (int rowI = 0; rowI < getRowsNum(); rowI++) {
            if (!Arrays.equals(elements[rowI], otherMatrix.elements[rowI])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(elements);
    }

    public Matrix add(Matrix matrix) {
        if (this.getRowsNum() != matrix.getRowsNum() || this.getColumnsNum() != matrix.getColumnsNum()) {
            throw new IllegalArgumentException("Matrices must have the same dimensions.");
        }

        Matrix result = new Matrix(this.getRowsNum(), this.getColumnsNum());

        for (int rowI = 0; rowI < this.getRowsNum(); rowI++) {
            for (int columnI = 0; columnI < this.getColumnsNum(); columnI++) {
                result.setValue(rowI, columnI, this.getValue(rowI, columnI) + matrix.getValue(rowI, columnI));
            }
        }

        return result;
    }

    public Matrix multiplyScalar(double scalar) {
        Matrix result = new Matrix(this.getRowsNum(), this.getColumnsNum());

        for (int rowI = 0; rowI < this.getRowsNum(); rowI++) {
            for (int columnI = 0; columnI < this.getColumnsNum(); columnI++) {
                result.setValue(rowI, columnI, this.getValue(rowI, columnI) * scalar);
            }
        }

        return result;
    }

    public Matrix multiply(Matrix matrix) {
        if (this.getColumnsNum() != matrix.getRowsNum()) {
            throw new IllegalArgumentException("Number of columns in the first matrix must be equal to the number of rows in the second matrix");
        }

        Matrix result = new Matrix(this.getRowsNum(), matrix.getColumnsNum());
        for (int rowI = 0; rowI < this.getRowsNum(); rowI++) {
            for (int columnI = 0; columnI < matrix.getColumnsNum(); columnI++) {
                double sum = 0.0;
                for (int k = 0; k < this.getColumnsNum(); k++) {
                    sum += this.getValue(rowI, k) * matrix.getValue(k, columnI);
                }
                result.setValue(rowI, columnI, sum);
            }
        }
        return result;
    }

    public Matrix transpose() {
        Matrix transposedMatrix = new Matrix(this.getColumnsNum(), this.getRowsNum());

        for (int rowI = 0; rowI < this.getRowsNum(); rowI++) {
            for (int columnI = 0; columnI < this.getColumnsNum(); columnI++) {
                transposedMatrix.setValue(columnI, rowI, this.getValue(rowI, columnI));
            }
        }

        return transposedMatrix;
    }

    public Matrix createDiagonalMatrix(double[] vector) {
        int size = vector.length;
        Matrix diagonalMatrix = new Matrix(size, size);

        for (int i = 0; i < size; i++) {
            diagonalMatrix.setValue(i, i, vector[i]);
        }

        return diagonalMatrix;
    }

    public static Matrix createIdentityMatrix(int dim) {
        if (dim < 0) {
            throw new IllegalArgumentException("The size of matrix cannot be a negative number.");
        }

        Matrix identityMatrix = new Matrix(dim, dim);

        for (int i = 0; i < dim; i++) {
            identityMatrix.setValue(i, i, 1.0);
        }

        return identityMatrix;
    }

    public static Matrix createRandomRow(int columnsNum) {
        if (columnsNum < 0) {
            throw new IllegalArgumentException("The number of columns cannot be negative.");
        }

        Matrix result = new Matrix(1, columnsNum);

        for (int columnI = 0; columnI < columnsNum; columnI++) {
            result.elements[0][columnI] = Math.random();
        }

        return result;
    }

    public static Matrix createRandomColumn(int rowsNum) {
        if (rowsNum < 0) {
            throw new IllegalArgumentException("The number of rows cannot be negative.");
        }

        Matrix result = new Matrix(rowsNum, 1);

        for (int rowI = 0; rowI < rowsNum; rowI++) {
            result.elements[rowI][0] = Math.random();
        }

        return result;
    }

    private Matrix setUpAugmentedMatrix() {
        Matrix augmentedMatrix = new Matrix(getRowsNum(), getColumnsNum() * 2);

        for (int i = 0; i < getRowsNum(); i++) {
            for (int j = 0; j < getColumnsNum(); j++) {
                augmentedMatrix.setValue(i, j, getValue(i, j));
            }
        }

        Matrix identityMatrix = createIdentityMatrix(getRowsNum());
        for (int i = 0; i < getRowsNum(); i++) {
            for (int j = 0; j < getRowsNum(); j++) {
                augmentedMatrix.setValue(i, j + getColumnsNum(), identityMatrix.getValue(i, j));
            }
        }

        return augmentedMatrix;
    }

    public Matrix inverse() {
        if (getRowsNum() != getColumnsNum()) {
            throw new IllegalArgumentException("Matrix is not square. Inverse matrix does not exist.");
        }

        Matrix augmentedMatrix = setUpAugmentedMatrix();

        for (int rowI = 0; rowI < getRowsNum(); rowI++) {
            double divisor = augmentedMatrix.getValue(rowI, rowI);

            if (divisor == 0) {
                int nonZeroRow = findNonZeroRow(augmentedMatrix, rowI);
                if (nonZeroRow == -1) {
                    throw new ArithmeticException("Matrix is singular. Inverse matrix does not exist.");
                }

                augmentedMatrix.swapRows(rowI, nonZeroRow);
                divisor = augmentedMatrix.getValue(rowI, rowI);

            }

            for (int columnI = 0; columnI < getColumnsNum() * 2; columnI++) {
                augmentedMatrix.setValue(rowI, columnI, augmentedMatrix.getValue(rowI, columnI) / divisor);

            }

            for (int j = 0; j < getRowsNum(); j++) {
                if (rowI != j) {
                    double multiplier = augmentedMatrix.getValue(j, rowI);
                    for (int k = 0; k < getColumnsNum() * 2; k++) {
                        augmentedMatrix.setValue(j, k, augmentedMatrix.getValue(j, k) - multiplier * augmentedMatrix.getValue(rowI, k));

                    }
                }
            }
        }

        Matrix inverse = new Matrix(getRowsNum(), getColumnsNum());
        for (int rowI = 0; rowI < getRowsNum(); rowI++) {
            for (int columnI = 0; columnI < getRowsNum(); columnI++) {
                inverse.setValue(rowI, columnI, augmentedMatrix.getValue(rowI, columnI + getColumnsNum()));
            }
        }

        return inverse;
    }

    public void swapRows(int row1, int row2) {
        double[] tempRow = elements[row1];
        elements[row1] = elements[row2];
        elements[row2] = tempRow;
    }

    private int findNonZeroRow(Matrix matrix, int col) {
        for (int row = col + 1; row < matrix.getRowsNum(); row++) {
            if (matrix.getValue(row, col) != 0) {
                return row;
            }
        }
        return -1;
    }
}

