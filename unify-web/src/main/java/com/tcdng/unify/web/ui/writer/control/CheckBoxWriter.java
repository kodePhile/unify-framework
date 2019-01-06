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
package com.tcdng.unify.web.ui.writer.control;

import com.tcdng.unify.core.UnifyException;
import com.tcdng.unify.core.annotation.Component;
import com.tcdng.unify.core.annotation.Writes;
import com.tcdng.unify.web.ui.ResponseWriter;
import com.tcdng.unify.web.ui.Widget;
import com.tcdng.unify.web.ui.control.CheckBox;
import com.tcdng.unify.web.ui.writer.AbstractControlWriter;

/**
 * Check box writer.
 * 
 * @author Lateef Ojulari
 * @since 1.0
 */
@Writes(CheckBox.class)
@Component("checkbox-writer")
public class CheckBoxWriter extends AbstractControlWriter {

    @Override
    protected void doWriteStructureAndContent(ResponseWriter writer, Widget widget) throws UnifyException {
        CheckBox checkBox = (CheckBox) widget;
        writer.write("<input type=\"checkbox\"");
        writeTagAttributes(writer, checkBox);
        if (checkBox.getValue(boolean.class)) {
            writer.write(" checked=\"checked\"");
        }
        writer.write("/>");
        if (!checkBox.isLayoutCaption()) {
            writeCaption(writer, checkBox);
        }
    }

}
