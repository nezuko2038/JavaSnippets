package com.github.nezuko2038.javasnippets.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 *
 * @see 文字列を半角1文字全角2文字でカウント
 * @author Nezu Mariko<nezuko2038@gmail.com>
 * @since 2019-04-14
 */
class 文字列を半角1文字全角2文字でカウントTest {

	@Test
	void test() {
		assertEquals(1, 文字列を半角1文字全角2文字でカウント.countZen2Han1("1"));
		assertEquals(4, 文字列を半角1文字全角2文字でカウント.countZen2Han1("漢字"));
	}

}
