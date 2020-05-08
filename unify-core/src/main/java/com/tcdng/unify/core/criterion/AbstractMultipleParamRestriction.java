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

package com.tcdng.unify.core.criterion;

import java.util.Collection;

/**
 * Convenient abstract base class for multiple parameter restrictions.
 * 
 * @author Lateef Ojulari
 * @since 1.0
 */
public abstract class AbstractMultipleParamRestriction extends AbstractSimpleRestriction
        implements MultipleParamRestriction {

    private Collection<?> params;

    public AbstractMultipleParamRestriction(String propertyName, Collection<?> params) {
        super(propertyName);
        this.params = params;
    }

    @Override
    public Collection<?> getParams() {
        return params;
    }

    @Override
    public void setParams(Collection<?> val) {
        this.params = val;
    }

    @Override
    public boolean isValid() {
        return params !=null && !params.isEmpty();
    }

}
