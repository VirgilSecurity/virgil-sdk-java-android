/*
 * Copyright (c) 2017, Virgil Security, Inc.
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
package com.virgilsecurity.sdk.client;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class contains common configuration parameters of {@link CardsClient}.
 * 
 * @author Andrii Iakovenko
 *
 */
public class CardsClientContext extends VirgilClientContext {

    private URL cardsServiceURL;

    private URL readOnlyCardsServiceURL;

    private URL raServiceURL;

    /**
     * Create new instance of {@link VirgilClientContext}.
     */
    public CardsClientContext() {
        init();
    }

    /**
     * Create a new instance of {@code VirgilClientContext}
     *
     * @param accessToken
     *            The application access token.
     */
    public CardsClientContext(String accessToken) {
        super(accessToken);
        init();
    }

    /**
     * @return the readOnlyCardsServiceURL
     */
    public URL getReadOnlyCardsServiceURL() {
        return readOnlyCardsServiceURL;
    }

    /**
     * @param readOnlyCardsServiceURL
     *            the readOnlyCardsServiceURL to set
     */
    public void setReadOnlyCardsServiceURL(URL readOnlyCardsServiceURL) {
        this.readOnlyCardsServiceURL = readOnlyCardsServiceURL;
    }

    /**
     * @return the cardsServiceURL
     */
    public URL getCardsServiceURL() {
        return cardsServiceURL;
    }

    /**
     * @param cardsServiceURL
     *            the cardsServiceURL to set
     */
    public void setCardsServiceURL(URL cardsServiceURL) {
        this.cardsServiceURL = cardsServiceURL;
    }

    /**
     * @return the raServiceURL
     */
    public URL getRaServiceURL() {
        return raServiceURL;
    }

    /**
     * @param raServiceURL
     *            the raServiceURL to set
     */
    public void setRaServiceURL(URL raServiceURL) {
        this.raServiceURL = raServiceURL;
    }

    private void init() {
        try {
            this.cardsServiceURL = new URL("https://cards.virgilsecurity.com");
            this.readOnlyCardsServiceURL = new URL("https://cards-ro.virgilsecurity.com");
            this.raServiceURL = new URL("https://ra.virgilsecurity.com");
        } catch (MalformedURLException e) {
            // This should never happen
        }
    }

}
