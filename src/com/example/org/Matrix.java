package com.example.org;

import com.example.org.exception.MatrixIndexOutOfBoundsException;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;

public class Matrix {

  private int[][] data;
  private int size;

  public Matrix(int size) {
    this.size = size;
    this.data = new int[size][size];
  }

  public int getSize() {
    return size;
  }

  public int getValue(int row, int col) {
    checkBounds(row, col);
    return data[row][col];
  }

  public void setValue(int row, int col, int value) {
    checkBounds(row, col);
    data[row][col] = value;
  }

  public boolean isSymmetrical() {
    for (int i = 0; i < getSize(); i++) {
      for (int j = 0; j < getSize(); j++) {
        if (getValue(i, j) != getValue(j, i)) {
          return false;
        }
      }
    }
    return true;
  }

  private void checkBounds(int row, int col) {
    if (row < 0) {
      throw new MatrixIndexOutOfBoundsException(MessageFormat.format("Row index (value: {0}) cannot be negative value: ", row));
    } else if (row >= getSize()) {
      throw new MatrixIndexOutOfBoundsException(MessageFormat.format("Row index (value: {0}) exceeds matrix size", row));
    } else if (col < 0) {
      throw new MatrixIndexOutOfBoundsException(MessageFormat.format("Column index (value: {0}) cannot be negative value", row));
    } else if (col >= getSize()) {
      throw new MatrixIndexOutOfBoundsException(MessageFormat.format("Row index (value: {0}) exceeds matrix size", row));
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Matrix)) {
      return false;
    }
    Matrix matrix = (Matrix) o;
    return getSize() == matrix.getSize() && Arrays.equals(data, matrix.data);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(getSize());
    result = 31 * result + Arrays.hashCode(data);
    return result;
  }
}
