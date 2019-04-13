package com.github.nezuko2038.javasnippets.java8.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * ラムダ式。
 *
 * @since 2014-12-19
 * @author Nezu Mariko<nezuko2038@gmail.com>
 */
public class ListLambdaSample {
	public static void main(String[] args) {
		List<String> strs = Arrays.asList("A11", "1", "01111");
		strs.sort((s1, s2) -> s1.length() - s2.length());

		// ラムダ式を使用した場合
		strs.forEach((s) -> System.out.println(s));
	}
}
