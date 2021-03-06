/* Copyright 2006, Queensland University of Technology
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
 * Creation Date: 28/09/2006
 * 
 * Purpose: Indicates that a failure has occurred in the authentication process
 */
package com.qut.middleware.esoe.authn.exception;

/** */
public class AuthnFailureException extends Exception
{
	private static final long serialVersionUID = 2954971878841146814L;
	
	/**
	 * Constructor
	 * @param message
	 */
	public AuthnFailureException(String message)
	{
		super(message);
	}
	
	/**
	 * Constructor which also specifies cause exception
	 * @param message human readable representation of why this was created
	 * @param cause Embedded exception cause stack
	 */
	public AuthnFailureException(String message, Exception cause)
	{
		super(message, cause);
	}
}
