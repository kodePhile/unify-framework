/*
 * Copyright 2018-2022 The Code Department.
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
package com.tcdng.unify.web.ui.response;

import com.tcdng.unify.core.UnifyException;
import com.tcdng.unify.core.annotation.Component;
import com.tcdng.unify.web.ui.AbstractJsonPageControllerResponse;
import com.tcdng.unify.web.ui.widget.ContentPanel;
import com.tcdng.unify.web.ui.widget.Page;
import com.tcdng.unify.web.ui.widget.ResponseWriter;

/**
 * Used for generating a load/refresh entire page content response.
 * 
 * @author The Code Department
 * @since 1.0
 */
@Component("loadcontentresponse")
public class LoadContentResponse extends AbstractJsonPageControllerResponse {

    public LoadContentResponse() {
        super("loadContentHdl", true);
    }

    @Override
    protected void doGenerate(ResponseWriter writer, Page page) throws UnifyException {
        logDebug("Preparing load content response: path ID = [{0}]", page.getPathId());
        ContentPanel contentPanel = getRequestContextUtil().getRequestDocument().getContentPanel();
        appendRefreshPageJSON(writer, contentPanel, page);
        writer.write(",");
        appendRefreshAttributesJson(writer, true);
        appendRegisteredDebounceWidgets(writer, true);
        writer.write(",\"scrollToTop\":true");
    }

    private void appendRefreshPageJSON(ResponseWriter writer, ContentPanel contentPanel, Page page)
            throws UnifyException {
        writer.write(",\"refreshPanels\":[");
        writer.writeJsonPanel(contentPanel, true);
        writer.write("]");
        if (getRequestContextUtil().isNoPushWidgets()) {
            writer.write(",\"noPushWidgets\":").writeJsonArray(getRequestContextUtil().getNoPushWidgetIds());
        }
    }
}
