/*
 * Copyright (c) 2015-2018, Virgil Security, Inc.
 *
 * Lead Maintainer: Virgil Security Inc. <support@virgilsecurity.com>
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     (1) Redistributions of source code must retain the above copyright notice, this
 *     list of conditions and the following disclaimer.
 *
 *     (2) Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 *     (3) Neither the name of virgil nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
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
package com.virgilsecurity.crypto;

public class VirgilKeyPair implements java.lang.AutoCloseable {
    public final static class Type {
        public final static VirgilKeyPair.Type RSA_256 = new VirgilKeyPair.Type("RSA_256");
        public final static VirgilKeyPair.Type RSA_512 = new VirgilKeyPair.Type("RSA_512");
        public final static VirgilKeyPair.Type RSA_1024 = new VirgilKeyPair.Type("RSA_1024");
        public final static VirgilKeyPair.Type RSA_2048 = new VirgilKeyPair.Type("RSA_2048");
        public final static VirgilKeyPair.Type RSA_3072 = new VirgilKeyPair.Type("RSA_3072");
        public final static VirgilKeyPair.Type RSA_4096 = new VirgilKeyPair.Type("RSA_4096");
        public final static VirgilKeyPair.Type RSA_8192 = new VirgilKeyPair.Type("RSA_8192");
        public final static VirgilKeyPair.Type EC_SECP192R1 = new VirgilKeyPair.Type("EC_SECP192R1");
        public final static VirgilKeyPair.Type EC_SECP224R1 = new VirgilKeyPair.Type("EC_SECP224R1");
        public final static VirgilKeyPair.Type EC_SECP256R1 = new VirgilKeyPair.Type("EC_SECP256R1");
        public final static VirgilKeyPair.Type EC_SECP384R1 = new VirgilKeyPair.Type("EC_SECP384R1");
        public final static VirgilKeyPair.Type EC_SECP521R1 = new VirgilKeyPair.Type("EC_SECP521R1");
        public final static VirgilKeyPair.Type EC_BP256R1 = new VirgilKeyPair.Type("EC_BP256R1");
        public final static VirgilKeyPair.Type EC_BP384R1 = new VirgilKeyPair.Type("EC_BP384R1");
        public final static VirgilKeyPair.Type EC_BP512R1 = new VirgilKeyPair.Type("EC_BP512R1");
        public final static VirgilKeyPair.Type EC_SECP192K1 = new VirgilKeyPair.Type("EC_SECP192K1");
        public final static VirgilKeyPair.Type EC_SECP224K1 = new VirgilKeyPair.Type("EC_SECP224K1");
        public final static VirgilKeyPair.Type EC_SECP256K1 = new VirgilKeyPair.Type("EC_SECP256K1");
        public final static VirgilKeyPair.Type EC_CURVE25519 = new VirgilKeyPair.Type("EC_CURVE25519");
        public final static VirgilKeyPair.Type FAST_EC_X25519 = new VirgilKeyPair.Type("FAST_EC_X25519");
        public final static VirgilKeyPair.Type FAST_EC_ED25519 = new VirgilKeyPair.Type("FAST_EC_ED25519");

        private static Type[] swigValues = { RSA_256, RSA_512, RSA_1024, RSA_2048, RSA_3072, RSA_4096, RSA_8192,
                EC_SECP192R1, EC_SECP224R1, EC_SECP256R1, EC_SECP384R1, EC_SECP521R1, EC_BP256R1, EC_BP384R1,
                EC_BP512R1, EC_SECP192K1, EC_SECP224K1, EC_SECP256K1, EC_CURVE25519, FAST_EC_X25519, FAST_EC_ED25519 };

        private static int swigNext = 0;

        public static Type swigToEnum(int swigValue) {
            if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
                return swigValues[swigValue];
            for (int i = 0; i < swigValues.length; i++)
                if (swigValues[i].swigValue == swigValue)
                    return swigValues[i];
            throw new IllegalArgumentException("No enum " + Type.class + " with value " + swigValue);
        }

        private final int swigValue;

        private final String swigName;

        private Type(String swigName) {
            this.swigName = swigName;
            this.swigValue = swigNext++;
        }

        private Type(String swigName, int swigValue) {
            this.swigName = swigName;
            this.swigValue = swigValue;
            swigNext = swigValue + 1;
        }

        private Type(String swigName, Type swigEnum) {
            this.swigName = swigName;
            this.swigValue = swigEnum.swigValue;
            swigNext = this.swigValue + 1;
        }

        public final int swigValue() {
            return swigValue;
        }

        public String toString() {
            return swigName;
        }
    }

    public static boolean checkPrivateKeyPassword(byte[] key, byte[] pwd) {
        return virgil_crypto_javaJNI.VirgilKeyPair_checkPrivateKeyPassword(key, pwd);
    }

    public static byte[] decryptPrivateKey(byte[] privateKey, byte[] privateKeyPassword) {
        return virgil_crypto_javaJNI.VirgilKeyPair_decryptPrivateKey(privateKey, privateKeyPassword);
    }

    public static byte[] encryptPrivateKey(byte[] privateKey, byte[] privateKeyPassword) {
        return virgil_crypto_javaJNI.VirgilKeyPair_encryptPrivateKey(privateKey, privateKeyPassword);
    }

    public static byte[] extractPublicKey(byte[] privateKey, byte[] privateKeyPassword) {
        return virgil_crypto_javaJNI.VirgilKeyPair_extractPublicKey(privateKey, privateKeyPassword);
    }

    public static VirgilKeyPair generate(VirgilKeyPair.Type type) {
        return new VirgilKeyPair(virgil_crypto_javaJNI.VirgilKeyPair_generate__SWIG_1(type.swigValue()), true);
    }

    public static VirgilKeyPair generate(VirgilKeyPair.Type type, byte[] pwd) {
        return new VirgilKeyPair(virgil_crypto_javaJNI.VirgilKeyPair_generate__SWIG_0(type.swigValue(), pwd), true);
    }

    public static VirgilKeyPair generateFrom(VirgilKeyPair donorKeyPair) {
        return new VirgilKeyPair(virgil_crypto_javaJNI
                .VirgilKeyPair_generateFrom__SWIG_2(VirgilKeyPair.getCPtr(donorKeyPair), donorKeyPair), true);
    }

    public static VirgilKeyPair generateFrom(VirgilKeyPair donorKeyPair, byte[] donorPrivateKeyPassword) {
        return new VirgilKeyPair(virgil_crypto_javaJNI.VirgilKeyPair_generateFrom__SWIG_1(
                VirgilKeyPair.getCPtr(donorKeyPair), donorKeyPair, donorPrivateKeyPassword), true);
    }

    public static VirgilKeyPair generateFrom(VirgilKeyPair donorKeyPair, byte[] donorPrivateKeyPassword,
            byte[] newKeyPairPassword) {
        return new VirgilKeyPair(virgil_crypto_javaJNI.VirgilKeyPair_generateFrom__SWIG_0(
                VirgilKeyPair.getCPtr(donorKeyPair), donorKeyPair, donorPrivateKeyPassword, newKeyPairPassword), true);
    }

    public static VirgilKeyPair generateRecommended() {
        return new VirgilKeyPair(virgil_crypto_javaJNI.VirgilKeyPair_generateRecommended__SWIG_1(), true);
    }

    public static VirgilKeyPair generateRecommended(byte[] pwd) {
        return new VirgilKeyPair(virgil_crypto_javaJNI.VirgilKeyPair_generateRecommended__SWIG_0(pwd), true);
    }

    public static boolean isKeyPairMatch(byte[] publicKey, byte[] privateKey) {
        return virgil_crypto_javaJNI.VirgilKeyPair_isKeyPairMatch__SWIG_1(publicKey, privateKey);
    }

    public static boolean isKeyPairMatch(byte[] publicKey, byte[] privateKey, byte[] privateKeyPassword) {
        return virgil_crypto_javaJNI.VirgilKeyPair_isKeyPairMatch__SWIG_0(publicKey, privateKey, privateKeyPassword);
    }

    public static boolean isPrivateKeyEncrypted(byte[] privateKey) {
        return virgil_crypto_javaJNI.VirgilKeyPair_isPrivateKeyEncrypted(privateKey);
    }

    public static byte[] privateKeyToDER(byte[] privateKey) {
        return virgil_crypto_javaJNI.VirgilKeyPair_privateKeyToDER__SWIG_1(privateKey);
    }

    public static byte[] privateKeyToDER(byte[] privateKey, byte[] privateKeyPassword) {
        return virgil_crypto_javaJNI.VirgilKeyPair_privateKeyToDER__SWIG_0(privateKey, privateKeyPassword);
    }

    public static byte[] privateKeyToPEM(byte[] privateKey) {
        return virgil_crypto_javaJNI.VirgilKeyPair_privateKeyToPEM__SWIG_1(privateKey);
    }

    public static byte[] privateKeyToPEM(byte[] privateKey, byte[] privateKeyPassword) {
        return virgil_crypto_javaJNI.VirgilKeyPair_privateKeyToPEM__SWIG_0(privateKey, privateKeyPassword);
    }

    public static byte[] publicKeyToDER(byte[] publicKey) {
        return virgil_crypto_javaJNI.VirgilKeyPair_publicKeyToDER(publicKey);
    }

    public static byte[] publicKeyToPEM(byte[] publicKey) {
        return virgil_crypto_javaJNI.VirgilKeyPair_publicKeyToPEM(publicKey);
    }

    public static byte[] resetPrivateKeyPassword(byte[] privateKey, byte[] oldPassword, byte[] newPassword) {
        return virgil_crypto_javaJNI.VirgilKeyPair_resetPrivateKeyPassword(privateKey, oldPassword, newPassword);
    }

    protected static long getCPtr(VirgilKeyPair obj) {
        return (obj == null) ? 0 : obj.swigCPtr;
    }

    private transient long swigCPtr;

    protected transient boolean swigCMemOwn;

    public VirgilKeyPair(byte[] publicKey, byte[] privateKey) {
        this(virgil_crypto_javaJNI.new_VirgilKeyPair__SWIG_0(publicKey, privateKey), true);
    }

    public VirgilKeyPair(VirgilKeyPair other) {
        this(virgil_crypto_javaJNI.new_VirgilKeyPair__SWIG_1(VirgilKeyPair.getCPtr(other), other), true);
    }

    protected VirgilKeyPair(long cPtr, boolean cMemoryOwn) {
        swigCMemOwn = cMemoryOwn;
        swigCPtr = cPtr;
    }

    @Override
    public void close() {
        delete();
    }

    public synchronized void delete() {
        if (swigCPtr != 0) {
            if (swigCMemOwn) {
                swigCMemOwn = false;
                virgil_crypto_javaJNI.delete_VirgilKeyPair(swigCPtr);
            }
            swigCPtr = 0;
        }
    }

    public byte[] privateKey() {
        return virgil_crypto_javaJNI.VirgilKeyPair_privateKey(swigCPtr, this);
    }

    public byte[] publicKey() {
        return virgil_crypto_javaJNI.VirgilKeyPair_publicKey(swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

}
