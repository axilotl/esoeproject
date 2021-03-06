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
 * Creation Date: 18/01/2008
 * 
 * Purpose: 
 */

#ifndef REQUESTPARAMETERS_H_
#define REQUESTPARAMETERS_H_

#include "ISAPIRequest.h"

#include <string>
#include <map>

namespace spep { namespace isapi {

	class RequestParameters
	{
		private:
		std::map<std::string,std::string> _params;
		
		RequestParameters( const RequestParameters& other );
		RequestParameters& operator=( const RequestParameters& other );
		
		public:
		RequestParameters( ISAPIRequest *req );
		const std::string& operator[]( const std::string& name );
	};

} }

#endif /*REQUESTPARAMETERS_H_*/
