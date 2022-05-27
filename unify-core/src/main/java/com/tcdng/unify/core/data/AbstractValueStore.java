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
package com.tcdng.unify.core.data;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.tcdng.unify.core.UnifyException;
import com.tcdng.unify.core.util.DataUtils;
import com.tcdng.unify.core.util.GetterSetterInfo;
import com.tcdng.unify.core.util.ReflectUtils;

/**
 * Abstract value store.
 * 
 * @author The Code Department
 * @since 1.0
 */
public abstract class AbstractValueStore implements ValueStore {

    private String dataPrefix;
    
    private ValueStorePolicy policy;
    
    @Override
    public String getDataPrefix() {
        return dataPrefix;
    }

    @Override
    public void setDataPrefix(String dataPrefix) {
        this.dataPrefix = dataPrefix;
    }

    @Override
    public void setPolicy(ValueStorePolicy policy) {
        this.policy = policy;
    }

    @Override
    public Audit diff(ValueStore newSource) throws UnifyException {
        Audit.Builder ab = Audit.newBuilder();
        for (GetterSetterInfo getterSetterInfo : ReflectUtils.getGetterSetterList(getDataClass())) {
            if (getterSetterInfo.isGetterSetter()) {
                String fieldName = getterSetterInfo.getName();
                Object oldVal = retrieve(fieldName);
                Object newVal = newSource.retrieve(fieldName);
                if (!DataUtils.equals(oldVal, newVal)) {
                    ab.addItem(fieldName, oldVal, newVal);
                }
            }
        }

        return ab.build();
    }

    @Override
    public Audit diff(ValueStore newSource, String... inclusionFieldNames) throws UnifyException {
        Set<String> inclusion = new HashSet<String>(Arrays.asList(inclusionFieldNames));
        return this.diff(newSource, inclusion);
    }

    @Override
    public Audit diff(ValueStore newSource, Collection<String> inclusionFieldNames) throws UnifyException {
        Audit.Builder ab = Audit.newBuilder();
        for (GetterSetterInfo getterSetterInfo : ReflectUtils.getGetterSetterList(getDataClass())) {
            if (getterSetterInfo.isGetterSetter()) {
                String fieldName = getterSetterInfo.getName();
                if (inclusionFieldNames.contains(fieldName)) {
                    Object oldVal = retrieve(fieldName);
                    Object newVal = newSource.retrieve(fieldName);
                    if (!DataUtils.equals(oldVal, newVal)) {
                        ab.addItem(fieldName, oldVal, newVal);
                    }
                }
            }
        }

        return ab.build();
    }

    @Override
    public void copy(ValueStore source) throws UnifyException {
        for (GetterSetterInfo getterSetterInfo : ReflectUtils.getGetterSetterList(getDataClass())) {
            if (getterSetterInfo.isGetterSetter()) {
                String fieldName = getterSetterInfo.getName();
                store(fieldName, source.retrieve(fieldName));
            }
        }
    }

    @Override
    public void copyWithExclusions(ValueStore source, String... exclusionFieldNames) throws UnifyException {
        Set<String> exclusion = new HashSet<String>(Arrays.asList(exclusionFieldNames));
        copyWithExclusions(source, exclusion);
    }

    @Override
    public void copyWithInclusions(ValueStore source, String... inclusionFieldNames) throws UnifyException {
        Set<String> inclusion = new HashSet<String>(Arrays.asList(inclusionFieldNames));
        copyWithInclusions(source, inclusion);
    }

    @Override
    public void copyWithExclusions(ValueStore source, Collection<String> exclusionFieldNames) throws UnifyException {
        for (GetterSetterInfo getterSetterInfo : ReflectUtils.getGetterSetterList(getDataClass())) {
            if (getterSetterInfo.isGetterSetter()) {
                String fieldName = getterSetterInfo.getName();
                if (!exclusionFieldNames.contains(fieldName)) {
                    store(fieldName, source.retrieve(fieldName));
                }
            }
        }
    }

    @Override
    public void copyWithInclusions(ValueStore source, Collection<String> inclusionFieldNames) throws UnifyException {
        for (GetterSetterInfo getterSetterInfo : ReflectUtils.getGetterSetterList(getDataClass())) {
            if (getterSetterInfo.isGetterSetter()) {
                String fieldName = getterSetterInfo.getName();
                if (inclusionFieldNames.contains(fieldName)) {
                    store(fieldName, source.retrieve(fieldName));
                }
            }
        }
    }

    protected ValueStorePolicy getPolicy() {
        return policy;
    }

    protected abstract Class<?> getDataClass() throws UnifyException;

}
