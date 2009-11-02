/* 
 * Copyright 2008, Queensland University of Technology
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
 * Author: Shaun Mangelsdorf
 * Creation Date: 08/09/2008
 * 
 * Purpose: 
 */

package com.qut.middleware.esoe.sessions.exception;

public class SessionCacheDAOException extends Exception
{
	private static final long serialVersionUID = -2107443113973063018L;

	public SessionCacheDAOException()
	{
		super();
	}

	public SessionCacheDAOException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public SessionCacheDAOException(String message)
	{
		super(message);
	}

	public SessionCacheDAOException(Throwable cause)
	{
		super(cause);
	}

}
