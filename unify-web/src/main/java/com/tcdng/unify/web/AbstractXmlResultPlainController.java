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

package com.tcdng.unify.web;

import com.tcdng.unify.core.UnifyException;
import com.tcdng.unify.core.annotation.Configurable;
import com.tcdng.unify.core.constant.ContentTypeConstants;
import com.tcdng.unify.core.stream.XMLObjectStreamer;

/**
 * Abstract XML result plain controller.
 * 
 * @author Lateef Ojulari
 * @since 1.0
 */
public abstract class AbstractXmlResultPlainController extends AbstractPlainController {

    @Configurable
    private XMLObjectStreamer xmlObjectStreamer;

    @Override
    public void execute(ClientRequest request, ClientResponse response) throws UnifyException {
        response.setContentType(ContentTypeConstants.APPLICATION_XML);
        xmlObjectStreamer.marshal(doExecute(request), response.getWriter());
    }

    protected abstract Object doExecute(ClientRequest request) throws UnifyException;
}