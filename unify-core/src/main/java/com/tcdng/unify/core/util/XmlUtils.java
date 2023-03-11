/*
 * Copyright 2018-2023 The Code Department.
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

package com.tcdng.unify.core.util;

import javax.xml.parsers.SAXParser;

import com.tcdng.unify.core.UnifyException;
import com.tcdng.unify.core.data.SAXParserPool;

/**
 * XML utilities.
 * 
 * @author The Code Department
 * @since 1.0
 */
public final class XmlUtils {

    private static SAXParserPool saxParserPool = new SAXParserPool();

    private XmlUtils() {

    }

    public static SAXParser borrowSAXParser() throws UnifyException {
        return getSAXParserPool().borrowObject();
    }

    public static boolean restoreSAXParser(SAXParser saxParser) {
        return getSAXParserPool().returnObject(saxParser);
    }

    private static SAXParserPool getSAXParserPool() {
        if (saxParserPool == null) {
            synchronized (XmlUtils.class) {
                if (saxParserPool == null) {
                    saxParserPool = new SAXParserPool();
                }
            }
        }

        return saxParserPool;
    }
}
