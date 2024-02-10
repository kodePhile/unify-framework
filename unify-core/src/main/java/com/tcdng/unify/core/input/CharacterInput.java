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

package com.tcdng.unify.core.input;

import com.tcdng.unify.core.data.Input;

/**
 * Character input.
 * 
 * @author The Code Department
 * @since 1.0
 */
public class CharacterInput extends Input<Character> {

    public CharacterInput(String name, String description, String editor, boolean mandatory) {
        super(Character.class, name, description, editor, mandatory);
    }

    public void setValue(Character value) {
        this.value = value;
    }

    public Character getValue() {
        return value;
    }

}
