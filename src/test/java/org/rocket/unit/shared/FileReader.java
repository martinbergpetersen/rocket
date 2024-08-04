package org.rocket.unit.shared;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

  public String from(String path) throws IOException {
    return new String(Files.readAllBytes(Paths.get(path)));
  }
}
