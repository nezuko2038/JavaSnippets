package com.github.nezuko2038.javasnippets.java8.lambda;

/**
 *
 * @since 2014-12-19
 * @author Nezu Mariko<nezuko2038@gmail.com>
 */
public class SimpleLambda {
	@FunctionalInterface
	interface Foo {
		/**
		 * @param str 文字列
		 */
		public void say(String str);
	}

	public static void main(String[] args) {

		Foo obj2 = (str2) -> System.out.println(str2);
		obj2.say("A");

	}

}
