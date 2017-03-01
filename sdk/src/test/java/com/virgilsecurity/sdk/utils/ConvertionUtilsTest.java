/*
 * Copyright (c) 2016, Virgil Security, Inc.
 *
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * * Neither the name of virgil nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.virgilsecurity.sdk.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.virgilsecurity.sdk.utils.ConvertionUtils;

/**
 * Unit tests for {@linkplain ConvertionUtils}.
 *
 * @author Andrii Iakovenko
 *
 */
public class ConvertionUtilsTest {

	private static final String TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

	@Test
	public void base64String() {
		String base64string = ConvertionUtils.toBase64String(TEXT);
		String str = ConvertionUtils.base64ToString(base64string);

		assertEquals(TEXT, str);
	}

	@Test
	public void base64ByteArray() {
		byte[] base64bytes = ConvertionUtils.toBase64Bytes(TEXT);
		String str = ConvertionUtils.base64ToString(base64bytes);

		assertEquals(TEXT, str);
	}

	@Test
	public void toBytes() {
		byte[] bytes = ConvertionUtils.toBytes(TEXT);
		String str = ConvertionUtils.toString(bytes);
		assertEquals(TEXT, str);
	}

	@Test
	public void toHEX() {
		byte[] bytes = ConvertionUtils.toBytes(TEXT);
		String str = ConvertionUtils.toHex(bytes);
		bytes = ConvertionUtils.hexToBytes(str);
		str = ConvertionUtils.toString(bytes);
		assertEquals(TEXT, str);
	}

}