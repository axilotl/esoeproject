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
 * Author: Shaun Mangelsdorf
 * Creation Date: 11/10/2006
 *
 * Purpose: Tests the LDAP implementation of the Handler interface for mapping
 * 		attributes to identities.
 */
package com.qut.middleware.esoe.identity.plugins.ldap;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import com.qut.middleware.esoe.identity.plugins.ldap.handler.LDAPHandler;
import com.qut.middleware.esoe.sessions.bean.IdentityAttribute;
import com.qut.middleware.esoe.sessions.bean.IdentityData;
import com.qut.middleware.esoe.sessions.bean.SessionConfigData;
import com.qut.middleware.esoe.sessions.bean.impl.IdentityAttributeImpl;
import com.qut.middleware.esoe.sessions.bean.impl.IdentityDataImpl;
import com.qut.middleware.esoe.sessions.bean.impl.SessionConfigDataImpl;
import com.qut.middleware.esoe.sessions.exception.ConfigurationValidationException;
import com.qut.middleware.esoe.sessions.exception.DataSourceException;
import com.qut.middleware.esoe.sessions.exception.SessionsDAOException;
import com.qut.middleware.esoe.sessions.identity.pipeline.Handler;
import com.qut.middleware.esoe.sessions.sqlmap.SessionsDAO;
import com.qut.middleware.metadata.processor.MetadataProcessor;

public class LDAPHandlerImplTest
{
	private String IDENTIFIER = "uid";

	private Properties props;
	private String LDAP_SERVER;
	private String LDAP_SERVER_URL;
	private String LDAP_BASE_DN;
	private String CONTEXT_BASE;
	private String SEARCH_BASE;
	private String LDAP_USER;
	private String LDAP_USER_DN;
	private String LDAP_USER_PASSWORD;
	private String LDAP_ADMIN_USER;
	private String LDAP_ADMIN_USER_PASSWORD;
	private SessionsDAO sessionsDAO;
	private MetadataProcessor metadata;
	private SessionConfigData sessionConfigData;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		props = new Properties();
		FileInputStream reader = new FileInputStream(new File("tests/testdata/functional.properties"));
		props.load(reader);

		this.LDAP_SERVER = props.getProperty("ldapServer");
		this.LDAP_SERVER_URL = props.getProperty("ldapServerURL");
		this.LDAP_BASE_DN = props.getProperty("ldapBaseDN");
		this.CONTEXT_BASE = props.getProperty("ldapContextBase");
		this.SEARCH_BASE = props.getProperty("ldapSearchBase");
		this.LDAP_USER = props.getProperty("ldapUser");
		this.LDAP_USER_DN = props.getProperty("ldapUserDN");
		this.LDAP_USER_PASSWORD = props.getProperty("ldapUserPass");
	}

	/**
	 * Test method for
	 * {@link com.qut.middleware.esoe.sessions.identity.pipeline.impl.LDAPHandlerImpl#execute(com.qut.middleware.esoe.sessions.bean.IdentityData)}.
	 */
	@Test
	public final void testExecute()
	{
		LdapContextSource context = new LdapContextSource();
		context.setBase(this.CONTEXT_BASE);
		context.setUserName(this.LDAP_USER_DN );
		context.setPassword(this.LDAP_USER_PASSWORD);
		context.setUrl(this.LDAP_SERVER_URL);

		try{
		context.afterPropertiesSet();
		}catch(Exception ex){throw new UnsupportedOperationException();}

		LdapTemplate template = new LdapTemplate(context);

		try
		{
			File xmlConfig = new File(this.getClass().getResource("sessiondata.xml").toURI()); //$NON-NLS-1$

			FileInputStream attributeStream = new FileInputStream(xmlConfig);
			byte[] attributeData = new byte[(int)xmlConfig.length()];
			attributeStream.read(attributeData);

			String entityID = "http://test.service.com";
			Integer entID = new Integer("1");

			this.metadata = createMock(MetadataProcessor.class);

			this.sessionsDAO = createMock(SessionsDAO.class);
			expect(sessionsDAO.getEntID(entityID)).andReturn(entID);
			expect(sessionsDAO.selectActiveAttributePolicy(entID)).andReturn(attributeData);

			replay(this.metadata);
			replay(this.sessionsDAO);

			this.sessionConfigData = new SessionConfigDataImpl(sessionsDAO, metadata, entityID);
		}
		catch(URISyntaxException ex)
		{
			fail("Failed to parse session data"); //$NON-NLS-1$
		}
		catch (ConfigurationValidationException ex)
		{
			fail("Failed to parse session data"); //$NON-NLS-1$
		}
		catch (IOException e)
		{
			fail("IOException "  + e.getLocalizedMessage()); //$NON-NLS-1$
		}
		catch (SessionsDAOException e)
		{
			fail("SessionsDAOException " + e.getLocalizedMessage()); //$NON-NLS-1$
		}

		Handler handler = new LDAPHandler(template, this.IDENTIFIER, this.SEARCH_BASE, sessionConfigData);

		IdentityData data = new IdentityDataImpl();
		data.setPrincipalAuthnIdentifier("beddoes"); //$NON-NLS-1$

		IdentityAttribute attribute = new IdentityAttributeImpl();
		data.getAttributes().put("uid", attribute); //$NON-NLS-1$
		attribute = new IdentityAttributeImpl();
		data.getAttributes().put("mail", attribute); //$NON-NLS-1$
		attribute = new IdentityAttributeImpl();
		data.getAttributes().put("sn", attribute); //$NON-NLS-1$

		try
		{
			handler.execute(data);
		}
		catch (DataSourceException ex)
		{
			fail("Problem connecting to LDAP server"); //$NON-NLS-1$
			return;
		}

		Map<String,IdentityAttribute> attributes = data.getAttributes();
		IdentityAttribute uidAttribute = attributes.get("uid"); //$NON-NLS-1$
		List<Object> values = uidAttribute.getValues();

		assertTrue("Exactly one UID found for user", values.size() > 0); //$NON-NLS-1$
		if(values.size() > 0)
		{
			assertTrue("UID is string type", values.get(0) instanceof String); //$NON-NLS-1$
			String uid = (String)values.get(0);
			assertEquals("UID matches principal name", uid, data.getPrincipalAuthnIdentifier()); //$NON-NLS-1$
		}
	}
}
