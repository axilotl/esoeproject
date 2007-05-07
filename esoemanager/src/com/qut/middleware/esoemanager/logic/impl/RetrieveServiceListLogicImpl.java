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
 * Creation Date: 1/5/07
 * 
 * Purpose: Retreive service list logic default implementation
 */
package com.qut.middleware.esoemanager.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.qut.middleware.esoemanager.Constants;
import com.qut.middleware.esoemanager.bean.ContactPersonBean;
import com.qut.middleware.esoemanager.bean.ServiceBean;
import com.qut.middleware.esoemanager.bean.ServiceNodeBean;
import com.qut.middleware.esoemanager.bean.impl.ServiceBeanImpl;
import com.qut.middleware.esoemanager.exception.RetrieveServiceListException;
import com.qut.middleware.esoemanager.exception.SPEPDAOException;
import com.qut.middleware.esoemanager.logic.RetrieveServiceListLogic;
import com.qut.middleware.esoemanager.spep.sqlmap.SPEPDAO;

public class RetrieveServiceListLogicImpl implements RetrieveServiceListLogic
{
	private SPEPDAO spepDAO;
	
	/* Local logging instance */
	private Logger logger = Logger.getLogger(RetrieveServiceListLogicImpl.class.getName());
	
	public RetrieveServiceListLogicImpl(SPEPDAO spepDAO)
	{
		if (spepDAO == null)
		{
			this.logger.error("spepDAO for RetrieveKeyStoreLogicImpl was NULL");
			throw new IllegalArgumentException("spepDAO for RetrieveKeyStoreLogicImpl was NULL");
		}
		
		this.spepDAO = spepDAO;
	}

	/* (non-Javadoc)
	 * @see com.qut.middleware.esoemanager.logic.RetrieveServiceListLogic#execute()
	 */
	public List<ServiceBean> execute() throws RetrieveServiceListException
	{
		try
		{
			List<ServiceBean> services = new ArrayList<ServiceBean>();
			List<String> activeServices = this.spepDAO.queryActiveServices();
			
			for(String entityID : activeServices)
			{
				ServiceBean bean = new ServiceBeanImpl();
				Vector<ContactPersonBean> contactPersons = new Vector<ContactPersonBean>();
				Vector<ServiceNodeBean> serviceNodes = new Vector<ServiceNodeBean>();
				
				/* Get the core system data for this service */
				List<Map<String, String>> serviceDetails = this.spepDAO.queryServiceDetails(entityID);
				for(Map<String, String> service : serviceDetails)
				{
					/* There should only be one, if there is multiple results the last one returned will be displayed */
					bean.setEntityID(service.get(Constants.FIELD_ENTITY_ID));
					bean.setActiveFlag(service.get(Constants.FIELD_ACTIVE_FLAG));
				}
				
				/* Get descriptive detail about the service */
				List<Map<String, String>> serviceDescription = this.spepDAO.queryServiceDescription(entityID);
				for(Map<String, String> description : serviceDescription)
				{
					/* There should only be one, if there is multiple results the last one returned will be displayed */
					bean.setServiceName(description.get(Constants.FIELD_SERVICE_NAME));
					bean.setServiceURL(description.get(Constants.FIELD_SERVICE_URL));
					bean.setServiceDescription(description.get(Constants.FIELD_SERVICE_DESC));
					bean.setServiceAuthzFailureMsg(description.get(Constants.FIELD_SERVICE_AUTHZ_FAIL));
				}
				
				services.add(bean);
			}
			
			return services;
		}
		catch (SPEPDAOException e)
		{
			throw new RetrieveServiceListException("Exception when attempting to retrieve service list", e);
		}
	}

}