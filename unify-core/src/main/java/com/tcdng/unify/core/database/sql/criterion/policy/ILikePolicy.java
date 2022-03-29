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
package com.tcdng.unify.core.database.sql.criterion.policy;

import com.tcdng.unify.core.UnifyException;
import com.tcdng.unify.core.database.sql.SqlDataSourceDialectPolicies;
import com.tcdng.unify.core.database.sql.SqlLikeType;

/**
 * Case-insensitive like operator policy.
 * 
 * @author The Code Department
 * @since 1.0
 */
public class ILikePolicy extends SingleParameterPolicy {

    private SqlLikeType type;
    
    public ILikePolicy(SqlDataSourceDialectPolicies rootPolicies) {
        this(SqlLikeType.CONTAINS, rootPolicies);
    }
    
    protected ILikePolicy(SqlLikeType type, SqlDataSourceDialectPolicies rootPolicies) {
        super(" LIKE ", rootPolicies, true);
        this.type = type;
    }

    @Override
    protected Object resolveParam(String tableName, Object param) throws UnifyException{
        return getRootPolicies().generateLikeParameter(type, tableName, param);
    }
}
