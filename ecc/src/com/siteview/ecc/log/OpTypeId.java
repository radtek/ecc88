package com.siteview.ecc.log;

import org.zkoss.util.resource.Labels;

public class OpTypeId
{
	public static final OpTypeId	signin		= new OpTypeId("0", Labels.getLabel("SignIn"));
	public static final OpTypeId	add			= new OpTypeId("1", Labels.getLabel("Add"));
	public static final OpTypeId	edit		= new OpTypeId("2", Labels.getLabel("Editor"));
	public static final OpTypeId	del			= new OpTypeId("3", Labels.getLabel("Delete"));
	public static final OpTypeId	enable		= new OpTypeId("4", Labels.getLabel("Enable"));
	public static final OpTypeId	diable		= new OpTypeId("5", Labels.getLabel("Disable"));
	public static final OpTypeId	many_add	= new OpTypeId("6", Labels.getLabel("BatchAdd"));
	public static final OpTypeId	fast_add	= new OpTypeId("7", Labels.getLabel("QuicklyAdd"));
	public static final OpTypeId	paste		= new OpTypeId("8", Labels.getLabel("Paste"));
	public static final OpTypeId	confirm		= new OpTypeId("9", Labels.getLabel("EventIdentification"));

	public String id;
	public String name;
	
	public OpTypeId(String id, String name)
	{
		this.id= id;
		this.name= name;
	}
}
