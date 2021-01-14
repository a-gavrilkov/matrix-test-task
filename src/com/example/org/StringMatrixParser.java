package com.example.org;

import com.example.org.exception.MatrixParseException;

import java.text.MessageFormat;

public class StringMatrixParser {

  private final String colSeparator;
  private final String rowSeparator;

  public StringMatrixParser(String colSeparator, String rowSeparator) {
    this.colSeparator = colSeparator;
    this.rowSeparator = rowSeparator;
  }

  public StringMatrixParser() {
    this(" ", "\n");
  }

  public Matrix parse(String matrixString) {
    String[] rows = matrixString.split(rowSeparator);
    Matrix matrix = new Matrix(rows.length);

    for (int i = 0; i < matrix.getSize(); i++) {
      String[] cols = rows[i].split(colSeparator);
      if (matrix.getSize() != cols.length) {
        throw new MatrixParseException(MessageFormat.format(
          "Matrix size should be N x N, but found row with {0} columns. Number of rows: {1}", cols.length, matrix.getSize()));
      }
      for (int j = 0; j < matrix.getSize(); j++) {
        int parsedValue = Integer.parseInt(cols[j]);
        matrix.setValue(i, j, parsedValue);
      }
    }
    return matrix;
  }
}
