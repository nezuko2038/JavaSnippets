package com.github.nezuko2038.javasnippets.io;

/**
 *
 * @since 2019-04-14
 * @author Nezu Mariko<nezuko2038@gmail.com>
 *
 */
public class 文字列を半角1文字全角2文字でカウント {
	static int countZen2Han1(String str) {
		// 戻り値
		int count = 0;

		char[] carray = str.toCharArray();
		for (int i = 0; i < carray.length; i++) {
			if (String.valueOf(carray[i]).getBytes().length <= 1) {
				count += 1; // 半角文字なら＋１
			} else {
				count += 2; // 全角文字なら＋２
			}
		}

		return count;
	}

}
