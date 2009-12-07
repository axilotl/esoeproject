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
 * Creation Date: 01/12/2006
 * 
 * Purpose: Tests the authentication servlet.
 */
package com.qut.middleware.spep.servlet;

import static com.qut.middleware.test.regression.Capture.capture;
import static com.qut.middleware.test.regression.Modify.modify;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

import com.qut.middleware.metadata.bean.EntityData;
import com.qut.middleware.metadata.bean.saml.SPEPRole;
import com.qut.middleware.metadata.bean.saml.TrustedESOERole;
import com.qut.middleware.metadata.processor.MetadataProcessor;
import com.qut.middleware.spep.SPEP;
import com.qut.middleware.spep.authn.AuthnProcessor;
import com.qut.middleware.spep.authn.AuthnProcessorData;
import com.qut.middleware.spep.exception.AuthenticationException;
import com.qut.middleware.test.regression.Capture;
import com.qut.middleware.test.regression.LineVectorOutputStream;
import com.qut.middleware.test.regression.Modify;

/** */
@SuppressWarnings({"nls"})
public class AuthenticationServletTest
{
	private AuthenticationServlet authenticationServlet;
	private SPEP spep;
	private ServletContext servletContext;
	private ServletConfig servletConfig;
	private AuthnProcessor authnProcessor;
	private MetadataProcessor metadata;
	private String tokenName;
	private String serviceHost;
	private String spepIdentifier;
	private String esoeIdentifier;
	private byte[] authnRequest;
	private String singleSignOnEndpoint;
	private String defaultRequestURL;
	private List<Object> mocked;
	private EntityData esoeEntityData;
	private TrustedESOERole esoeRole;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		this.mocked = new ArrayList<Object>();
		
		this.tokenName = "spep-session";
		this.serviceHost = "http://spep-dev.example.com";
		this.spepIdentifier = "spep-dev.example.com";
		this.esoeIdentifier = "esoe.example.com";
		this.authnRequest = new String("This is an authn request.").getBytes();
		this.singleSignOnEndpoint = "this is the single sign-on endpoint";
		this.defaultRequestURL = "http://spep-dev.example.com/default";
		
		this.authnProcessor = createMock(AuthnProcessor.class);
		this.mocked.add(this.authnProcessor);
		
		this.metadata = createMock(MetadataProcessor.class);
		this.mocked.add(this.metadata);
		this.esoeEntityData = createMock(EntityData.class);
		this.mocked.add(this.esoeEntityData);
		this.esoeRole = createMock(TrustedESOERole.class);
		this.mocked.add(this.esoeRole);
		expect(this.metadata.getEntityData(this.esoeIdentifier)).andReturn(this.esoeEntityData).anyTimes();
		expect(this.metadata.getEntityRoleData(this.esoeIdentifier, TrustedESOERole.class)).andReturn(this.esoeRole).anyTimes();
		expect(this.esoeEntityData.getRoleData(TrustedESOERole.class)).andReturn(this.esoeRole).anyTimes();
		expect(this.esoeRole.getSingleSignOnService((String)notNull())).andReturn(this.singleSignOnEndpoint).anyTimes();
		
		this.spep = createMock(SPEP.class);
		this.mocked.add(this.spep);
		expect(this.spep.getAuthnProcessor()).andReturn(this.authnProcessor).anyTimes();
		expect(this.spep.getMetadataProcessor()).andReturn(this.metadata).anyTimes();
		expect(this.spep.getTokenName()).andReturn(this.tokenName).anyTimes();
		expect(this.spep.getServiceHost()).andReturn(this.serviceHost).anyTimes();
		expect(this.spep.getDefaultUrl()).andReturn(this.defaultRequestURL).anyTimes();
		expect(this.spep.isStarted()).andReturn(Boolean.TRUE).anyTimes();
		expect(this.spep.getTrustedESOEIdentifier()).andReturn(this.esoeIdentifier).anyTimes();
		
		this.servletContext = createMock(ServletContext.class);
		this.mocked.add(this.servletContext);
		expect(this.servletContext.getAttribute((String)notNull())).andReturn(this.spep).anyTimes();
		
		this.servletConfig = createMock(ServletConfig.class);
		this.mocked.add(this.servletConfig);
		expect(this.servletConfig.getServletContext()).andReturn(this.servletContext).anyTimes();

