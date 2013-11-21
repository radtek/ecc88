/**
 * 
 */
package com.siteview.ecc.log.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.util.resource.Labels;

/**
 * @author yuandong s[0] = m.get(i).get("_UserID"); s[1] =
 *         m.get(i).get("_OperateObjName"); s[2] = m.get(i).get("_OperateType");
 *         s[3] = m.get(i).get("_OperateTime"); s[4] =
 *         m.get(i).get("_OperateObjInfo");
 */
public class LogValueBean  {

	
	private String userId;

	private String operateObjName;
	private String operateType;
	private String operateTime;
	private String operateObjInfo;

	private Map<String, String> m_obj = new HashMap<String, String>();
	private Map<String, String> m_type = new HashMap<String, String>();

	public LogValueBean(List<Map<String, String>> a, int i) {
		
		//以下代号定义来自 mmc 版本   UserLogMmcListView.cs  60行  至 90行  //修改部分和 mmc 保持一致
		
		m_obj.put("0", Labels.getLabel("SignIn"));            
		m_obj.put("1", "SE");              
		m_obj.put("2", Labels.getLabel("Group"));              
		m_obj.put("3", Labels.getLabel("Equipment"));            
		m_obj.put("4", Labels.getLabel("Monitor"));          
		m_obj.put("5", Labels.getLabel("BasicSetting"));        
		m_obj.put("6", Labels.getLabel("CustomerName"));        
		m_obj.put("7", Labels.getLabel("MonitoringServerName"));  
		m_obj.put("8", Labels.getLabel("AlarmRules"));        
		m_obj.put("9", Labels.getLabel("TopologyView"));        
		m_obj.put("10", Labels.getLabel("SysLogSettings"));     
		m_obj.put("11", Labels.getLabel("UserManagement"));       
		m_obj.put("12", Labels.getLabel("MailSettings"));       
		m_obj.put("13", Labels.getLabel("TimeSlotTaskPlan")); 
		m_obj.put("14", Labels.getLabel("AbsoluteTimeTaskPlan"));
		m_obj.put("15", Labels.getLabel("MessageSettings"));       
		m_obj.put("16", Labels.getLabel("TopNReport"));       
		m_obj.put("17", Labels.getLabel("StatisticalReport"));       
		m_obj.put("18", Labels.getLabel("DutyTableConfiguration"));       
		m_obj.put("20", Labels.getLabel("RelativeTimeTaskPlan")); 
		m_obj.put("22", Labels.getLabel("MonitorBrowsing")); 		
		m_obj.put("23", Labels.getLabel("MonitorSettings")); 
		m_obj.put("24", Labels.getLabel("EmailTemplate")); 
		m_obj.put("25", Labels.getLabel("SMSTemplates")); 
		m_obj.put("28", Labels.getLabel("IPAddress")); 
		
		m_type.put("0", Labels.getLabel("SignIn"));
		m_type.put("1", Labels.getLabel("Add"));
		m_type.put("2", Labels.getLabel("Editor"));
		m_type.put("3", Labels.getLabel("Delete"));
		m_type.put("4", Labels.getLabel("Enable"));
		m_type.put("5", Labels.getLabel("Disable"));
		m_type.put("6", Labels.getLabel("BatchAdd"));
		m_type.put("7", Labels.getLabel("QuicklyAdd"));
		m_type.put("8", Labels.getLabel("Paste"));
		m_type.put("9", Labels.getLabel("EventIdentification"));

		
		userId = a.get(i).get("_UserID");
		operateObjName = m_obj.get(a.get(i).get("_OperateObjName"));
		operateType = m_type.get(a.get(i).get("_OperateType"));
		operateTime = a.get(i).get("_OperateTime");
		operateObjInfo = a.get(i).get("_OperateObjInfo");

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOperateObjName() {
		return operateObjName;
	}

	public void setOperateObjName(String operateObjName) {
		this.operateObjName = operateObjName;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateObjInfo() {
		return operateObjInfo;
	}

	public void setOperateObjInfo(String operateObjInfo) {
		this.operateObjInfo = operateObjInfo;
	}

	public Map<String, String> getM_obj() {
		return m_obj;
	}

	public void setM_obj(Map<String, String> m_obj) {
		this.m_obj = m_obj;
	}

	public Map<String, String> getM_type() {
		return m_type;
	}

	public void setM_type(Map<String, String> m_type) {
		this.m_type = m_type;
	}


	/*public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		LogValueList temp = (LogValueList) arg0;
		return this.operateTime.compareTo(temp.operateTime);
	}*/

}
