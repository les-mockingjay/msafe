/**
 * Copyright (c) 2004-2011 Wang Jinbao(Julian Wong), http://www.ralasafe.com
 * Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php
 */
package org.ralasafe.client.servlet;

import javax.servlet.http.HttpServletRequest;

import org.ralasafe.entitlement.entitle.BusinessDataManager;

public class BusinessDataMngAction extends AbstractTreeAction{
	public AbstractTreeHandler createTreeHandler(HttpServletRequest req){
		BusinessDataManager  manager=WebUtil.getBusinessDataManager(req);
		return new BusinessDataTreeHandler(manager);
	}
}
