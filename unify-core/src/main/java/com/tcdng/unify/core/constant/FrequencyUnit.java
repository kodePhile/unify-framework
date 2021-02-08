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
package com.tcdng.unify.core.constant;

import com.tcdng.unify.core.annotation.StaticList;
import com.tcdng.unify.core.util.EnumUtils;

/**
 * Constant definitions for frequency units.
 * 
 * @author Lateef Ojulari
 * @since 1.0
 */
@StaticList(name = "frequencyunitlist", description="$m{staticlist.frequencyunitlist}")
public enum FrequencyUnit implements EnumConst {

    SECOND("S"), MINUTE("M"), HOUR("H");

    private final String code;

    private FrequencyUnit(String code) {
        this.code = code;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String defaultCode() {
        return SECOND.code;
    }

    public static FrequencyUnit fromCode(String code) {
        return EnumUtils.fromCode(FrequencyUnit.class, code);
    }

    public static FrequencyUnit fromName(String name) {
        return EnumUtils.fromName(FrequencyUnit.class, name);
    }
}
