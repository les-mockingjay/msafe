/**
 * Copyright (c) 2004-2011 Wang Jinbao(Julian Wong), http://www.ralasafe.com
 * Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php
 */
package org.ralasafe.client.servlet;

import javax.servlet.http.HttpServletRequest;

import org.ralasafe.entitlement.entitle.BusinessData;
import org.ralasafe.entitlement.entitle.BusinessDataManager;

public class BusinessDataPolicyAction extends AbstractPolicyAction {
	//private static final Log log=LogFactory.getLog( BusinessDataPolicyAction.class );
	
	
	public boolean isRawScript( HttpServletRequest req ) {
		int id=WebUtil.getIntParameter( req, "id", 0 );
		
		BusinessDataManager manager=WebUtil.getBusinessDataManager( req );
		BusinessData uc=manager.getBusinessData( id );
		
		org.ralasafe.entitlement.script.BusinessData script=uc.getScriptBusinessData();
		return script.isRawScript();
	}
}