		this.authenticationServlet = new AuthenticationServlet();
	}
	
	private void startMock()
	{
		for (Object o : this.mocked) replay(o);
	}
	
	private void endMock()
	{
		for (Object o : this.mocked) verify(o);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGet1() throws Exception
	{
		final LineVectorOutputStream outputStream = new LineVectorOutputStream();

		String redirectURL = "http://spep-dev.qut.edu.au/redirect";
		String base64RedirectURL = new String(Base64.encodeBase64(redirectURL.getBytes("UTF-16")));
		
		Modify<AuthnProcessorData> modifyAuthnProcessorData = new ModifyAuthnProcessorData( null, null, AuthenticationServletTest.this.authnRequest );
		
		this.authnProcessor.generateAuthnRequest(modify(modifyAuthnProcessorData));
		expectLastCall().anyTimes();

		startMock();
		
		this.authenticationServlet.init(this.servletConfig);
		
		ServletOutputStream out = new OutputStreamServletOutputStream( outputStream );
		
		HttpServletRequest request = createMock(HttpServletRequest.class);
		expect(request.getParameter("redirectURL")).andReturn(base64RedirectURL).anyTimes();
		
		HttpServletResponse response = createMock(HttpServletResponse.class);
		expect(response.getOutputStream()).andReturn(out).anyTimes();
		
		response.setStatus(200);
		response.setHeader("Content-Type", "text/html");
		expectLastCall().anyTimes();
		
		replay(request);
		replay(response);
		
		this.authenticationServlet.doGet(request, response);
		
		verify(request);
		verify(response);
		
		String base64Document = new String(Base64.encodeBase64(this.authnRequest));
		
		boolean foundURL = false, foundDocument = false;
		for (String line : outputStream.getLines())
		{
			if (line.contains(this.singleSignOnEndpoint))
			{
				foundURL = true;
			}
			if (line.contains(base64Document))
			{
				foundDocument = true;
			}
			
			//System.out.println(line);
		}
		
		assertTrue("Didn't find URL in generated HTML", foundURL);
		assertTrue("Didn't find authnRequest in generated HTML", foundDocument);
		
		endMock();
	}

	/**
	 * @throws Exception
	 */
	@Test(expected = ServletException.class)
	public void testGet2() throws Exception
	{
		final LineVectorOutputStream outputStream = new LineVectorOutputStream();

		String redirectURL = "http://spep-dev.qut.edu.au/redirect";
		String base64RedirectURL = new String(Base64.encodeBase64(redirectURL.getBytes("UTF-8")));
		
		this.authnProcessor.generateAuthnRequest((AuthnProcessorData)notNull());
		expectLastCall().andThrow(new AuthenticationException("test error")).anyTimes();
		
		startMock();
		
		this.authenticationServlet.init(this.servletConfig);
		
		ServletOutputStream out = new OutputStreamServletOutputStream( outputStream );
		
		HttpServletRequest request = createMock(HttpServletRequest.class);
		expect(request.getParameter("redirectURL")).andReturn(base64RedirectURL).anyTimes();
		
		HttpServletResponse response = createMock(HttpServletResponse.class);
		expect(response.getOutputStream()).andReturn(out).anyTimes();
		
		response.setStatus(200);
		expectLastCall().anyTimes();
		
		replay(request);
		replay(response);
		
		this.authenticationServlet.doGet(request, response);
		
		verify(request);
		verify(response);
		
		endMock();
	}


	/**
	 * @throws Exception
	 */
	@Test(expected = ServletException.class)
	public void testPost1a() throws Exception
	{
		final LineVectorOutputStream outputStream = new LineVectorOutputStream();
		
		startMock();
		
		this.authenticationServlet.init(this.servletConfig);
		
		// Empty saml response
		String samlResponse = null;
		
		ServletOutputStream out = new OutputStreamServletOutputStream( outputStream );
		
		HttpServletRequest request = createMock(HttpServletRequest.class);
		expect(request.getParameter("SAMLResponse")).andReturn(samlResponse).anyTimes();
		
		HttpServletResponse response = createMock(HttpServletResponse.class);
		expect(response.getOutputStream()).andReturn(out).anyTimes();
		
		response.setStatus(200);
		expectLastCall().anyTimes();
		
		replay(request);
		replay(response);
		
		this.authenticationServlet.doPost(request, response);
		
		verify(request);
		verify(response);
		
		endMock();
	}

	/**
	 * @throws Exception
	 */
	@Test(expected = ServletException.class)
	public void testPost1b() throws Exception
	{
		final LineVectorOutputStream outputStream = new LineVectorOutputStream();
		
		// Cause authn to fail validation
		this.authnProcessor.processAuthnResponse((AuthnProcessorData)notNull());
		expectLastCall().andThrow(new AuthenticationException("test exception"));
		
		startMock();
		
		this.authenticationServlet.init(this.servletConfig);
		
		String samlResponse = new String(Base64.encodeBase64("some response document".getBytes("UTF-8")));
		
		ServletOutputStream out = new OutputStreamServletOutputStream( outputStream );
		
		HttpServletRequest request = createMock(HttpServletRequest.class);
		expect(request.getParameter("SAMLResponse")).andReturn(samlResponse).anyTimes();
		
		HttpServletResponse response = createMock(HttpServletResponse.class);
		
		replay(request);
		replay(response);
		
		this.authenticationServlet.doPost(request, response);
		
		verify(request);
		verify(response);
		
		endMock();
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testPost2a() throws Exception
	{
		final LineVectorOutputStream outputStream = new LineVectorOutputStream();
		final String sessionID = "9809283409182304981234-923-501209348091234";
		final String base64RequestURL = new String(Base64.encodeBase64(this.defaultRequestURL.getBytes("UTF-8")));
		
		Modify<AuthnProcessorData> modifyAuthnProcessorData = new ModifyAuthnProcessorData( sessionID, null, null );
		
		Capture<Cookie> captureCookie = new Capture<Cookie>();
		
		this.authnProcessor.processAuthnResponse(modify(modifyAuthnProcessorData));
		expectLastCall().anyTimes();
		
		startMock();
		
		this.authenticationServlet.init(this.servletConfig);
		
		String samlResponse = new String(Base64.encodeBase64("some response document".getBytes("UTF-8")));
		
		ServletOutputStream out = new OutputStreamServletOutputStream( outputStream );
		
		HttpServletRequest request = createMock(HttpServletRequest.class);
		expect(request.getParameter("SAMLResponse")).andReturn(samlResponse).anyTimes();
		
		HttpServletResponse response = createMock(HttpServletResponse.class);
		response.addCookie(capture(captureCookie));
		expectLastCall().once();
		// Make sure we get redirected to the default URL
		response.sendRedirect(this.defaultRequestURL);
		expectLastCall().once();
		
		replay(request);
		replay(response);
		
		this.authenticationServlet.doPost(request, response);
		
		verify(request);
		verify(response);
		
		Cookie spepCookie = null;
		for (Cookie cookie : captureCookie.getCaptured())
		{
			if (cookie.getName().equals(this.tokenName))
			{
				spepCookie = cookie;
				break;
			}
		}
		
		assertNotNull(spepCookie);
		assertEquals(this.tokenName, spepCookie.getName());
		assertEquals(sessionID, spepCookie.getValue());
		
		endMock();
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testPost2b() throws Exception
	{
		final LineVectorOutputStream outputStream = new LineVectorOutputStream();
		final String sessionID = "9809283409182304981234-923-501209348091234";
		final String requestURL = "http://lol.request.url/somepage.jsp";
		final String base64RequestURL = new String(Base64.encodeBase64(requestURL.getBytes("UTF-8")));
		
		Modify<AuthnProcessorData> modifyAuthnProcessorData = new ModifyAuthnProcessorData( sessionID, base64RequestURL, null );
		
		Capture<Cookie> captureCookie = new Capture<Cookie>();
		
		this.authnProcessor.processAuthnResponse(modify(modifyAuthnProcessorData));
		expectLastCall().anyTimes();
		
		startMock();
		
		this.authenticationServlet.init(this.servletConfig);
		
		String samlResponse = new String(Base64.encodeBase64("some response document".getBytes("UTF-8")));
		
		ServletOutputStream out = new OutputStreamServletOutputStream( outputStream );
		
		HttpServletRequest request = createMock(HttpServletRequest.class);
		expect(request.getParameter("SAMLResponse")).andReturn(samlResponse).anyTimes();
		
		HttpServletResponse response = createMock(HttpServletResponse.class);
		response.addCookie(capture(captureCookie));
		expectLastCall().once();
		// Make sure we get redirected to the session URL
		response.sendRedirect(requestURL);
		expectLastCall().once();
		
		
		replay(request);
		replay(response);
		
		this.authenticationServlet.doPost(request, response);
		
		verify(request);
		verify(response);
		
		Cookie spepCookie = null;
		for (Cookie cookie : captureCookie.getCaptured())
		{
			if (cookie.getName().equals(this.tokenName))
			{
				spepCookie = cookie;
				break;
			}
		}
		
		assertNotNull(spepCookie);
		assertEquals(this.tokenName, spepCookie.getName());
		assertEquals(sessionID, spepCookie.getValue());
		
		endMock();
	}
}

class OutputStreamServletOutputStream extends ServletOutputStream
{
	private OutputStream out;
	
	public OutputStreamServletOutputStream( OutputStream out )
	{
		this.out = out;
	}
	
	@Override
	public void write(int b) throws IOException
	{
		this.out.write(b);
	}
}

class ModifyAuthnProcessorData extends Modify<AuthnProcessorData>
{
	private String sessionID;
	private String base64RequestURL;
	private byte[] requestDocument;

	public ModifyAuthnProcessorData(String sessionID, String base64RequestURL, byte[] requestDocument)
	{
		this.sessionID = sessionID;
		this.base64RequestURL = base64RequestURL;
		this.requestDocument = requestDocument;
	}

	@Override
	public void operate(AuthnProcessorData object)
	{
		object.setSessionID(this.sessionID);
		object.setRequestURL(this.base64RequestURL);
		object.setRequestDocument(this.requestDocument);
	}
	
}

