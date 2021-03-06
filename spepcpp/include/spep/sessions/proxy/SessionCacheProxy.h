/* Copyright 2006-2007, Queensland University of Technology
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
 * Creation Date: 07/02/2007
 * 
 * Purpose: 
 */

#ifndef SESSIONCACHEPROXY_H_
#define SESSIONCACHEPROXY_H_

#include "spep/Util.h"
#include "spep/sessions/SessionCache.h"

#include "spep/ipc/Socket.h"

namespace spep { namespace ipc {
	
	class SPEPEXPORT SessionCacheProxy : public spep::SessionCache
	{
		
		public:
		SessionCacheProxy( spep::ipc::ClientSocketPool *socketPool );
		
		/** @see spep::SessionCache */
		/*@{*/
		virtual void getPrincipalSession(PrincipalSession& principalSession, const std::string& localSessionID);
		virtual void getPrincipalSessionByEsoeSessionID(PrincipalSession& principalSession, const std::wstring& esoeSessionID);
		virtual void insertPrincipalSession(const std::string& sessionID, PrincipalSession &principalSession);
		virtual void terminatePrincipalSession(const std::wstring& sessionID);
		
		virtual void getUnauthenticatedSession(UnauthenticatedSession &unauthenticatedSession, const std::wstring& requestID);
		virtual void insertUnauthenticatedSession(UnauthenticatedSession &unauthenticatedSession);
		virtual void terminateUnauthenticatedSession(const std::wstring& requestID);
		
		virtual void terminateExpiredSessions( int sessionCacheTimeout );
		/*@}*/

		private:
		// Defined mutable because even for const methods we still need to make an RPC call.
		mutable spep::ipc::ClientSocketPool *_socketPool;
		
	};
	
} }

#endif /*SESSIONCACHEPROXY_H_*/
