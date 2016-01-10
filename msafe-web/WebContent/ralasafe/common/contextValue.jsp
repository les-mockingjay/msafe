<%
/**
 * Copyright (c) 2004-2011 Wang Jinbao(Julian Wong), http://www.ralasafe.com
 * Licensed under the MIT license: http://www.opensource.org/licenses/mit-license.php
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.ralasafe.db.sql.xml.DefineVariable" %>
<%@ page import="org.ralasafe.client.servlet.AbstractPolicyDesignHandler" %>
<%@ page import="org.ralasafe.db.sql.xml.ContextValue" %>

<%
org.ralasafe.util.I18N i18n=org.ralasafe.util.I18N.getWebInstance( request );

DefineVariable var=(DefineVariable) request.getAttribute( "variable" );
String oper="addVariable";
String name="";
String key="";
String varIndex="-1";
String id=request.getParameter( "id" );

if( var!=null ) {
	ContextValue value=var.getContextValue();
	
	varIndex=request.getParameter( "index" );
	oper="updateVariable";
	key=value.getKey();
	name=var.getName();
}
%>


	<input type="hidden" name="oper" value="<%=oper%>"/>
	<input type="hidden" name="id" value="<%=id%>"/>
	<input type="hidden" name="type" value="contextValue"/>
	<input type="hidden" name="index" value="<%=varIndex%>"/>
	<label><%=i18n.say( "Variable_name" )%></label>
	<input type="text" name="name" value="<%=name%>" class="required"/>
	<br/>
	<label><%=i18n.say( "Context_key" )%></label>
	<input type="text" name="key" value="<%=key%>" class="required"/>