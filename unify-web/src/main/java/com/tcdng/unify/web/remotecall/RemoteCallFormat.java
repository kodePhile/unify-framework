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
package com.tcdng.unify.web.remotecall;

import com.tcdng.unify.core.constant.MimeType;
import com.tcdng.unify.core.constant.EnumConst;
import com.tcdng.unify.core.util.EnumUtils;

/**
 * Supported remote call messaging formats.
 * 
 * @author Lateef Ojulari
 * @since 1.0
 */
public enum RemoteCallFormat implements EnumConst {

    JSON("JSON", MimeType.APPLICATION_JSON),
    XML("XML", MimeType.APPLICATION_XML),
    TAGGED_BINARYMESSAGE("TAGGED_BINARYMESSAGE", MimeType.APPLICATION_OCTETSTREAM),
    TAGGED_XMLMESSAGE("TAGGED_XMLMESSAGE", MimeType.APPLICATION_XML);

    private String code;

    private MimeType mimeType;

    private RemoteCallFormat(String code, MimeType mimeType) {
        this.code = code;
        this.mimeType = mimeType;
    }

    public MimeType mimeType() {
        return mimeType;
    }

    @Override
    public String code() {
        return this.code;
    }

    public boolean stringFormat() {
        return JSON.equals(this) || XML.equals(this);
    }

    public static RemoteCallFormat fromCode(String code) {
        return EnumUtils.fromCode(RemoteCallFormat.class, code);
    }

    public static RemoteCallFormat fromName(String name) {
        return EnumUtils.fromName(RemoteCallFormat.class, name);
    }

    public static RemoteCallFormat fromContentType(String contentType) {
        if (contentType != null) {
            if (contentType.startsWith(JSON.mimeType.template())) {
                return JSON;
            }

            if (contentType.startsWith(XML.mimeType.template())) {
                return XML;
            }

            if (contentType.equals(TAGGED_BINARYMESSAGE.mimeType.template())) {
                return TAGGED_BINARYMESSAGE;
            }
        }

        return null;
    }
}