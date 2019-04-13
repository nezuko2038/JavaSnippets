package com.github.nezuko2038.javasnippets.java8.staticinterface;

interface Foo {
	static int getValue() {
		return 100;
	}
}

class FooImpl implements Foo {
	static int getValue() {
		return 200;
	}
}

/**
 * Static インターフェイス
 *
 * @since 2014-12-19
 * @author Nezu Mariko<nezuko2038@gmail.com>
 */
public class StaticInterfaceSample {
	public static void main(String[] args) {

		System.out.println(Foo.getValue());
		System.out.println(FooImpl.getValue());
	}
}
