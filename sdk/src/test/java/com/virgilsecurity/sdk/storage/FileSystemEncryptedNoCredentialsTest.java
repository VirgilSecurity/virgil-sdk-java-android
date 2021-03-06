/*
 * Copyright (c) 2015-2020, Virgil Security, Inc.
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

package com.virgilsecurity.sdk.storage;

import com.virgilsecurity.common.exception.NullArgumentException;
import com.virgilsecurity.common.model.Data;
import com.virgilsecurity.sdk.crypto.VirgilCrypto;
import com.virgilsecurity.sdk.crypto.VirgilKeyPair;
import com.virgilsecurity.sdk.crypto.VirgilPrivateKey;
import com.virgilsecurity.sdk.crypto.exceptions.CryptoException;
import com.virgilsecurity.sdk.storage.exceptions.DirectoryNotExistsException;
import com.virgilsecurity.sdk.storage.exceptions.FileSystemException;
import com.virgilsecurity.sdk.storage.exceptions.NotAFileException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * FileSystemEncryptedNoCredentialsTest class.
 */
class FileSystemEncryptedNoCredentialsTest {

    private FileSystem fileSystemEncrypted;
    private File tmpDir;
    private String alias;
    private String subdirectory;

    private VirgilKeyPair keyPair;
    private Data data;

    @BeforeEach
    void setUp() throws CryptoException, FileSystemException {
        VirgilCrypto crypto = new VirgilCrypto();

        tmpDir = new File(System.getProperty("java.io.tmpdir") + File.separator + UUID.randomUUID().toString());
        fileSystemEncrypted = new FileSystemEncrypted(tmpDir.getAbsolutePath());

        keyPair = crypto.generateKeyPair();

        alias = UUID.randomUUID().toString();
        subdirectory = UUID.randomUUID().toString();
        byte[] privateKeyData = crypto.exportPrivateKey(keyPair.getPrivateKey());
        data = new Data(privateKeyData);
    }

    @Test
    void delete() throws Exception {
        fileSystemEncrypted.write(data, alias);
        fileSystemEncrypted.delete(alias);

        assertFalse(fileSystemEncrypted.exists(alias));
    }

    @Test
    void delete_subdirectory() throws Exception {
        fileSystemEncrypted.write(data, alias, subdirectory);
        fileSystemEncrypted.delete(alias, subdirectory);

        assertFalse(fileSystemEncrypted.exists(alias, subdirectory));
    }

    @Test
    void delete_nonExisting() {
        assertFalse(fileSystemEncrypted.delete(alias));
    }

    @Test
    void delete_nonExisting_subdirectory() throws NotAFileException {
        assertFalse(fileSystemEncrypted.delete(alias, subdirectory));
    }

    @Test
    void delete_nullName() {
        assertThrows(NullArgumentException.class, () -> {
            fileSystemEncrypted.delete(null);
        });
    }

    @Test
    void delete_nullName_subdirectory() {
        assertThrows(NullArgumentException.class, () -> {
            fileSystemEncrypted.delete(null, subdirectory);
        });
    }

    @Test
    void exists() throws Exception {
        if (!tmpDir.exists()) {
            boolean created = tmpDir.mkdirs();
            assertTrue(created);
        }
        fileSystemEncrypted.write(data, alias);

        assertTrue(fileSystemEncrypted.exists(alias));
    }

    @Test
    void exists_subdirectory() throws Exception {
        if (!tmpDir.exists()) {
            boolean created = tmpDir.mkdirs();
            assertTrue(created);
        }
        fileSystemEncrypted.write(data, alias, subdirectory);

        assertTrue(fileSystemEncrypted.exists(alias, subdirectory));
    }

    @Test
    void exists_nullAlias() {
        assertThrows(NullArgumentException.class, () -> {
            fileSystemEncrypted.exists(null);
        });
    }

    @Test
    void exists_nullAlias_subdirectory() {
        assertThrows(NullArgumentException.class, () -> {
            fileSystemEncrypted.exists(null, subdirectory);
        });
    }

    @Test
    void exists_randomName() throws Exception {
        assertFalse(fileSystemEncrypted.exists(UUID.randomUUID().toString()));
    }

    @Test
    void exists_randomName_subdirectory() throws Exception {
        assertFalse(fileSystemEncrypted.exists(UUID.randomUUID().toString(), subdirectory));
    }

    @Test
    void load() throws Exception {
        fileSystemEncrypted.write(data, alias);

        Data dataRead = fileSystemEncrypted.read(alias);
        assertEquals(data, dataRead);

        VirgilPrivateKey privateKeyImported = new VirgilCrypto().importPrivateKey(dataRead.getValue()).getPrivateKey();
        assertEquals(keyPair.getPrivateKey(), privateKeyImported);
    }

