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
package com.tcdng.unify.convert.converters;

/**
 * A value to byte converter.
 * 
 * @author The Code Department
 * @since 1.0
 */
public class ByteConverter extends AbstractConverter<Byte> {

    @Override
    protected Byte doConvert(Object value, ConverterFormatter<?> formatter) throws Exception {
        if (value instanceof Number) {
            return Byte.valueOf(((Number) value).byteValue());
        }
        if (value instanceof String) {
            String string = ((String) value).trim();
            if (!string.isEmpty()) {
                if (formatter == null) {
                    return Byte.decode(string);
                }
                return doConvert(formatter.parse(string), null);
            }
        }
        return null;
    }
}
