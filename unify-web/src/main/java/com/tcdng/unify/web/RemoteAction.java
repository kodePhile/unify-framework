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
package com.tcdng.unify.web;

import java.lang.reflect.Method;

import com.tcdng.unify.web.remotecall.RemoteCallParams;
import com.tcdng.unify.web.remotecall.RemoteCallResult;

/**
 * Remote action.
 * 
 * @author The Code Department
 * @since 1.0
 */
public class RemoteAction extends Action {

    private String methodCode;

    private boolean restricted;

    public RemoteAction(String methodCode, Method method, boolean restricted) {
    	super(method);
        this.methodCode = methodCode;
        this.restricted = restricted;
    }

    public String getMethodCode() {
        return methodCode;
    }

    public boolean isRestricted() {
        return restricted;
    }

	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends RemoteCallParams> getParamType() {
		return (Class<? extends RemoteCallParams>) super.getParamType();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends RemoteCallResult> getReturnType() {
		return (Class<? extends RemoteCallResult> ) super.getReturnType();
	}
    
}