    @Test
    void load_subdirectory() throws Exception {
        fileSystemEncrypted.write(data, alias, subdirectory);

        Data dataRead = fileSystemEncrypted.read(alias, subdirectory);
        assertEquals(data, dataRead);

        VirgilPrivateKey privateKeyImported = new VirgilCrypto().importPrivateKey(dataRead.getValue()).getPrivateKey();
        assertEquals(keyPair.getPrivateKey(), privateKeyImported);
    }

    @Test
    void load_file_itself() throws Exception {
        fileSystemEncrypted.write(data, alias);

        File fileToRead = new File(tmpDir.getAbsolutePath(), alias);

        Data dataRead;
        try (FileInputStream is = new FileInputStream(fileToRead);
             ByteArrayOutputStream os = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[4096];
            int n;
            while (-1 != (n = is.read(buffer))) {
                os.write(buffer, 0, n);
            }

            byte[] data = os.toByteArray();

            dataRead = new Data(data);
        }

        // Should NOT fail because file is NOT encrypted with credentials
        VirgilPrivateKey privateKeyImported = new VirgilCrypto().importPrivateKey(dataRead.getValue()).getPrivateKey();
        assertEquals(keyPair.getPrivateKey(), privateKeyImported);
    }

    @Test
    void load_nonExisting() {
        assertThrows(FileNotFoundException.class, () -> {
            fileSystemEncrypted.read(alias);
        });
    }

    @Test
    void load_nonExisting_subdirectory() {
        assertThrows(FileNotFoundException.class, () -> {
            fileSystemEncrypted.read(alias, subdirectory);
        });
    }

    @Test
    void load_nullName() {
        assertThrows(NullArgumentException.class, () -> {
            fileSystemEncrypted.read(null);
        });
    }

    @Test
    void load_nullName_subdirectory() {
        assertThrows(NullArgumentException.class, () -> {
            fileSystemEncrypted.read(null, subdirectory);
        });
    }

    @Test
    void names() throws Exception {
        fileSystemEncrypted.write(data, alias);

        Set<String> names = fileSystemEncrypted.listFiles();
        assertNotNull(names);
        assertEquals(1, names.size());
        assertEquals(alias, names.iterator().next());
    }

    @Test
    void names_subdirectory() throws Exception {
        fileSystemEncrypted.write(data, alias, subdirectory);

        Set<String> names = fileSystemEncrypted.listFiles(subdirectory);
        assertNotNull(names);
        assertEquals(1, names.size());
        assertEquals(alias, names.iterator().next());
    }

    @Test
    void names_empty() {
        Set<String> names = fileSystemEncrypted.listFiles();
        assertNotNull(names);
        assertTrue(names.isEmpty());
    }

    @Test
    void names_empty_subdirectory_not_created() {
        assertThrows(DirectoryNotExistsException.class, () -> {
           fileSystemEncrypted.listFiles(subdirectory);
        });
    }

    @Test
    void names_empty_subdirectory_created() throws Exception {
        File fileWithSubdir = new File(tmpDir.getAbsolutePath() + File.separator + subdirectory);
        if (!fileWithSubdir.exists()) {
            boolean created = fileWithSubdir.mkdirs();
            assertTrue(created);
        }

        Set<String> names = fileSystemEncrypted.listFiles(subdirectory);
        assertNotNull(names);
        assertTrue(names.isEmpty());
    }

    @Test
    void store() throws Exception {
        fileSystemEncrypted.write(data, alias);
        assertTrue(fileSystemEncrypted.exists(alias));
    }

    @Test
    void store_subdirectory() throws Exception {
        fileSystemEncrypted.write(data, alias, subdirectory);
        assertTrue(fileSystemEncrypted.exists(alias, subdirectory));
    }

    @Test
    void store_duplicated() throws Exception {
        fileSystemEncrypted.write(data, alias);
        fileSystemEncrypted.write(data, alias);
        assertTrue(fileSystemEncrypted.exists(alias));
        assertEquals(1, fileSystemEncrypted.listFiles().size());
    }

    @Test
    void store_duplicated_subdirectory() throws Exception {
        fileSystemEncrypted.write(data, alias, subdirectory);
        fileSystemEncrypted.write(data, alias, subdirectory);
        assertTrue(fileSystemEncrypted.exists(alias, subdirectory));
        assertEquals(1, fileSystemEncrypted.listFiles(subdirectory).size());
    }
}