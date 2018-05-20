package net.nantgarth.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class FileUtil {

	/**
	 * Reads a file from the file system.
	 * @param path The path to the file to read.
	 * @return A string containing the contents of the file or <code>null</code> if the file could not be found.
	 */
	public static String read(String path) {
		try {
			return new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			System.err.println("Could not find file: " + path);
			return null;
		}
	}
}
