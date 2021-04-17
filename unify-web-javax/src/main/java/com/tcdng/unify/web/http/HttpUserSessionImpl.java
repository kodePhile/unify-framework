/*
 * Copyright 2018-2020 The Code Department.
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
package com.tcdng.unify.web.http;

import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * HTTP user session implementation.
 * 
 * @author Lateef Ojulari
 * @since 1.0
 */
public class HttpUserSessionImpl extends AbstractHttpUserSession implements HttpSessionBindingListener {

    public HttpUserSessionImpl(Locale locale, TimeZone timeZone, String sessionId, String uriBase, String contextPath,
            String tenantPath, String remoteHost, String remoteIpAddress, String remoteUser) {
        super(locale, timeZone, sessionId, uriBase, contextPath, tenantPath, remoteHost, remoteIpAddress, remoteUser);
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {

    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        invalidate();
    }
}
