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

package com.virgilsecurity.sdk.jwt;

import java.util.Arrays;

import com.virgilsecurity.sdk.jwt.contract.AccessToken;
import com.virgilsecurity.sdk.utils.ConvertionUtils;
import com.virgilsecurity.sdk.utils.Validator;

/**
 * The {@link Jwt} class implements {@link AccessToken} interface and is used to
 * get access for network requests.
 */
public class Jwt implements AccessToken {

    private JwtHeaderContent headerContent;
    private JwtBodyContent bodyContent;
    private byte[] signatureData;
    private String stringRepresentation;

    /**
     * Instantiates a new Jwt.
     *
     * @param headerContent the header content
     * @param bodyContent   the body content
     */
    public Jwt(JwtHeaderContent headerContent, JwtBodyContent bodyContent) {
        Validator.checkNullAgrument(headerContent, "Jwt -> 'headerContent' should not be null");
        Validator.checkNullAgrument(bodyContent, "Jwt -> 'bodyContent' should not be null");

        this.headerContent = headerContent;
        this.bodyContent = bodyContent;

        this.stringRepresentation = new StringBuilder().append(headerBase64url()).append(".").append(bodyBase64url())
                .append(".").toString();
    }

    /**
     * Instantiates a new Jwt.
     *
     * @param headerContent the header content
     * @param bodyContent   the body content
     * @param signatureData the signature data
     */
    public Jwt(JwtHeaderContent headerContent, JwtBodyContent bodyContent, byte[] signatureData) {
        Validator.checkNullAgrument(headerContent, "Jwt -> 'headerContent' should not be null");
        Validator.checkNullAgrument(bodyContent, "Jwt -> 'bodyContent' should not be null");
        Validator.checkNullEmptyAgrument(signatureData, "Jwt -> 'signatureData' should not be null or empty");

        this.headerContent = headerContent;
        this.bodyContent = bodyContent;
        this.signatureData = signatureData;

        this.stringRepresentation = new StringBuilder().append(headerBase64url()).append(".").append(bodyBase64url())
                .append(".").append(signatureBase64url()).toString();
    }

    /**
     * Instantiates a new Jwt.
     *
     * @param jwtToken the jwt token in string representation.
     *                 Should have at least two parts - header and body.
     *                 (ex. "***.***", where "***" is base64 encoded string)
     */
    public Jwt(String jwtToken) {
        String[] jwtParts = jwtToken.split("[.]");

        if (jwtParts.length < 2) {
            throw new IllegalArgumentException("Jwt -> 'jwtToken' has wrong format");
        }

        String headerJson = ConvertionUtils.base64urldecode(jwtParts[0]);
        headerContent = JwtParser.parseJwtHeaderContent(headerJson);

        String bodyJson = ConvertionUtils.base64urldecode(jwtParts[1]);
        bodyContent = JwtParser.parseJwtBodyContent(bodyJson);

        if (jwtParts.length == 3) {
            signatureData = ConvertionUtils.base64urldecodeToBytes(jwtParts[2]);
        }

        this.stringRepresentation = jwtToken;
    }

    /**
     * Gets header content.
     *
     * @return the header content
     */
    public JwtHeaderContent getHeaderContent() {
        return headerContent;
    }

    /**
     * Gets body content.
     *
     * @return the body content
     */
    public JwtBodyContent getBodyContent() {
        return bodyContent;
    }

    /**
     * Get signature data.
     *
     * @return the signature data in byte [ ]
     */
    public byte[] getSignatureData() {
        return signatureData;
    }

    /**
     * Sets signature data.
     *
     * @param signatureData the signature data
     */
    public void setSignatureData(byte[] signatureData) {
        this.signatureData = signatureData;

        this.stringRepresentation = new StringBuilder().append(headerBase64url()).append(".").append(bodyBase64url())
                .append(".").append(signatureBase64url()).toString();
    }

    @Override
    public String getIdentity() {
        return bodyContent.getIdentity();
    }

    /**
     * Whether the token is expired.
     *
     * @return if the token is already expired then - {@code true}, otherwise {@code false}
     */
    public boolean isExpired() {
        return bodyContent.getExpiresAt().isExpired();
    }

    private String headerBase64url() {
        return ConvertionUtils.base64urlencode(ConvertionUtils.captureSnapshot(headerContent));
    }

    private String bodyBase64url() {
        return ConvertionUtils.base64urlencode(ConvertionUtils.captureSnapshot(bodyContent));
    }

    private String signatureBase64url() {
        return ConvertionUtils.base64urlencode(signatureData);
    }

    /**
     * Snapshot without signatures.
     *
     * @return the byte [ ]
     */
    public byte[] snapshotWithoutSignatures() {
        return (headerBase64url() + "." + bodyBase64url()).getBytes();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return stringRepresentation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bodyContent == null) ? 0 : bodyContent.hashCode());
        result = prime * result + ((headerContent == null) ? 0 : headerContent.hashCode());
        result = prime * result + Arrays.hashCode(signatureData);
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Jwt other = (Jwt) obj;
        if (bodyContent == null) {
            if (other.bodyContent != null)
                return false;
        } else if (!bodyContent.equals(other.bodyContent))
            return false;
        if (headerContent == null) {
            if (other.headerContent != null)
                return false;
        } else if (!headerContent.equals(other.headerContent))
            return false;
        if (!Arrays.equals(signatureData, other.signatureData))
            return false;
        return true;
    }
}
