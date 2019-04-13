package com.github.nezuko2038.javasnippets.swing;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

/**
 *
 * @author Nezu Mariko<nezuko2038@gmail.com>
 *
 */
public class FontList {
	public static void main(String[] args) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Font fonts[] = ge.getAllFonts();
		for (Font f : fonts) {
			System.out.println(f.getName());
		}

	}
}
