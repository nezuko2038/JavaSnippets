package com.github.nezuko2038.javasnippets.swing;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

/**
 * GraphicsEnvironment
 *
 * @since 2019-03-10
 * @author Nezu Mariko<nezuko2038@gmail.com>
 *
 */
public class GraphicsEnvironmentSample {
	public static void main(String[] args) {

		String str = System.getProperty("sun.java2d.opengl");
		System.out.println("sun.java2d.opengl:" + str);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		for (int j = 0; j < gs.length; j++) {

			GraphicsDevice gd = gs[j];
			GraphicsConfiguration[] gc = gd.getConfigurations();
			for (int i = 0; i < gc.length; i++) {
				System.out.println("GraphicsConfiguration#getDefaultTransform()=" + gc[i].getDefaultTransform());
				System.out.println("GraphicsConfiguration#getBounds()=" + gc[i].getBounds());

			}
		}
	}
}
