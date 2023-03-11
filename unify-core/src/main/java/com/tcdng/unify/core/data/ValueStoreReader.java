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

package com.tcdng.unify.core.data;

import com.tcdng.unify.core.UnifyException;

/**
 * Value store reader.
 * 
 * @author The Code Department
 * @since 1.0
 */
public class ValueStoreReader {
    
    private ValueStore valueStore;
    
    public ValueStoreReader(ValueStore valueStore) {
        this.valueStore = valueStore;
    }

    public ValueStoreReader(Object valueObject) {
        this.valueStore = new BeanValueStore(valueObject);
    }
    
    public boolean isNull(String name) throws UnifyException {
    	return valueStore.isNull(name);
    }

    public boolean isNotNull(String name) throws UnifyException {
    	return valueStore.isNotNull(name);
    }

    public Object readScratch(String fieldName) throws UnifyException {
        return valueStore.getTempValue(fieldName);
    }

    public <T> T readScratch(Class<T> type, String fieldName) throws UnifyException {
        return valueStore.getTempValue(type, fieldName);
    }

    public Object read(String fieldName) throws UnifyException {
        return valueStore.retrieve(fieldName);
    }

    public <T> T read(Class<T> type, String fieldName) throws UnifyException {
        return valueStore.retrieve(type, fieldName);
    }
    
    public ValueStore getValueStore() {
        return valueStore;
    }
    
    public Object getValueObject() {
        return valueStore.getValueObject();
    }

    public Object getTempValue(String name) throws UnifyException {
        return valueStore.getTempValue(name);
    }

    public <T> T getTempValue(Class<T> type, String name) throws UnifyException {
        return valueStore.getTempValue(type, name);
    }

    public void setTempValue(String name, Object value) throws UnifyException {
        valueStore.setTempValue(name, value);
    }

    public boolean isTempValue(String name) {
        return valueStore.isTempValue(name);
    }
}
