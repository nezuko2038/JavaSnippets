package com.github.nezuko2038.javasnippets.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * ストリーム。
 * 
 * @since 2014-12-19
 * @author Nezu Mariko<nezuko2038@gmail.com>
 */
public class StreamSample {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("A", "B");
		list.forEach(s -> System.out.println(s));
		// メソッド参照
		list.forEach(System.out::println);

		Stream<String> st = list.stream();
		st.map(s -> "[" + s + "]");

	}
}
