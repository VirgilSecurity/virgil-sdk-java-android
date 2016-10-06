/**
 * Copyright (C) 2016 Virgil Security Inc.
 *
 * Lead Maintainer: Virgil Security Inc. <support@virgilsecurity.com>
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *     (1) Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *     (2) Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in
 *     the documentation and/or other materials provided with the
 *     distribution.
 *
 *     (3) Neither the name of the copyright holder nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package com.virgilsecurity.crypto;

public class VirgilStreamSigner implements java.lang.AutoCloseable {
	private transient long swigCPtr;
	protected transient boolean swigCMemOwn;

	protected VirgilStreamSigner(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(VirgilStreamSigner obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				virgil_crypto_javaJNI.delete_VirgilStreamSigner(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	@Override
	public void close() {
		delete();
	}

	public VirgilStreamSigner(VirgilHash.Algorithm hashAlgorithm) {
		this(virgil_crypto_javaJNI.new_VirgilStreamSigner__SWIG_0(hashAlgorithm.swigValue()), true);
	}

	public VirgilStreamSigner() {
		this(virgil_crypto_javaJNI.new_VirgilStreamSigner__SWIG_1(), true);
	}

	public byte[] sign(VirgilDataSource source, byte[] privateKey, byte[] privateKeyPassword) {
		return virgil_crypto_javaJNI.VirgilStreamSigner_sign__SWIG_0(swigCPtr, this, VirgilDataSource.getCPtr(source),
				source, privateKey, privateKeyPassword);
	}

	public byte[] sign(VirgilDataSource source, byte[] privateKey) {
		return virgil_crypto_javaJNI.VirgilStreamSigner_sign__SWIG_1(swigCPtr, this, VirgilDataSource.getCPtr(source),
				source, privateKey);
	}

	public boolean verify(VirgilDataSource source, byte[] sign, byte[] publicKey) {
		return virgil_crypto_javaJNI.VirgilStreamSigner_verify(swigCPtr, this, VirgilDataSource.getCPtr(source), source,
				sign, publicKey);
	}

}
