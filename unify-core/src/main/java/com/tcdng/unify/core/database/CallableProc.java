/*
 * Copyright 2018-2023 The Code Department.
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


/**
 * Interface required by all database procedures.
 * 
 * @author The Code Department
 * @since 1.0
 */
public interface CallableProc {

    /**
     * Gets the callable procedure return value.
     * @return the return value object.
     */
    Object getReturnValue();
    
    /**
     * Sets the callable procedure return value.
     * 
     * @param returnValue the return value to set
     */
    void setReturnValue(Object returnValue);
}
