/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.virgilsecurity.crypto;

public class VirgilPBKDF extends VirgilAsn1Compatible implements java.lang.AutoCloseable {
	private long swigCPtr;

	protected VirgilPBKDF(long cPtr, boolean cMemoryOwn) {
		super(virgil_crypto_javaJNI.VirgilPBKDF_SWIGUpcast(cPtr), cMemoryOwn);
		swigCPtr = cPtr;
	}

	protected static long getCPtr(VirgilPBKDF obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				virgil_crypto_javaJNI.delete_VirgilPBKDF(swigCPtr);
			}
			swigCPtr = 0;
		}
		super.delete();
	}

	@Override
	public void close() {
		delete();
	}

	public VirgilPBKDF() {
		this(virgil_crypto_javaJNI.new_VirgilPBKDF__SWIG_0(), true);
	}

	public VirgilPBKDF(byte[] salt, long iterationCount) {
		this(virgil_crypto_javaJNI.new_VirgilPBKDF__SWIG_1(salt, iterationCount), true);
	}

	public VirgilPBKDF(byte[] salt) {
		this(virgil_crypto_javaJNI.new_VirgilPBKDF__SWIG_2(salt), true);
	}

	public byte[] getSalt() {
		return virgil_crypto_javaJNI.VirgilPBKDF_getSalt(swigCPtr, this);
	}

	public long getIterationCount() {
		return virgil_crypto_javaJNI.VirgilPBKDF_getIterationCount(swigCPtr, this);
	}

	public void setAlgorithm(VirgilPBKDF.Algorithm alg) {
		virgil_crypto_javaJNI.VirgilPBKDF_setAlgorithm(swigCPtr, this, alg.swigValue());
	}

	public VirgilPBKDF.Algorithm getAlgorithm() {
		return VirgilPBKDF.Algorithm.swigToEnum(virgil_crypto_javaJNI.VirgilPBKDF_getAlgorithm(swigCPtr, this));
	}

	public void setHash(VirgilPBKDF.Hash hash) {
		virgil_crypto_javaJNI.VirgilPBKDF_setHash(swigCPtr, this, hash.swigValue());
	}

	public VirgilPBKDF.Hash getHash() {
		return VirgilPBKDF.Hash.swigToEnum(virgil_crypto_javaJNI.VirgilPBKDF_getHash(swigCPtr, this));
	}

	public void enableRecommendationsCheck() {
		virgil_crypto_javaJNI.VirgilPBKDF_enableRecommendationsCheck(swigCPtr, this);
	}

	public void disableRecommendationsCheck() {
		virgil_crypto_javaJNI.VirgilPBKDF_disableRecommendationsCheck(swigCPtr, this);
	}

	public byte[] derive(byte[] pwd, long outSize) {
		return virgil_crypto_javaJNI.VirgilPBKDF_derive__SWIG_0(swigCPtr, this, pwd, outSize);
	}

	public byte[] derive(byte[] pwd) {
		return virgil_crypto_javaJNI.VirgilPBKDF_derive__SWIG_1(swigCPtr, this, pwd);
	}

	public final static long kIterationCount_Default = virgil_crypto_javaJNI.VirgilPBKDF_kIterationCount_Default_get();

	public final static class Algorithm {
		public final static VirgilPBKDF.Algorithm None = new VirgilPBKDF.Algorithm("None",
				virgil_crypto_javaJNI.VirgilPBKDF_None_get());
		public final static VirgilPBKDF.Algorithm PBKDF2 = new VirgilPBKDF.Algorithm("PBKDF2");

		public final int swigValue() {
			return swigValue;
		}

		public String toString() {
			return swigName;
		}

		public static Algorithm swigToEnum(int swigValue) {
			if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
				return swigValues[swigValue];
			for (int i = 0; i < swigValues.length; i++)
				if (swigValues[i].swigValue == swigValue)
					return swigValues[i];
			throw new IllegalArgumentException("No enum " + Algorithm.class + " with value " + swigValue);
		}

		private Algorithm(String swigName) {
			this.swigName = swigName;
			this.swigValue = swigNext++;
		}

		private Algorithm(String swigName, int swigValue) {
			this.swigName = swigName;
			this.swigValue = swigValue;
			swigNext = swigValue + 1;
		}

		private Algorithm(String swigName, Algorithm swigEnum) {
			this.swigName = swigName;
			this.swigValue = swigEnum.swigValue;
			swigNext = this.swigValue + 1;
		}

		private static Algorithm[] swigValues = { None, PBKDF2 };
		private static int swigNext = 0;
		private final int swigValue;
		private final String swigName;
	}

	public final static class Hash {
		public final static VirgilPBKDF.Hash SHA1 = new VirgilPBKDF.Hash("SHA1",
				virgil_crypto_javaJNI.VirgilPBKDF_SHA1_get());
		public final static VirgilPBKDF.Hash SHA224 = new VirgilPBKDF.Hash("SHA224");
		public final static VirgilPBKDF.Hash SHA256 = new VirgilPBKDF.Hash("SHA256");
		public final static VirgilPBKDF.Hash SHA384 = new VirgilPBKDF.Hash("SHA384");
		public final static VirgilPBKDF.Hash SHA512 = new VirgilPBKDF.Hash("SHA512");

		public final int swigValue() {
			return swigValue;
		}

		public String toString() {
			return swigName;
		}

		public static Hash swigToEnum(int swigValue) {
			if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
				return swigValues[swigValue];
			for (int i = 0; i < swigValues.length; i++)
				if (swigValues[i].swigValue == swigValue)
					return swigValues[i];
			throw new IllegalArgumentException("No enum " + Hash.class + " with value " + swigValue);
		}

		private Hash(String swigName) {
			this.swigName = swigName;
			this.swigValue = swigNext++;
		}

		private Hash(String swigName, int swigValue) {
			this.swigName = swigName;
			this.swigValue = swigValue;
			swigNext = swigValue + 1;
		}

		private Hash(String swigName, Hash swigEnum) {
			this.swigName = swigName;
			this.swigValue = swigEnum.swigValue;
			swigNext = this.swigValue + 1;
		}

		private static Hash[] swigValues = { SHA1, SHA224, SHA256, SHA384, SHA512 };
		private static int swigNext = 0;
		private final int swigValue;
		private final String swigName;
	}

}