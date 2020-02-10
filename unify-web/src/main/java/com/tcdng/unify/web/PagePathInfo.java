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

package com.tcdng.unify.web;

/**
 * Page path information.
 * 
 * @author Lateef Ojulari
 * @since 1.0
 */
public class PagePathInfo {

    private String pathId;

    private String colorScheme;

    private String openPagePath;

    private String savePagePath;

    private String closePagePath;

    private boolean remoteSave;

    public PagePathInfo(String pathId, String colorScheme, String openPagePath, String savePagePath,
            String closePagePath, boolean remoteSave) {
        this.pathId = pathId;
        this.colorScheme = colorScheme;
        this.openPagePath = openPagePath;
        this.savePagePath = savePagePath;
        this.closePagePath = closePagePath;
        this.remoteSave = remoteSave;
    }

    public String getPathId() {
        return pathId;
    }

    public String getColorScheme() {
        return colorScheme;
    }

    public String getOpenPagePath() {
        return openPagePath;
    }

    public String getSavePagePath() {
        return savePagePath;
    }

    public String getClosePagePath() {
        return closePagePath;
    }

    public boolean isRemoteSave() {
        return remoteSave;
    }

    public void setColorScheme(String colorScheme) {
        this.colorScheme = colorScheme;
    }

    public void setSavePagePath(String savePagePath) {
        this.savePagePath = savePagePath;
    }

    public void setRemoteSave(boolean remoteSave) {
        this.remoteSave = remoteSave;
    }

}
