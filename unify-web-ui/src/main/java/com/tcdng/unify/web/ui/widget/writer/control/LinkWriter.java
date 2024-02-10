/*
 * Copyright 2018-2024 The Code Department.
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
package com.tcdng.unify.web.ui.widget.writer.control;

import com.tcdng.unify.core.UnifyException;
import com.tcdng.unify.core.annotation.Component;
import com.tcdng.unify.core.annotation.Writes;
import com.tcdng.unify.core.util.StringUtils;
import com.tcdng.unify.web.ui.widget.ResponseWriter;
import com.tcdng.unify.web.ui.widget.TargetControl;
import com.tcdng.unify.web.ui.widget.control.Link;
import com.tcdng.unify.web.ui.widget.writer.AbstractTargetControlWriter;

/**
 * Link writer.
 * 
 * @author The Code Department
 * @since 1.0
 */
@Writes(Link.class)
@Component("link-writer")
public class LinkWriter extends AbstractTargetControlWriter {

    @Override
    protected void doWriteTargetControl(ResponseWriter writer, TargetControl targetControl) throws UnifyException {
        Link link = (Link) targetControl;
        String caption = null;
        if (link.isUsePreferredCaption()) {
            caption = link.getPreferredCaption();
            if (StringUtils.isBlank(caption)) {
                return;
            }
        }
        
        writer.write("<a");
        writeTagAttributes(writer, link);
        writer.write(">");
        if(StringUtils.isNotBlank(caption)) {
            writer.writeWithHtmlEscape(caption);
        } else {
            writeCaption(writer, link);
        }
        writer.write("</a>");
    }
}
