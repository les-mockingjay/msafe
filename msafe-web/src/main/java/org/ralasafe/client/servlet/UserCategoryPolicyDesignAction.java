/**
 * Copyright (c) 2004-2011 Wang Jinbao(Julian Wong), http://www.ralasafe.com
 * Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php
 */
package org.ralasafe.client.servlet;

import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;

import org.ralasafe.common.RalasafeException;
import org.ralasafe.entitlement.entitle.UserCategory;
import org.ralasafe.entitlement.entitle.UserCategoryManager;

public class UserCategoryPolicyDesignAction extends AbstractPolicyDesignAction {
	
	public String getPolicyHandlerAttributeKey( HttpServletRequest req ) {
		int id=WebUtil.getIntParameter( req, "id", 0 );
		return "_$ralasafeUserCategory_" + id;
	}

	public AbstractPolicyDesignHandler createPolicyHandler( HttpServletRequest req ) {
		int id=WebUtil.getIntParameter( req, "id", 0 );
		
		UserCategoryManager manager=WebUtil.getUserCategoryManager( req );
		UserCategory uc=manager.getUserCategory( id );
		
		org.ralasafe.db.sql.xml.UserCategoryType xmlUc;
		try {
			xmlUc = org.ralasafe.db.sql.xml.UserCategory.unmarshal(new StringReader(uc.getXmlContent()));
		} catch (Exception e) {
			throw new RalasafeException(e);
		}
		
		return new UserCategoryPolicyDesignHandler( manager, xmlUc );
	}
}
