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

package com.tcdng.unify.core.database;

import java.util.List;

import com.tcdng.unify.core.annotation.ChildList;

/**
 * Abstract base test class for work entities.
 * 
 * @author The Code Department
 * @since 1.0
 */
public abstract class AbstractWorkEntity extends AbstractTestVersionedTableEntity {

    @ChildList
    private List<FileAttachment> fileList;

    public List<FileAttachment> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileAttachment> fileList) {
        this.fileList = fileList;
    }
}
