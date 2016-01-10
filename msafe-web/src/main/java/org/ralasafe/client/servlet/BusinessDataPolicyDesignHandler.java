/**
 * Copyright (c) 2004-2011 Wang Jinbao(Julian Wong), http://www.ralasafe.com
 * Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php
 */
package org.ralasafe.client.servlet;

import java.util.HashMap;
import java.util.Map;

import org.ralasafe.common.EntityExistException;
import org.ralasafe.common.SystemConstant;
import org.ralasafe.db.sql.xml.DefineVariable;
import org.ralasafe.db.sql.xml.ExprGroup;
import org.ralasafe.db.sql.xml.BusinessData;
import org.ralasafe.db.sql.xml.BusinessDataType;
import org.ralasafe.entitlement.entitle.ScriptTestResult;
import org.ralasafe.entitlement.entitle.BusinessDataManager;
import org.ralasafe.entitlement.script.AbstractPolicy;
import org.ralasafe.entitlement.script.ScriptFactory;
import org.ralasafe.forclientintf.user.User;

public class BusinessDataPolicyDesignHandler extends AbstractPolicyDesignHandler {
	private final BusinessDataManager manager;
	final BusinessDataType xml;
	
	public String getPolicyType() {
		return "businessData";
	}

	public String getManagePage() {
		return "./businessDataMng.rls";
	}

	public BusinessDataPolicyDesignHandler( BusinessDataManager manager,
			BusinessDataType xmlUc ) {
		this.manager=manager;
		this.xml=xmlUc;
	}

	public String getDesignPageTitle() {
		return "Design Business Data: " + xml.getName();
	}
	
	public String getRawPageTitle() {
		return "Edit Business Data manually: " + xml.getName();
	}
	
	public DefineVariable[] getVariables() {
		return xml.getDefineVariable();
	}

	public void deleteVariable( int id ) {
		xml.removeDefineVariableAt( id );
	}

	public void addVariable( DefineVariable var ) {
		xml.addDefineVariable( var );
	}

	public void updateVariable( int varIndex, DefineVariable var ) {
		xml.setDefineVariable( varIndex, var );
	}

	public ExprGroup getExprGroup() {
		return xml.getExprGroup();
	}

	public void save( int id ) throws EntityExistException {
		manager.updateBusinessData( id, (BusinessData) xml );
	}

	public void setDesignMode() {
		xml.setIsRawScript( false );
	}
	
	public void setRawMode() {
		xml.setIsRawScript( true );
	}

	public void setRawScript( String script ) {
		xml.getRawScript().setContent( script );
	}

	public String getRawScript() {
		return xml.getRawScript().getContent();
	}

	public ScriptTestResult run( User user, Object businessData, Map context ) {
		AbstractPolicy policy2=getPolicy();
		org.ralasafe.entitlement.script.BusinessData script=(org.ralasafe.entitlement.script.BusinessData) policy2;
		
		if( context==null ) {
			context=new HashMap();
		}
		context.put(SystemConstant.BUSINESS_DATA, businessData);
		
		return script.test( user, context, getQueryManager() );
	}

	public AbstractPolicy transferXml2Policy() {
		return ScriptFactory.getBusinessData( xml, getQueryManager() );
	}
}
