/* 
 * Copyright 2006, Queensland University of Technology
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy of 
 * the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations under 
 * the License.
 * 
 * Author: Bradley Beddoes
 * Creation Date: 05/12/2006
 * 
 * Purpose: Thrown when an invalid interaction occurs with web services client layer
 */
package com.qut.middleware.delegator.shib.ws.exception;

/** */
public class WSClientException extends Exception
{
	private static final long serialVersionUID = 3144732993218944580L;

	/**
	 * Thrown when an invalid SAML LogoutRequest has been presented.
	 * 
	 * @param message Human readable message indicating why this exception was thrown
	 * @param cause Any exception which caused this exception to be thrown, may be null
	 */
	public WSClientException(String message, Exception cause)
	{
		super(message, cause);
	}
	
	/**
	 * Thrown when an invalid SAML LogoutRequest has been presented.
	 * 
	 * @param message Human readable message indicating why this exception was thrown
	 */
	public WSClientException(String message)
	{
		super(message);
	}
}
