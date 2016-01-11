/**
 * Copyright (c) 2004-2011 Wang Jinbao(Julian Wong), http://www.ralasafe.com
 * Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php
 */
package org.ralasafe.client.servlet;

import javax.servlet.http.HttpServletRequest;

import org.ralasafe.entitlement.entitle.UserCategory;
import org.ralasafe.entitlement.entitle.UserCategoryManager;

public class UserCategoryPolicyAction extends AbstractPolicyAction {
	//private static final Log log=LogFactory.getLog( UserCategoryPolicyServlet.class );
	
	
	public boolean isRawScript( HttpServletRequest req ) {
		int id=WebUtil.getIntParameter( req, "id", 0 );
		
		UserCategoryManager manager=WebUtil.getUserCategoryManager( req );
		UserCategory uc=manager.getUserCategory( id );
		
		org.ralasafe.entitlement.script.UserCategory script=uc.getScriptUserCategory();
		return script.isRawScript();
	}
}
