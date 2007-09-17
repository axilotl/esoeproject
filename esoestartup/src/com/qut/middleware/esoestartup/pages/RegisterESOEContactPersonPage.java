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
 */
package com.qut.middleware.esoestartup.pages;

import java.util.List;
import java.util.Vector;

import net.sf.click.control.Field;
import net.sf.click.control.Form;
import net.sf.click.control.Submit;

import com.qut.middleware.esoemanager.bean.ContactPersonBean;
import com.qut.middleware.esoemanager.pages.ContactPersonPage;
import com.qut.middleware.esoestartup.bean.ESOEBean;

public class RegisterESOEContactPersonPage extends ContactPersonPage
{
	public String action;
	public Integer ref;
	
	public ESOEBean esoeBean;
	
	public RegisterESOEContactPersonPage()
	{
		super();
	}
	
	@Override
	public void onInit()
	{
		super.onInit();
		
		this.esoeBean = (ESOEBean) this.retrieveSession(ESOEBean.class.getName());
	
		Submit nextButton = new Submit(PageConstants.NAV_NEXT_LABEL, this, PageConstants.NAV_NEXT_FUNC);
		Submit backButton = new Submit(PageConstants.NAV_PREV_LABEL, this, PageConstants.NAV_PREV_FUNC);
		
		this.contactDetails.add(backButton);
		this.contactDetails.add(nextButton);
		this.contactDetails.setButtonAlign(Form.ALIGN_RIGHT);
	}
	
	@Override
	public void onPost()
	{
		/* Ensure session data is correctly available */
		if(this.esoeBean == null)
		{
			previousClick();
			return;
		}
		
		super.createOrUpdateContact(esoeBean);
		
		if(this.contactDetails.isValid())
		{
			String redirectPath;
			
			/* Move allow users to enter additional contacts, send current form back */
			redirectPath = getContext().getPagePath(RegisterESOEContactPersonPage.class);
			setRedirect(redirectPath);
		}
	}
	
	@Override
	public void onGet()
	{
		/* Ensure session data is correctly available */
		if(this.esoeBean == null)
		{
			previousClick();
			return;
		}
		
		/* Ensure session data is correctly available */
		if(this.esoeBean == null)
		{
			previousClick();
			return;
		}
		
		/* Check if previous registration stage completed */
		Boolean status = (Boolean)this.retrieveSession(PageConstants.STAGE4_RES);
		if(status == null || status.booleanValue() != true)
		{
			previousClick();
		}
		
		if (this.action != null)
		{
			if (this.action.equals(PageConstants.EDIT))
			{
				editContact();
			}
			if (this.action.equals(PageConstants.DELETE))
			{
				deleteContact();
			}
		}
	}
	
	protected void deleteContact()
	{
		Vector<ContactPersonBean> contacts = (Vector<ContactPersonBean>) this.esoeBean.getContacts();
		if (contacts != null)
		{
			if (this.ref != null)
			{
				/*
				 * Submitted value for contact ID will be the current position of the contact in the contacts vector - 1
				 * as listed by velocity when writing the response
				 */
				contacts.remove(this.ref - 1);
				this.esoeBean.setContacts(contacts);
			}

		}
	}

	protected void editContact()
	{
		Vector<ContactPersonBean> contacts = (Vector<ContactPersonBean>) this.esoeBean.getContacts();
		if (contacts != null)
		{
			if (ref != null)
			{
				/*
				 * Submitted value for contact ID will be the current position of the contact in the contacts vector - 1
				 * as listed by velocity when writing the response
				 */
				ContactPersonBean contact = contacts.get(this.ref - 1);
				contacts.remove(this.ref - 1);
				this.esoeBean.setContacts(contacts);

				translateDetails(contact);
			}

		}
	}
	
	public boolean nextClick()
	{
		/* Ensure session data is correctly available */
		if(this.esoeBean == null)
		{
			previousClick();
			return false;
		}
		
		/* Determine if the user is clicking next with a fresh submission (as opposed to saving) if so
		 * attempt to add that contact to the list
		 */
		List<Field> fieldList = this.contactDetails.getFieldList();
		for (Field field : fieldList)
		{
			String value = field.getValue();
			if(!field.getName().equals(PageConstants.CONTACTTYPE) && !field.isHidden() && value.length() > 1)
			{
				if(this.contactDetails.isValid())
				{
					this.createOrUpdateContact(this.esoeBean);
					
					/* Attempting to create submittied contact failed */
					if(!this.contactDetails.isValid())
						return true;
				}
				else
					return true;
				
				break;
			}
		}
		
		Vector<ContactPersonBean> contacts = (Vector<ContactPersonBean>) this.esoeBean.getContacts();
		
		if(contacts == null || contacts.size() == 0)
		{
			/* We don't care about errors on the submission itself just that no contacts have been added */
			this.contactDetails.clearErrors();
			
			this.contactDetails.setError("No contacts added, please add at least one contact person");
			return true;
		}
		
		/* This stage completed correctly */
		this.storeSession(PageConstants.STAGE5_RES, new Boolean(true));
		
		/* Move client to add crypto details for this service */
		String path = getContext().getPagePath(RegisterESOEManagerServicePage.class);
		setRedirect(path);
		
		return false;
	}
	
	public boolean previousClick()
	{
		/* Move client to register service page */
		String path = getContext().getPagePath(RegisterESOELDAPAuthenticatorPage.class);
		setRedirect(path);
				
		return false;
	}
}
