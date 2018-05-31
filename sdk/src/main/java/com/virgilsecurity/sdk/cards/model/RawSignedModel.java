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

package com.virgilsecurity.sdk.cards.model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gson.annotations.SerializedName;
import com.virgilsecurity.sdk.client.exceptions.SignatureNotUniqueException;
import com.virgilsecurity.sdk.utils.ConvertionUtils;
import com.virgilsecurity.sdk.utils.Validator;

/**
 * The Raw signed model.
 */
public class RawSignedModel {
    private static final Logger LOGGER = Logger.getLogger(RawSignedModel.class.getName());

    @SerializedName("content_snapshot")
    private byte[] contentSnapshot;

    @SerializedName("signatures")
    private List<RawSignature> signatures;

    /**
     * Instantiates a new Raw signed model.
     *
     * @param contentSnapshot
     *            the content snapshot
     */
    public RawSignedModel(byte[] contentSnapshot) {
        Validator.checkNullEmptyAgrument(contentSnapshot,
                "RawSignedModel -> 'contentSnapshot' should not be null or empty");

        this.contentSnapshot = contentSnapshot;

        signatures = new ArrayList<>();
    }

    /**
     * Create new instance of {@link RawSignedModel}.
     * 
     * @param base64EncodedString
     *            the Base64-encoded card content snapshot.
     */
    public RawSignedModel(String base64EncodedString) {
        this(ConvertionUtils.base64ToBytes(base64EncodedString));
    }

    /**
     * Instantiates a new Raw signed model.
     *
     * @param contentSnapshot
     *            the content snapshot
     * @param signatures
     *            the list of signatures
     */
    public RawSignedModel(byte[] contentSnapshot, List<RawSignature> signatures) {
        Validator.checkNullEmptyAgrument(contentSnapshot,
                "RawSignedModel -> 'contentSnapshot' should not be null or empty");
        Validator.checkNullEmptyAgrument(signatures, "RawSignedModel -> 'signatures' should not be null or empty");

        if (!isAllSignaturesUnique(signatures))
            throw new SignatureNotUniqueException("RawSignedModel -> 'signatures' should have unique signatures");

        this.contentSnapshot = contentSnapshot;
        this.signatures = signatures;
    }

    /**
     * Get content snapshot.
     *
     * @return the byte [ ]
     */
    public byte[] getContentSnapshot() {
        return contentSnapshot;
    }

    /**
     * Sets content snapshot.
     *
     * @param contentSnapshot
     *            the content snapshot
     */
    public void setContentSnapshot(byte[] contentSnapshot) {
        this.contentSnapshot = contentSnapshot;
    }

    /**
     * Gets list of signatures.
     *
     * @return the signatures
     */
    public List<RawSignature> getSignatures() {
        return signatures;
    }

    /**
     * Sets list of signatures.
     *
     * @param signatures
     *            the list of signatures
     */
    public void setSignatures(List<RawSignature> signatures) {
        if (signatures.size() > 8) {
            LOGGER.warning(
                    "RawSignedModel can hold up to 8 signatures only. While 'signatures' size is " + signatures.size());
            throw new IllegalArgumentException("RawSignedModel -> 'signatures' can hold up to 8 signatures only"); // TODO:
                                                                                                                   // 2/13/18
                                                                                                                   // add
                                                                                                                   // size
                                                                                                                   // test
        }

        if (!isAllSignaturesUnique(signatures))
            throw new SignatureNotUniqueException("RawSignedModel -> 'signatures' should have unique signatures");

        this.signatures = signatures;
    }

    /**
     * Add signature. The signature that is about to add must be unique (by signer). Max number of signatures is up to
     * 8.
     *
     * @param rawSignature
     *            the raw signature
     */
    public void addSignature(RawSignature rawSignature) {
        if (signatures.size() > 7) {
            LOGGER.warning("RawSignedModel can hold up to 8 signatures only and is full already");
            throw new IllegalArgumentException("RawSignedModel -> 'signatures' can hold up to 8 signatures only");
        }

        if (!isSignaturesUnique(rawSignature))
            throw new SignatureNotUniqueException("RawSignedModel -> 'signatures' should have unique signatures");

        signatures.add(rawSignature);
    }

    private boolean isAllSignaturesUnique(List<RawSignature> signatures) {
        for (RawSignature rawSignatureOuter : signatures) {
            for (RawSignature rawSignatureInner : signatures) {
                if (rawSignatureOuter.getSigner().equals(rawSignatureInner.getSigner())) {
                    LOGGER.warning(String.format(
                            "RawSignedModel should have unique signatures only. The '%s' signature is already present",
                            rawSignatureOuter.getSigner()));
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isSignaturesUnique(RawSignature signature) {
        for (RawSignature rawSignatureOuter : signatures) {
            if (rawSignatureOuter.getSigner().equals(signature.getSigner())) {
                LOGGER.warning(String.format(
                        "RawSignedModel should have unique signatures only. The '%s' signature is already present",
                        signature.getSigner()));
                return false;
            }
        }

        return true;
    }

    /**
     * Export as base64 string.
     *
     * @return the string
     */
    public String exportAsBase64String() {
        return ConvertionUtils.toBase64String(ConvertionUtils.serializeToJson(this));
    }

    /**
     * Export as json in string format.
     *
     * @return the string
     */
    public String exportAsJson() {
        return ConvertionUtils.serializeToJson(this);
    }

    /**
     * Instantiate {@link RawSignedModel} from provided base64 string.
     *
     * @param cardModel
     *            the card model
     * @return the raw signed model
     */
    public static RawSignedModel fromString(String cardModel) {
        return fromJson(ConvertionUtils.base64ToString(cardModel));
    }

    /**
     * Instantiate {@link RawSignedModel} from provided string.
     *
     * @param cardModel
     *            the card model
     * @return the raw signed model
     */
    public static RawSignedModel fromJson(String cardModel) {
        return ConvertionUtils.deserializeFromJson(cardModel, RawSignedModel.class);
    }
}