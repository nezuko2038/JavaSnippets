package com.github.nezuko2038.javasnippets.java7.io;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * UTF-8のバイト読み書きサンプル。
 *
 *
 * @author Nezu Mariko<nezuko2038@gmail.com>
 * @since 2019-04-14
 */
public class FilesIOSample {
	public static void main(String[] args) {
		Path path = Paths.get("sample.dat");
		ByteBuffer buf = createBuffer();

		// write
		try {
			Files.write(path, buf.array());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// read
		try {
			byte[] readBytes = Files.readAllBytes(path);
			assert (Arrays.equals(readBytes, buf.array()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static ByteBuffer createBuffer() {
		ByteBuffer buf = ByteBuffer.allocate(100);
		String str = "UTF-8の日本語";
		// String -> byte[]
		byte[] strBytes = str.getBytes(StandardCharsets.UTF_8);
		buf.put(strBytes);
		buf.position(0);
		return buf;
	}
}
