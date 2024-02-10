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
import com.tcdng.unify.web.ui.widget.ResponseWriter;
import com.tcdng.unify.web.ui.widget.TargetControl;
import com.tcdng.unify.web.ui.widget.control.ToggleButton;
import com.tcdng.unify.web.ui.widget.writer.AbstractTargetControlWriter;

/**
 * Toggle button writer.
 * 
 * @author The Code Department
 * @since 1.0
 */
@Writes(ToggleButton.class)
@Component("togglebutton-writer")
public class ToggleButtonWriter extends AbstractTargetControlWriter {

    @Override
    protected void doWriteTargetControl(ResponseWriter writer, TargetControl targetControl) throws UnifyException {
        ToggleButton toggleButton = (ToggleButton) targetControl;
        writer.write("<button type=\"button\"");
        writeTagAttributes(writer, toggleButton);
        writer.write(">");
        if (toggleButton.getValue(boolean.class, toggleButton.getToggleBinding())) {
            writer.writeWithHtmlEscape(toggleButton.getOnMessageKey());
        } else {
            writer.writeWithHtmlEscape(toggleButton.getOffMessageKey());
        }
        writer.write("</button>");
    }

}
