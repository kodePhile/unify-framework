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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.tcdng.unify.core.UnifyException;
import com.tcdng.unify.core.annotation.Component;
import com.tcdng.unify.core.annotation.Writes;
import com.tcdng.unify.web.ui.ResponseWriter;
import com.tcdng.unify.web.ui.Widget;
import com.tcdng.unify.web.ui.control.Tree;
import com.tcdng.unify.web.ui.data.EventType;
import com.tcdng.unify.web.ui.data.TreeInfo;
import com.tcdng.unify.web.ui.data.TreeInfo.MenuInfo;
import com.tcdng.unify.web.ui.data.TreeInfo.TreeItemInfo;
import com.tcdng.unify.web.ui.data.TreeItemCategoryInfo;
import com.tcdng.unify.web.ui.writer.AbstractControlWriter;

/**
 * Tree writer.
 * 
 * @author Lateef Ojulari
 * @since 1.0
 */
@Writes(Tree.class)
@Component("tree-writer")
public class TreeWriter extends AbstractControlWriter {

    private static final String RESERVED_MENU_PREFIX = "trermp";

    private static final String[] EVENT_CODES = { EventType.MOUSE_CLICK.code(), EventType.MOUSE_DBLCLICK.code(),
            EventType.MOUSE_RIGHTCLICK.code(), EventType.MOUSE_MENUCLICK.code() };

    @Override
    protected void doWriteStructureAndContent(ResponseWriter writer, Widget widget) throws UnifyException {
        Tree tree = (Tree) widget;
        writer.write("<div");
        writeTagAttributes(writer, tree);
        writer.write("><ul>");
        TreeInfo treeInfo = (TreeInfo) tree.getValue();
        List<Integer> selectedItemIds = Collections.emptyList();
        List<Integer> visibleItemIds = Collections.emptyList();
        if (treeInfo != null) {
            selectedItemIds = treeInfo.getSelectedItemIds();
            String collapsedSrc = tree.getCollapsedIcon();
            String expandedSrc = tree.getExpandedIcon();
            String ctrlIdBase = tree.getControlImgIdBase();
            String captionIdBase = tree.getCaptionIdBase();

            visibleItemIds = new ArrayList<Integer>();
            TreeItemInfo treeItemInfo = treeInfo.getFirstTreeItemInfo();
            while (treeItemInfo != null) {
                if (!treeItemInfo.isHidden()) {
                    Integer itemId = treeItemInfo.getId();
                    visibleItemIds.add(itemId);

                    // Open branch
                    writer.write("<li>");

                    // Add left tabs
                    for (int j = 0; j < treeItemInfo.getDepth(); j++) {
                        writeFileImageHtmlElement(writer, "$t{images/blank.png}", null, null, null);
                    }

                    // Add control icon
                    if (treeItemInfo.isParent()) {
                        if (treeItemInfo.isExpanded()) {
                            writeFileImageHtmlElement(writer, expandedSrc, ctrlIdBase + itemId, null, null);
                        } else {
                            writeFileImageHtmlElement(writer, collapsedSrc, ctrlIdBase + itemId, null, null);
                        }
                    } else {
                        writeFileImageHtmlElement(writer, "$t{images/blank.png}", null, null, null);
                    }

                    // Add item icon and caption
                    writer.write("<span id=\"").write(captionIdBase).write(itemId);
                    if (selectedItemIds.contains(itemId)) {
                        writer.write("\" class=\"tsel\">");
                    } else {
                        writer.write("\" class=\"tnorm\">");
                    }

                    writeFileImageHtmlElement(writer, treeItemInfo.getCategoryInfo().getIcon(), null, null, null);
                    writer.write("<span>");
                    writer.writeWithHtmlEscape(treeItemInfo.getCaption());
                    writer.write("</span></span>");

                    // Close branch
                    writer.write("</li>");
                }

                treeItemInfo = treeItemInfo.getNext();
            }
        }
        writer.write("</ul>");
        // Hidden controls
        writer.write("<select ");
        writeTagId(writer, tree.getSelectedItemIdsCtrl());
        writeTagStyle(writer, "display:none;");
        writer.write(" multiple=\"multiple\">");
        if (treeInfo != null) {
            for (Integer itemId : visibleItemIds) {
                writer.write("<option value=\"").write(itemId).write("\"");
                if (selectedItemIds.contains(itemId)) {
                    writer.write(" selected");
                }
                writer.write("></option>");
            }
        }
        writer.write("</select>");

        writer.writeStructureAndContent(tree.getMenuCodeCtrl());
        writer.writeStructureAndContent(tree.getSelectedCtrlIdCtrl());
        writer.writeStructureAndContent(tree.getEventTypeCtrl());
        writer.write("</div>");

        if (treeInfo != null) {
            // Main Menu
            if (treeInfo.isMenu()) {
                String menuId = tree.getPrefixedId(RESERVED_MENU_PREFIX);
                writeMenuStructureAndContent(writer, menuId, treeInfo.getMenuList());
            }

            // Category menu
            for (TreeItemCategoryInfo treeItemCategoryInfo : treeInfo.getTreeCategoryInfos()) {
                if (treeItemCategoryInfo.isMenu()) {
                    String menuId = tree.getPrefixedId(treeItemCategoryInfo.getName());
                    writeMenuStructureAndContent(writer, menuId, treeItemCategoryInfo.getMenuList());
                }
            }
        }

    }

