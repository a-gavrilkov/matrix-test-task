package com.example.org;

import com.example.org.exception.MatrixParseException;

import java.io.*;
import java.text.MessageFormat;

public class Main {

  static class Config {
    boolean useFile;
    String filePath;
  }

  private static Config parseCommandLineArgs(String[] args) {
    Config config = new Config();
    if (args.length == 1) {
      config.useFile = true;
      config.filePath = args[0];
    } else {
      config.useFile = false;
    }
    return config;
  }

  public static void main(String[] args) {
    Config config = parseCommandLineArgs(args);
    Matrix matrix;

    try {
      InputStream stream;
      if (config.useFile) {
        System.out.println(MessageFormat.format("Чтение матрицы из файла: {0}", config.filePath));
        stream = new FileInputStream(new File(config.filePath));
      } else {
        System.out.println("Введите матрицу, разделяя числа в строке пробелами:");
        stream = System.in;
      }
      MatrixReader reader = new MatrixReader();
      matrix = reader.read(stream);
    } catch (MatrixParseException e) {
      System.out.println("Введенная матрица не является квадратной");
      return;
    } catch (FileNotFoundException e) {
      System.out.println(MessageFormat.format("Не найден файл по указанному пути: {0}", config.filePath));
      return;
    } catch (IOException e) {
      System.out.println("Не удалось прочитать матрицу");
      e.printStackTrace();
      return;
    }

    if (matrix.isSymmetrical()) {
      System.out.println("ДА, является");
    } else {
      System.out.println("НЕТ, не является");
    }
  }
}
