package org.matrix;

public final class ImmutableMatrix extends Matrix{

    public ImmutableMatrix() {
        super();
    }

    public ImmutableMatrix(int rows, int columns) {
        super(rows, columns);
    }

    public ImmutableMatrix(Matrix matrix) {
        super(matrix);
    }

    @Override
    public void setValue(int rowI, int columnI, double elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setRowsNum(int rows) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setColumnsNum(int columns) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Matrix add(Matrix matrix) {
        return new Matrix(this).add(matrix);
    }

    @Override
    public Matrix multiplyScalar(double scalar) {
        return new Matrix(this).multiplyScalar(scalar);
    }

    @Override
    public Matrix createDiagonalMatrix(double[] vector) {
        return new Matrix(this).createDiagonalMatrix(vector);
    }

    @Override
    public Matrix multiply(Matrix matrix) {
        return new Matrix(this).multiply(matrix);
    }

    @Override
    public Matrix transpose() {
        return new Matrix(this).transpose();
    }

    @Override
    public Matrix inverse() {
        return new Matrix(this).inverse();
    }


}
