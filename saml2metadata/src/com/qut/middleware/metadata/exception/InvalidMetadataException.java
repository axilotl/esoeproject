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
 * Creation Date: 17/04/2008
 * 
 * Purpose: 
 */

package com.qut.middleware.metadata.exception;

public class InvalidMetadataException extends Exception
{
	private static final long serialVersionUID = -19045827926768789L;
	private static final String DEFAULT_MESSAGE = "The operation could not be performed because the metadata detected to be invalid.";
	
	public InvalidMetadataException()
	{
		super(DEFAULT_MESSAGE);
	}

	public InvalidMetadataException(String message)
	{
		super(message);
	}

	public InvalidMetadataException(Throwable cause)
	{
		super(cause);
	}

	public InvalidMetadataException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
