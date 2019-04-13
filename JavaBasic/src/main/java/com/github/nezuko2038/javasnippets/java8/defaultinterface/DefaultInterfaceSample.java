package com.github.nezuko2038.javasnippets.java8.defaultinterface;

/**
 * デフォルトインターフェイス。
 * 
 * @since 2014-12-19
 * @author Nezu Mariko<nezuko2038@gmail.com>
 */
public class DefaultInterfaceSample {
	interface FooInterface {
		default public String foo() {
			return "Foo";
		}
	}

	public static void main(String[] args) {
		FooInterface foo = new FooInterface() {
		};
		System.out.println(foo.foo());
	}
}
