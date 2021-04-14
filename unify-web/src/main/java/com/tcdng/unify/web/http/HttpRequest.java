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

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * HTTP request.
 * 
 * @author Lateef Ojulari
 * @since 1.0
 */
public interface HttpRequest {

    String getContentType();

    String getPathInfo();

    String getCharacterEncoding();

    String getRemoteAddr();

    String getRemoteHost();

    String getRemoteUser();

    String getServletPath();
    
    String getScheme();

    String getServerName();

    int getServerPort();

    String getHeader(String headerName);

    String getParameter(String paramName);

    HttpUserSession createHttpUserSession(Locale locale, TimeZone timeZone, String sessionId, String uriBase,
            String contextPath, String tenantPath, String remoteIpAddress);

    InputStream getInputStream() throws IOException;

    Map<String, String[]> getParameterMap();

    Collection<HttpPart> getParts() throws Exception;

    void invalidateSession();

    void setSessionAttribute(String name, Object val);

    Object getSessionAttribute(String name);

    void removeSessionAttribute(String name);

    Object getSessionSychObject();
}
