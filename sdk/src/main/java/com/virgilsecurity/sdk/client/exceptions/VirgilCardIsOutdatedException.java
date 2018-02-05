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

package com.virgilsecurity.sdk.client.exceptions;

import com.virgilsecurity.sdk.cards.model.RawSignedModel;

/**
 * This exception occurred when returned Virgil Card is outdated.
 */
public class VirgilCardIsOutdatedException extends RuntimeException {

    private static final long serialVersionUID = 3841365581319657840L;
    private RawSignedModel cardModel;

    /**
     * Instantiates a new Virgil card is outdated exception.
     */
    public VirgilCardIsOutdatedException() {

    }

    /**
     * Instantiates a new Virgil card is outdated exception.
     *
     * @param cardModel the card model of outdated Card
     */
    public VirgilCardIsOutdatedException(RawSignedModel cardModel) {
        this.cardModel = cardModel;
    }

    /**
     * Instantiates a new Virgil card is outdated exception.
     *
     * @param cause the cause
     */
    public VirgilCardIsOutdatedException(Exception cause) {
        super(cause);
    }

    /**
     * Gets card model.
     *
     * @return the card model
     */
    public RawSignedModel getCardModel() {
        return cardModel;
    }
}
