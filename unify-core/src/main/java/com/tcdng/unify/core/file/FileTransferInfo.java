/*
 * Copyright 2018-2019 The Code Department.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.tcdng.unify.core.file;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.tcdng.unify.core.UnifyException;
import com.tcdng.unify.core.util.DataUtils;

/**
 * File transfer information data object.
 * 
 * @author Lateef Ojulari
 * @since 1.0
 */
public class FileTransferInfo {

    private String remoteHost;

    private String authenticationId;

    private String authenticationPassword;

    private String remotePath;

    private String localPath;

    private Set<String> filePrefixes;

    private Set<String> fileSuffixes;

    private int remotePort;

    private boolean deleteSourceOnTransfer;

    private FileTransferInfo(String remoteHost, int remotePort, String authenticationId, String authenticationPassword,
            String remotePath, String localPath, Set<String> filePrefixes, Set<String> fileSuffixes,
            boolean deleteSourceOnTransfer) {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
        this.authenticationId = authenticationId;
        this.authenticationPassword = authenticationPassword;
        this.remotePath = remotePath;
        this.localPath = localPath;
        this.deleteSourceOnTransfer = deleteSourceOnTransfer;
        this.filePrefixes = filePrefixes;
        this.fileSuffixes = fileSuffixes;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public String getAuthenticationId() {
        return authenticationId;
    }

    public String getAuthenticationPassword() {
        return authenticationPassword;
    }

    public String getRemotePath() {
        return remotePath;
    }

    public String getLocalPath() {
        return localPath;
    }

    public Set<String> getFilePrefixes() {
        return filePrefixes;
    }

    public Set<String> getFileSuffixes() {
        return fileSuffixes;
    }

    public boolean isDeleteSourceOnTransfer() {
        return deleteSourceOnTransfer;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {

        private String remoteHost;

        private String authenticationId;

        private String authenticationPassword;

        private String remotePath;

        private String localPath;

        private Set<String> filePrefixes;

        private Set<String> fileSuffixes;

        private int remotePort;

        private boolean deleteSourceOnTransfer;

        private Builder() {

        }

        public Builder remoteHost(String remoteHost) {
            this.remoteHost = remoteHost;
            return this;
        }

        public Builder remotePath(String remotePath) {
            this.remotePath = remotePath;
            return this;
        }

        public Builder remotePort(int remotePort) {
            this.remotePort = remotePort;
            return this;
        }

        public Builder localPath(String localPath) {
            this.localPath = localPath;
            return this;
        }

        public Builder useAuthenticationId(String authenticationId) {
            this.authenticationId = authenticationId;
            return this;
        }

        public Builder useAuthenticationPassword(String authenticationPassword) {
            this.authenticationPassword = authenticationPassword;
            return this;
        }

        public Builder deleteSourceOnTransfer(boolean deleteSourceOnTransfer) {
            this.deleteSourceOnTransfer = deleteSourceOnTransfer;
            return this;
        }

        public Builder filterByPrefix(String prefix) {
            if (filePrefixes == null) {
                filePrefixes = new HashSet<String>();
            }

            filePrefixes.add(prefix);
            return this;
        }

        public Builder filterByPrefixes(Collection<String> prefixes) {
            if (!DataUtils.isBlank(prefixes)) {
                if (filePrefixes == null) {
                    filePrefixes = new HashSet<String>();
                }

                filePrefixes.addAll(prefixes);
            }

            return this;
        }

        public Builder filterByExtension(String extension) {
            if (fileSuffixes == null) {
                fileSuffixes = new HashSet<String>();
            }

            fileSuffixes.add(extension);
            return this;
        }

        public Builder filterByExtensions(Collection<String> extensions) {
            if (!DataUtils.isBlank(extensions)) {
                if (fileSuffixes == null) {
                    fileSuffixes = new HashSet<String>();
                }

                fileSuffixes.addAll(extensions);
            }

            return this;
        }

        public FileTransferInfo build() throws UnifyException {
            if (filePrefixes == null) {
                filePrefixes = Collections.emptySet();
            } else {
                filePrefixes = Collections.unmodifiableSet(filePrefixes);
            }

            if (fileSuffixes == null) {
                fileSuffixes = Collections.emptySet();
            } else {
                fileSuffixes = Collections.unmodifiableSet(fileSuffixes);
            }

            return new FileTransferInfo(remoteHost, remotePort, authenticationId, authenticationPassword, remotePath,
                    localPath, filePrefixes, fileSuffixes, deleteSourceOnTransfer);
        }
    }
}
