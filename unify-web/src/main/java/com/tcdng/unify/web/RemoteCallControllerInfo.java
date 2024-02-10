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

import java.util.Map;

/**
 * Encapsulates information about a remote-call controller.
 * 
 * @author The Code Department
 * @since 1.0
 */
public class RemoteCallControllerInfo extends ControllerInfo<RemoteAction> {

	private String remoteCallGateName;

	public RemoteCallControllerInfo(String controllerName, String remoteCallGateName, Map<String, RemoteAction> remoteHandlerMap) {
		super(controllerName, remoteHandlerMap);
		this.remoteCallGateName = remoteCallGateName;
	}

	public String getRemoteCallGateName() {
		return remoteCallGateName;
	}

	public boolean isRemoteCallGate() {
		return remoteCallGateName != null;
	}

}
