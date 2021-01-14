package com.example.org;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MatrixReader {

  private Matrix parseMatrixString(String matrixString) {
    StringMatrixParser parser = new StringMatrixParser();
    return parser.parse(matrixString);
  }

  public Matrix read(InputStream stream) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
    StringBuilder matrixStringBuilder = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null && !line.isEmpty() ) {
      matrixStringBuilder.append(line).append("\n");
    }
    return parseMatrixString(matrixStringBuilder.toString());
  }
}
