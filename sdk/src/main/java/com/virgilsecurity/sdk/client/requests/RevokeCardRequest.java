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
package com.virgilsecurity.sdk.client.requests;

import com.virgilsecurity.sdk.client.model.cards.RevocationReason;
import com.virgilsecurity.sdk.client.model.cards.RevokeCardSnapshotModel;

/**
 * Request used for Virgil Card revocation.
 *
 * @author Andrii Iakovenko
 *
 */
public class RevokeCardRequest extends SignedRequest {

    private String cardId;
    private RevocationReason reason;

    /**
     * Create new instance of {@link RevokeCardRequest}.
     * 
     */
    public RevokeCardRequest() {
        this.reason = RevocationReason.UNSPECIFIED;
    }

    /**
     * @return the cardId
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * @param cardId
     *            the cardId to set
     */
    public void setCardId(String cardId) {
        checkNoSnapshot();
        this.cardId = cardId;
    }

    /**
     * @return the reason
     */
    public RevocationReason getReason() {
        return reason;
    }

    /**
     * @param reason
     *            the reason to set
     */
    public void setReason(RevocationReason reason) {
        checkNoSnapshot();
        this.reason = reason;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.virgilsecurity.sdk.client.requests.SignedRequest#createSnapshot()
     */
    @Override
    protected byte[] createSnapshot() {
        RevokeCardSnapshotModel model = new RevokeCardSnapshotModel();
        model.setCardId(this.cardId);
        model.setReason(this.reason);

        return SnapshotUtils.takeSnapshot(model);
    }

}