    @Override
    protected void doWriteBehavior(ResponseWriter writer, Widget widget) throws UnifyException {
        super.doWriteBehavior(writer, widget);

        try {
            Tree tree = (Tree) widget;
            writer.write("ux.rigTree(");
            JsonObject jsonPrm = Json.object();
            jsonPrm.add("pId", tree.getId());
            jsonPrm.add("pContId", tree.getContainerId());
            jsonPrm.add("pCmdURL", getCommandURL());
            jsonPrm.add("pSelCtrlId", tree.getSelectedCtrlIdCtrl().getId());
            jsonPrm.add("pSelItemId", tree.getSelectedItemIdsCtrl().getId());
            jsonPrm.add("pMenuCodeCtrlId", tree.getMenuCodeCtrl().getId());
            jsonPrm.add("pEventTypeId", tree.getEventTypeCtrl().getId());
            jsonPrm.add("pSel", "tsel");
            jsonPrm.add("pNorm", "tnorm");
            jsonPrm.add("pCtrlBase", tree.getControlImgIdBase());
            jsonPrm.add("pLblBase", tree.getCaptionIdBase());
            jsonPrm.add("pEventCode", Json.array(EVENT_CODES));

            // Added to be able to push values on tree event
            List<String> pageNames = getPageManager().getExpandedReferences(tree.getId());
            if (!pageNames.isEmpty()) {
                jsonPrm.add("pEventRef", Json.array(pageNames.toArray(new String[pageNames.size()])));
            }

            TreeInfo treeInfo = (TreeInfo) tree.getValue();
            if (treeInfo != null) {
                if (treeInfo.isMenu()) {
                    jsonPrm.add("pMenu", getJsonMenu(tree.getPrefixedId(RESERVED_MENU_PREFIX), treeInfo.getMenuList()));
                }

                JsonArray menus = Json.array();
                for (TreeItemCategoryInfo treeItemCategoryInfo : treeInfo.getTreeCategoryInfos()) {
                    if (treeItemCategoryInfo.isMenu()) {
                        menus.add(getJsonMenu(tree.getPrefixedId(treeItemCategoryInfo.getName()),
                                treeItemCategoryInfo.getMenuList()));
                    }
                }
                jsonPrm.add("pMenus", menus);
            }

            JsonArray items = Json.array();
            TreeItemInfo treeItemInfo = treeInfo.getFirstTreeItemInfo();
            while (treeItemInfo != null) {
                TreeItemCategoryInfo treeItemCategoryInfo = treeItemInfo.getCategoryInfo();
                String popupId = "pop_" + tree.getPrefixedId(treeItemCategoryInfo.getName());
                Set<EventType> eventTypes = treeItemCategoryInfo.getEventTypes();
                if (!treeItemInfo.isHidden()) {
                    JsonObject item = Json.object();
                    item.add("idx", treeItemInfo.getId());
                    if (treeItemCategoryInfo.isMenu()) {
                        item.add("popupId", popupId);
                    }

                    item.add("parent", treeItemInfo.isParent());
                    item.add("expanded", treeItemInfo.isExpanded());
                    item.add("pClick", eventTypes.contains(EventType.MOUSE_CLICK));
                    item.add("pDblClick", eventTypes.contains(EventType.MOUSE_DBLCLICK));
                    item.add("pRtClick", eventTypes.contains(EventType.MOUSE_RIGHTCLICK));
                    items.add(item);
                }

                treeItemInfo = treeItemInfo.getNext();
            }
            jsonPrm.add("pItemList", items);

            jsonPrm.writeTo(writer.getStringWriter());
            writer.write(");");
        } catch (IOException e) {
            throwOperationErrorException(e);
        }
    }

    private void writeMenuStructureAndContent(ResponseWriter writer, String menuId, List<MenuInfo> menuInfoList)
            throws UnifyException {
        writer.write("<div");
        writeTagId(writer, "pop_" + menuId);
        writeTagStyleClass(writer, "tree-popup");
        writer.write(">");
        writer.write("<ul id=\"").write("popc_" + menuId).write("\">");
        for (int i = 0; i < menuInfoList.size(); i++) {
            MenuInfo menuInfo = menuInfoList.get(i);
            if (menuInfo.isSeparator()) {
                writer.write("<li class=\"msep\">");
            } else {
                writer.write("<li>");
            }
            writer.write("<a class=\"mitem\" id=\"").write(menuId + i).write("\">");
            writer.writeWithHtmlEscape(resolveSessionMessage(menuInfo.getCaption()));
            writer.write("</a></li>");
        }
        writer.write("</ul>");
        writer.write("</div>");
    }

    private JsonObject getJsonMenu(String menuId, List<MenuInfo> menuInfoList) throws UnifyException {
        JsonObject menu = Json.object();
        menu.add("menuId", menuId);
        menu.add("popupId", "pop_" + menuId);

        JsonArray items = Json.array();
        for (int i = 0; i < menuInfoList.size(); i++) {
            MenuInfo menuInfo = menuInfoList.get(i);
            JsonObject item = Json.object();
            item.add("id", menuId + i);
            item.add("code", menuInfo.getCode());
            item.add("multiple", menuInfo.isShowOnMultiple());
            items.add(item);
        }
        menu.add("items", items);
        return menu;
    }

}
