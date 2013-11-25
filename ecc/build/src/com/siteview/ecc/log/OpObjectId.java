package com.siteview.ecc.log;

import org.zkoss.util.resource.Labels;

public class OpObjectId
{
	public static final OpObjectId	login				= new OpObjectId("0", Labels.getLabel("SignIn"));
	public static final OpObjectId	se					= new OpObjectId("1", "SE");
	public static final OpObjectId	group				= new OpObjectId("2", Labels.getLabel("Group"));
	public static final OpObjectId	entity				= new OpObjectId("3", Labels.getLabel("Equipment"));
	public static final OpObjectId	monitor				= new OpObjectId("4", Labels.getLabel("Monitor"));
	public static final OpObjectId	general_set			= new OpObjectId("5", Labels.getLabel("BasicSetting"));
	public static final OpObjectId	customer_name		= new OpObjectId("6", Labels.getLabel("CustomerName"));
	public static final OpObjectId	server_name			= new OpObjectId("7", Labels.getLabel("MonitoringServerName"));
	public static final OpObjectId	alert_rule			= new OpObjectId("8", Labels.getLabel("AlarmRules"));
	public static final OpObjectId	tupo_view			= new OpObjectId("9", Labels.getLabel("TopologyView"));
	public static final OpObjectId	syslog_set			= new OpObjectId("10", Labels.getLabel("SysLogSettings"));
	public static final OpObjectId	user_manage			= new OpObjectId("11", Labels.getLabel("UserManagement"));
	public static final OpObjectId	mail_set			= new OpObjectId("12", Labels.getLabel("MailSettings"));
	public static final OpObjectId	time_task			= new OpObjectId("13", Labels.getLabel("TimeSlotTaskPlan"));
	public static final OpObjectId	absolute_task		= new OpObjectId("14", Labels.getLabel("AbsoluteTimeTaskPlan"));
	public static final OpObjectId	sms_set				= new OpObjectId("15", Labels.getLabel("MessageSettings"));
	public static final OpObjectId	topn_report			= new OpObjectId("16", Labels.getLabel("TopNReport"));
	public static final OpObjectId	statistic_report	= new OpObjectId("17", Labels.getLabel("StatisticalReport"));
	public static final OpObjectId	duty_set			= new OpObjectId("18", Labels.getLabel("DutyTableConfiguration"));
	public static final OpObjectId	virtual_groupnode	= new OpObjectId("19", Labels.getLabel("VirtualGroup"));
	public static final OpObjectId	relative_task		= new OpObjectId("20", Labels.getLabel("RelativeTimeTaskPlan"));
	public static final OpObjectId  alert_strategy      = new OpObjectId("21", Labels.getLabel("AlarmStrategy"));
	public static final OpObjectId  monitor_browser     = new OpObjectId("22", Labels.getLabel("MonitorBrowsing"));
	public static final OpObjectId  monitor_set         = new OpObjectId("23", Labels.getLabel("MonitorSettings"));
	public static final OpObjectId  email_template      = new OpObjectId("24", Labels.getLabel("EmailTemplate"));
	public static final OpObjectId  message_template    = new OpObjectId("25", Labels.getLabel("SMSTemplates"));
	public static final OpObjectId  status_report       = new OpObjectId("26", Labels.getLabel("StateStatisticalReport"));//"ReportStatus","状态统计报告","m_ShowStatusReport");//qimin.xiong
    public static final OpObjectId  system_diagnosis    = new OpObjectId("27", Labels.getLabel("SystemDiagnosis"));//"SystemDiagnosis", "系统诊断", "m_system_diagnosis"
    public static final OpObjectId  ipchoose		    = new OpObjectId("28", Labels.getLabel("IPAddress"));//"SystemDiagnosis", "系统诊断", "m_system_diagnosis"
    public static final OpObjectId  topn_set    		= new OpObjectId("29", Labels.getLabel("TopNSettings"));
    public String id;
	public String name;
	
	public OpObjectId(String id, String name)
	{
		this.id= id;
		this.name= name;
	}
}
