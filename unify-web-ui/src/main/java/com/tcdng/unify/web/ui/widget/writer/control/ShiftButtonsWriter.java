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
package com.tcdng.unify.web.ui.widget.writer.control;

import com.tcdng.unify.core.UnifyException;
import com.tcdng.unify.core.annotation.Component;
import com.tcdng.unify.core.annotation.Writes;
import com.tcdng.unify.web.ui.widget.Control;
import com.tcdng.unify.web.ui.widget.ResponseWriter;
import com.tcdng.unify.web.ui.widget.Widget;
import com.tcdng.unify.web.ui.widget.control.ShiftButtons;
import com.tcdng.unify.web.ui.widget.writer.AbstractControlWriter;

/**
 * Shift buttons writer.
 * 
 * @author The Code Department
 * @since 1.0
 */
@Writes(ShiftButtons.class)
@Component("shiftbuttons-writer")
public class ShiftButtonsWriter extends AbstractControlWriter {

    @Override
    protected void doWriteStructureAndContent(ResponseWriter writer, Widget widget) throws UnifyException {
        ShiftButtons shiftButtons = (ShiftButtons) widget;
        boolean disabled = !shiftButtons.isContainerEditable();
        Control topCtrl = shiftButtons.getShiftTopCtrl();
        Control upCtrl = shiftButtons.getShiftUpCtrl();
        Control downCtrl = shiftButtons.getShiftDownCtrl();
        Control bottomCtrl = shiftButtons.getShiftBottomCtrl();

        topCtrl.setDisabled(disabled);
        upCtrl.setDisabled(disabled);
        downCtrl.setDisabled(disabled);
        bottomCtrl.setDisabled(disabled);

        writer.write("<div");
        writeTagAttributes(writer, shiftButtons);
        writer.write(">");
        writer.writeStructureAndContent(topCtrl);
        writer.writeHtmlFixedSpace();
        writer.writeStructureAndContent(upCtrl);
        writer.writeHtmlFixedSpace();
        writer.writeStructureAndContent(downCtrl);
        writer.writeHtmlFixedSpace();
        writer.writeStructureAndContent(bottomCtrl);
        writer.write("</div>");
    }

}
