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
package com.tcdng.unify.core.filter.policy;

import com.tcdng.unify.core.UnifyException;
import com.tcdng.unify.core.filter.AbstractSingleParamObjectFilterPolicy;

/**
 * Ends with policy.
 * 
 * @author The Code Department
 * @since 1.0
 */
public class EndsWithPolicy extends AbstractSingleParamObjectFilterPolicy {

    public EndsWithPolicy() {
        super(false);
    }

    @Override
    protected boolean doMatch(Object fieldVal, Object param) throws UnifyException {
        return ((String) fieldVal).endsWith((String) param);
    }

}