package com.siteview.ecc.alert;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import org.zkoss.zul.event.PagingEvent;

import com.siteview.base.data.IniFile;
import com.siteview.base.data.UserRightId;
import com.siteview.base.manage.View;
import com.siteview.ecc.alert.control.TooltipPopup;
import com.siteview.ecc.alert.dao.Constand;
import com.siteview.ecc.alert.dao.bean.AccessControl;
import com.siteview.ecc.alert.dao.bean.AlertLogItem;
import com.siteview.ecc.alert.dao.bean.AlertLogQueryCondition;
import com.siteview.ecc.alert.dao.bean.AlertRuleQueryCondition;
import com.siteview.ecc.alert.dao.bean.BaseAlert;
import com.siteview.ecc.alert.dao.bean.EmailAlert;
import com.siteview.ecc.alert.dao.bean.SMSAlert;
import com.siteview.ecc.alert.dao.bean.ScriptAlert;
import com.siteview.ecc.alert.dao.bean.SoundAlert;
import com.siteview.ecc.alert.dao.type.AlertState;
import com.siteview.ecc.alert.dao.type.AlertTimes;
import com.siteview.ecc.alert.dao.type.AlertType;
import com.siteview.ecc.alert.util.BaseTools;
import com.siteview.ecc.alert.util.DictionaryFactory;
import com.siteview.ecc.report.common.SelectableListheader;
import com.siteview.ecc.util.Message;
import com.siteview.ecc.util.Toolkit;

public class AlertRuleView extends AbstractWindow {
	private static final long serialVersionUID = 7836913249749751601L;
	private static String EmailAlert_TargetUrl = "/main/alert/emailalert.zul";
	private static String SmsAlert_TargetUrl = "/main/alert/smsalert.zul";
	private static String ScriptAlert_TargetUrl = "/main/alert/scriptalert.zul";
	private static String SoundAlert_TargetUrl = "/main/alert/soundalert.zul";
	private boolean editFlag = true;

	public Window getAlertRuleTest(){
		return (Window)BaseTools.getComponentById(this, "alertRuleTest");
	}
	
	public Listbox getAlertRuleListbox() {
		return (Listbox) BaseTools.getComponentById(this, "alert_rule_list");
	}

	public Listbox getAlertLogListbox() {
		return (Listbox) BaseTools.getComponentById(this, "alert_log_list");
	}

	public Div getAddAlertDiv() {
		return (Div) BaseTools.getComponentById(this, "AddAlertDiv");
	}
	
	public Menupopup getAddAlertPopupMenupopup() {
		return (Menupopup) BaseTools.getComponentById(this, "AddAlertPopup");
	}
	public Menuitem getAddEmailMenuitem() {
		return (Menuitem) BaseTools.getComponentById(this, "addEmailAlert");
	}

	public Menuitem getAddSmsMenuitem() {
		return (Menuitem) BaseTools.getComponentById(this, "addSmsAlert");
	}

	public Menuitem getAddScriptMenuitem() {
		return (Menuitem) BaseTools.getComponentById(this, "addScriptAlert");
	}

	public Menuitem getAddSoundMenuitem() {
		return (Menuitem) BaseTools.getComponentById(this, "addSoundAlert");
	}

	public Button getAddButton(){
		return (Button) BaseTools.getComponentById(this, "add");
	}
	
	public Button getDeleteButton() {
		return (Button) BaseTools.getComponentById(this, "delete");
	}

	public Button getEnableButton() {
		return (Button) BaseTools.getComponentById(this, "enable");
	}

	public Button getDisableButton() {
		return (Button) BaseTools.getComponentById(this, "disable");
	}

	public TooltipPopup getDetailPopup(BaseAlert basealert) {
		TooltipPopup tooltippopup = new TooltipPopup();
		tooltippopup.onCreate();
		tooltippopup.setStyle("border:none;color:#FFFFFF;background-color:#717171");
		this.getTooltiptext(tooltippopup, basealert);
		tooltippopup.setParent(this);
		return tooltippopup;
	}

	public TooltipPopup getLogPopup(AlertLogItem alertlogitem) {
		TooltipPopup tooltippopup = new TooltipPopup();
		tooltippopup.onCreate();
		tooltippopup.setStyle("border:none;color:#FFFFFF;background-color:#717171");
		this.getTooltiptext(tooltippopup, alertlogitem);
		tooltippopup.setParent(this);
		return tooltippopup;
	}

	class AddEmailClickListener implements org.zkoss.zk.ui.event.EventListener {
		AlertRuleView view = null;

		public AddEmailClickListener(AlertRuleView view) {
			this.view = view;
		}

		@Override
		public void onEvent(Event event) throws Exception {
			try {
				final Window win = (Window) Executions.createComponents(EmailAlert_TargetUrl, null, null);
				win.setVariable(COMMAND_VAR, CMD_ADD, true);
				win.doModal();
				this.view.init();
			} catch (Exception e) {
				Message.showError(e.getMessage());
			}
		}

	}

	class AddScriptClickListener implements org.zkoss.zk.ui.event.EventListener {
		AlertRuleView view = null;

		public AddScriptClickListener(AlertRuleView view) {
			this.view = view;
		}

		@Override
		public void onEvent(Event event) throws Exception {
			try {
				final Window win = (Window) Executions.createComponents(ScriptAlert_TargetUrl, null, null);
				win.setVariable(COMMAND_VAR, CMD_ADD, true);
				win.doModal();
				this.view.init();
			} catch (Exception e) {
				Message.showError(e.getMessage());
			}

		}

	}

	class AddSmsClickListener implements org.zkoss.zk.ui.event.EventListener {
		AlertRuleView view = null;

		public AddSmsClickListener(AlertRuleView view) {
			this.view = view;
		}

		@Override
		public void onEvent(Event event) throws Exception {
			try {
				final Window win = (Window) Executions.createComponents(SmsAlert_TargetUrl, null, null);
				win.setVariable(COMMAND_VAR, CMD_ADD, true);
				win.doModal();
				this.view.init();
			} catch (Exception e) {
				Message.showError(e.getMessage());
			}
		}

	}

	class AddSoundClickListener implements org.zkoss.zk.ui.event.EventListener {
		AlertRuleView view = null;

		public AddSoundClickListener(AlertRuleView view) {
			this.view = view;
		}

		@Override
		public void onEvent(Event event) throws Exception {
			try {
				final Window win = (Window) Executions.createComponents(SoundAlert_TargetUrl, null, null);
				win.setVariable(COMMAND_VAR, CMD_ADD, true);
				win.doModal();
				this.view.init();
			} catch (Exception e) {
				Message.showError(e.getMessage());
			}
		}

	}

	class DeleteClickListener implements org.zkoss.zk.ui.event.EventListener {
		AlertRuleView view = null;

		public DeleteClickListener(AlertRuleView view) {
			this.view = view;
		}

		@Override
		public void onEvent(Event event) throws Exception {
			try {
				checkSelectItems();
				int ret = Message.showQuestion(Labels.getLabel("SureDeleteSelectedRecords?"));
				if (ret == Messagebox.CANCEL)
					return;
				Listbox listbox = this.view.getAlertRuleListbox();
				for (Object obj : listbox.getItems()) {
					if (obj instanceof Listitem) {
						Listitem item = (Listitem) obj;
						if (item.isSelected()) {
							BaseAlert basealert = (BaseAlert) item.getValue();
							if (basealert != null) {
								DictionaryFactory.getIAlertDao().deleteAlert(new AccessControl(), basealert);
//								View view = Toolkit.getToolkit().getSvdbView(Executions.getCurrent().getDesktop());
//								String loginname = view.getLoginName();
//								String minfo = loginname + "��" + OpObjectId.alert_rule.name + "�н�����  "+OpTypeId.del.name+""+OpObjectId.alert_rule.name+"  ����";
//								AppendOperateLog.addOneLog(loginname, minfo, OpTypeId.del, OpObjectId.alert_rule);
							}
							
						}
					}
				}
				this.view.init();//�����еı����Ĺ���ɾ�� ֮�� �ͻ��������
				if(listbox.getItems().size() != 0){
					this.view.getAlertRuleListbox().setSelectedIndex(0);
					refreshAlertLogListbox();					
				}else{
					refreshAlertLogListboxWithNoSelectedItem();
				}
			} catch (Exception e) {
				Message.showError(e.getMessage());
			}
		}

	}

	class EnableClickListener implements org.zkoss.zk.ui.event.EventListener {
		AlertRuleView view = null;

		public EnableClickListener(AlertRuleView view) {
			this.view = view;
		}

		@Override
		public void onEvent(Event event) throws Exception {
			try {
				checkSelectItems();
				Listbox listbox = this.view.getAlertRuleListbox();
				for (Object obj : listbox.getItems()) {
					if (obj instanceof Listitem) {
						Listitem item = (Listitem) obj;
						if (item.isSelected()) {
							BaseAlert basealert = (BaseAlert) item.getValue();
							if (basealert != null) {
								basealert.setState(AlertState.Enable);
								DictionaryFactory.getIAlertDao().updateAlert(new AccessControl(), basealert, false);
//								View view = Toolkit.getToolkit().getSvdbView(Executions.getCurrent().getDesktop());
//								String loginname = view.getLoginName();
//								String minfo = loginname + "��" + OpObjectId.alert_rule.name + "�н�����"+OpTypeId.enable.name+"  "+OpObjectId.alert_rule.name+"  ����";
//								AppendOperateLog.addOneLog(loginname, minfo, OpTypeId.enable, OpObjectId.alert_rule);

							}
						}
					}
				}
				this.view.init();
			} catch (Exception e) {
				Message.showError(e.getMessage());
			}
		}

	}

	class DisableClickListener implements org.zkoss.zk.ui.event.EventListener {
		AlertRuleView view = null;

		public DisableClickListener(AlertRuleView view) {
			this.view = view;
		}

		@Override
		public void onEvent(Event event) throws Exception {
			try {
				checkSelectItems();
				Listbox listbox = this.view.getAlertRuleListbox();
				for (Object obj : listbox.getItems()) {
					if (obj instanceof Listitem) {
						Listitem item = (Listitem) obj;
						if (item.isSelected()) {
							BaseAlert basealert = (BaseAlert) item.getValue();
							if (basealert != null) {
								basealert.setState(AlertState.Disable);
								DictionaryFactory.getIAlertDao().updateAlert(new AccessControl(), basealert, false);
//								View view = Toolkit.getToolkit().getSvdbView(Executions.getCurrent().getDesktop());
//								String loginname = view.getLoginName();
//								String minfo = loginname + "��" + OpObjectId.alert_rule.name + "�н�����  "+OpTypeId.diable.name+"  "+OpObjectId.alert_rule.name+"  ����";
//								AppendOperateLog.addOneLog(loginname, minfo, OpTypeId.diable, OpObjectId.alert_rule);

							}
						}
					}
				}
				this.view.init();
			} catch (Exception e) {
				Message.showError(e.getMessage());
			}
		}

	}

	private void checkSelectItems() throws Exception {
		Listbox listbox = this.getAlertRuleListbox();
		for (Object obj : listbox.getItems()) {
			if (obj instanceof Listitem) {
				Listitem item = (Listitem) obj;
				if (item.isSelected()) {
					return;
				}
			}
		}
		throw new Exception(Labels.getLabel("ObjectSelectOperation"));

	}

	private void initLargeListbox(AlertLogQueryCondition queryCondition) throws Exception {
//		 *******************modify by di.tang 20090609
		LargeListbox ll = new LargeListbox(this);
		ll.setTotalSize(288);
		ll.redraw(queryCondition, 0, Constand.recordecount);
		return;
	}

	class AlertRuleListboxSelectListener implements org.zkoss.zk.ui.event.EventListener {
		AlertRuleView view = null;

		public AlertRuleListboxSelectListener(AlertRuleView view) {
			this.view = view;
		}

		@Override
		public void onEvent(Event event) throws Exception {
			try {
				Set itemSet = getAlertRuleListbox().getSelectedItems();
				if(itemSet.size()>1) return;
				Listitem item = getAlertRuleListbox().getSelectedItem();
				if (item != null) {
					Object value = item.getValue();
					if (!(value instanceof BaseAlert))
						return;
					BaseAlert basealert = (BaseAlert) value;
//					Toolkit.getToolkit().setCookie("alert_rul_imfomation", basealert.getId(), 99999999);
					AlertLogQueryCondition queryCondition = new AlertLogQueryCondition();
					queryCondition.setAlertIndex(basealert.getId());
					queryCondition.setAlertType(basealert.getType());

					queryCondition.setEndTime(new Date());

					Calendar calStart = Calendar.getInstance();
					calStart.setTime(new Date());
					calStart.add(Calendar.HOUR, -24);
					queryCondition.setStartTime(calStart.getTime());

					queryCondition.setLimitIndex(true);
					queryCondition.setLimitTime(true);
					queryCondition.setLimitType(true);
					// *******************modify by di.tang 20090609
					initLargeListbox(queryCondition);
					return;
					/*ListBean result = DictionaryFactory.getIAlertLogDao().queryAlertLog(new AccessControl(), queryCondition);

					BaseTools.clear(this.view.getAlertLogListbox());
					AlertLogItem alertLogItem = new AlertLogItem();
					for (HashMap<String, String> map : result.getList()) {
						alertLogItem.init(map);
						Listitem listitem = BaseTools.setRow(this.view.getAlertLogListbox(), alertLogItem, DATE_TO_STRING
								.format(alertLogItem.getAlertTime()), alertLogItem.getAlertName(), alertLogItem.getEntityName(),
								alertLogItem.getMonitorName(), alertLogItem.getAlertType().getComponent(), alertLogItem.getAlertReceiver(),
								alertLogItem.getAlertStatus().getComponent());
						listitem.setTooltip(getLogPopup(alertLogItem));
					}
					if (!result.isSuccess())
						throw new Exception(result.getMessage());*/
				}
			} catch (Exception e) {
				Message.showError(e.getMessage());
			}
		}
	}

	class EditAlertMenuClickListener implements org.zkoss.zk.ui.event.EventListener {
		private AlertRuleView view = null;
		private BaseAlert alertItem = null;

		public EditAlertMenuClickListener(AlertRuleView view, BaseAlert alertItem) {
			this.view = view;
			this.alertItem = alertItem;
		}

		@Override
		public void onEvent(Event event) throws Exception {

			View userView = Toolkit.getToolkit().getSvdbView(Executions.getCurrent().getDesktop());
			if(editFlag){
				try {
					if (getDesktop().getSession().getAttribute("CurrentWindow")==null){
						Window win = null;
						if (alertItem instanceof EmailAlert) {
							win = (Window) Executions.createComponents(EmailAlert_TargetUrl, null, null);
							win.setTitle(Labels.getLabel("EditEmailAlarm"));
						} else if (alertItem instanceof ScriptAlert) {
							win = (Window) Executions.createComponents(ScriptAlert_TargetUrl, null, null);
							win.setTitle(Labels.getLabel("EditScriptAlarm"));
						} else if (alertItem instanceof SMSAlert) {
							win = (Window) Executions.createComponents(SmsAlert_TargetUrl, null, null);
							win.setTitle(Labels.getLabel("EditSMSAlarm"));
						} else if (alertItem instanceof SoundAlert) {
							win = (Window) Executions.createComponents(SoundAlert_TargetUrl, null, null);
							win.setTitle(Labels.getLabel("EditVoiceAlarm"));
						}
						if (win != null) {
							getDesktop().getSession().setAttribute("CurrentWindow", win);
							win.setVariable(COMMAND_VAR, CMD_EDIT, true);
							win.setVariable("alertinformation", alertItem, true);
							win.getDesktop().getExecution().setAttribute("all_selected_ids", alertItem.getTarget());
							win.doModal();
							getDesktop().getSession().removeAttribute("CurrentWindow");
							this.view.init();
						}
					}
				} catch (Exception e) {
					Message.showError(e.getMessage());
				}
			}else
			{
				try{
					Message.showInfo(Labels.getLabel("User:")+userView.getLoginName()+Labels.getLabel("NoPermissionEditAlarm"));
					return;
				}catch(Exception e){}
			}
			
		}

	}

	public void onCreate() throws Exception {
		try {

			this.getAddEmailMenuitem().addEventListener(Events.ON_CLICK, new AddEmailClickListener(this));
			this.getAddScriptMenuitem().addEventListener(Events.ON_CLICK, new AddScriptClickListener(this));
			this.getAddSmsMenuitem().addEventListener(Events.ON_CLICK, new AddSmsClickListener(this));
			this.getAddSoundMenuitem().addEventListener(Events.ON_CLICK, new AddSoundClickListener(this));

			this.getAddEmailMenuitem().setImage(AlertType.EmailAlert.getImage());
			this.getAddScriptMenuitem().setImage(AlertType.ScriptAlert.getImage());
			this.getAddSmsMenuitem().setImage(AlertType.SmsAlert.getImage());
			this.getAddSoundMenuitem().setImage(AlertType.SoundAlert.getImage());
			
			try{
				View view = Toolkit.getToolkit().getSvdbView(Executions.getCurrent().getDesktop());
				if(!view.isAdmin()){//�ǹ���Ա�û�
					IniFile userIniFile = view.getUserIni();
					String addAlert_str = userIniFile.getValue(userIniFile.getSections(), UserRightId.DoAlertRuleAdd);
					String deleteAlert_str = userIniFile.getValue(userIniFile.getSections(), UserRightId.DoAlertRuleDel);
					String editAlert_str = userIniFile.getValue(userIniFile.getSections(), UserRightId.DoAlertRuleEdit);
					if(!"1".equals(addAlert_str)){
						this.getAddButton().setDisabled(true);
						this.getAddAlertPopupMenupopup().detach();
					}
					if(!"1".equals(deleteAlert_str)){
						this.getDeleteButton().setDisabled(true);
					}
					if(!"1".equals(editAlert_str)){
						editFlag = false;
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			this.getDeleteButton().addEventListener(Events.ON_CLICK, new DeleteClickListener(this));
			this.getEnableButton().addEventListener(Events.ON_CLICK, new EnableClickListener(this));
			this.getDisableButton().addEventListener(Events.ON_CLICK, new DisableClickListener(this));
			this.getAlertRuleListbox().addEventListener(Events.ON_SELECT, new AlertRuleListboxSelectListener(this));

			((Listheader) BaseTools.getComponentById(this, "alerttype_header")).setSortAscending(BaseTools.getSortComparator(1, false));
			((Listheader) BaseTools.getComponentById(this, "alerttype_header")).setSortDescending(BaseTools.getSortComparator(1, true));
			((Listheader) BaseTools.getComponentById(this, "alertstate_header")).setSortAscending(BaseTools.getSortComparator(2, false));
			((Listheader) BaseTools.getComponentById(this, "alertstate_header")).setSortDescending(BaseTools.getSortComparator(2, true));
			((Listheader) BaseTools.getComponentById(this, "alertenable_header")).setSortAscending(BaseTools.getSortComparator(3, false));
			((Listheader) BaseTools.getComponentById(this, "alertenable_header")).setSortDescending(BaseTools.getSortComparator(3, true));

			SelectableListheader.addPopupmenu(getAlertRuleListbox());
			/*
			 * ((Listheader) BaseTools.getComponentById(this,
			 * "alertlogtype_header"
			 * )).setSortAscending(BaseTools.getSortComparator(4, false));
			 * ((Listheader) BaseTools.getComponentById(this,
			 * "alertlogtype_header"
			 * )).setSortDescending(BaseTools.getSortComparator(4, true));
			 * ((Listheader) BaseTools.getComponentById(this,
			 * "alertlogstatus_header"))
			 * .setSortAscending(BaseTools.getSortComparator(6, false));
			 * ((Listheader) BaseTools.getComponentById(this,
			 * "alertlogstatus_header"))
			 * .setSortDescending(BaseTools.getSortComparator(6, true));
			 */

			init();
			getAlertRuleListbox().addEventListener("onPaging", new EventListener() {
				@Override
				public void onEvent(Event event) throws Exception {
					PagingEvent pevt = (PagingEvent) event;
					int pagesize = getAlertRuleListbox().getPageSize();
					int pgno = pevt.getActivePage();
					getAlertRuleListbox().setSelectedIndex(pagesize * pgno);
					Events.sendEvent(new Event(Events.ON_SELECT,getAlertRuleListbox()));
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
//			Message.showError("��ʼ�����ݳ����п��������ӷ����������������⣬��ˢ��ҳ������ԣ�");
		}
	}

	public void init() throws Exception {
		//getAlertLogListbox().getItems().clear();
		AlertRuleQueryCondition condition = new AlertRuleQueryCondition();
		BaseAlert[] result = DictionaryFactory.getIAlertDao().queryAlertRule(new AccessControl(), condition);
		
		Arrays.sort(result, new Comparator<BaseAlert>(){
			@Override
			public int compare(BaseAlert o1, BaseAlert o2) {
				if (o1==null) return -1;
				if (o2==null) return 1;
				if (o1.getName()==null) return -1;
				if (o2.getName()==null) return 1;
				return o1.getName().compareTo(o2.getName());
			}
		});
		getAlertRuleListbox().getItems().clear();
		Listitem firstItem = null;
		if(this.editFlag){
			for (BaseAlert basealert : result) {
				Listitem item = BaseTools.setRow(getAlertRuleListbox(), basealert, basealert.getName(), basealert.getType().getComponent(),
						basealert.getCategory().getComponent(), basealert.getState().getComponent(), BaseTools.getWithLink("", Labels.getLabel("Editor"),
								"/main/images/alert/edit.gif", new EditAlertMenuClickListener(this, basealert)));
				item.setTooltip(getDetailPopup(basealert));
				if (firstItem == null) firstItem = item;
			}
		}else{
			for (BaseAlert basealert : result) {
				Listitem item = BaseTools.setRow(getAlertRuleListbox(), basealert, basealert.getName(), basealert.getType().getComponent(),
						basealert.getCategory().getComponent(), basealert.getState().getComponent(), BaseTools.getWithLink("", Labels.getLabel("Editor"),
								"/main/images/alert/edit_false.gif", new EditAlertMenuClickListener(this, basealert)));
				item.setTooltip(getDetailPopup(basealert));
				if (firstItem == null) firstItem = item;
			}
		}

		//Ĭ��ѡ���һ��
		getAlertRuleListbox().setSelectedItem(firstItem);
		Events.sendEvent(new Event(Events.ON_SELECT,getAlertRuleListbox()));
		
		String id  = Toolkit.getToolkit().getCookie("alert_rul_imfomation");
		if(id == null || !contains(result,id)){
			return;
		}
		
		//��ѡ���˵�����յ�
		for (Object obj : getAlertRuleListbox().getSelectedItems()) {
			((Listitem) obj).setSelected(false);
		}
		
		//ѡ������ӻ����޸��˵���
		Listitem selectedItem = null;
		for(Object obj : getAlertRuleListbox().getItems()){
			if((obj instanceof Listitem)==true){
				Listitem tmpItem = (Listitem)obj;
				BaseAlert tmpAlert = (BaseAlert)tmpItem.getValue();
				if(tmpAlert==null) continue;
				if(id.equals(tmpAlert.getId())){
					selectedItem = tmpItem;
					break;
				}
			}
		}
		
		if(selectedItem==null){
			return;
		}
		getAlertRuleListbox().setSelectedItem(selectedItem);
		Events.sendEvent(new Event(Events.ON_SELECT,getAlertRuleListbox()));
		Toolkit.getToolkit().setCookie("alert_rul_imfomation", "", 1);
	}
	
	private boolean contains(BaseAlert[] result,String id) {
		if (id == null || "".equals(id)) return false;
		for (BaseAlert ba : result) {
			if (id.equals(ba.getId())) {
				return true;
			}
		}
		return false;
	}
	
	public void refresh() {
		try {
			init();
			Events.sendEvent(new Event(Events.ON_SELECT,this.getAlertRuleListbox()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void getTooltiptext(TooltipPopup tooltippopup, AlertLogItem alertlogitem) {
		tooltippopup.setTitle(alertlogitem.getAlertName());
		tooltippopup.setImage(alertlogitem.getAlertType().getImage());
		tooltippopup.addDescription(Labels.getLabel("AlarmTime"), DATE_TO_STRING.format(alertlogitem.getAlertTime()));
		tooltippopup.addDescription(Labels.getLabel("DeviceNameTile"), alertlogitem.getEntityName());
		tooltippopup.addDescription(Labels.getLabel("MonitorName"), alertlogitem.getMonitorName());
		tooltippopup.addDescription(Labels.getLabel("AlarmReceiver"), alertlogitem.getAlertReceiver());
		tooltippopup.addDescription(Labels.getLabel("AlarmStatus"), alertlogitem.getAlertStatus().toString());
	}

	private void getTooltiptext(TooltipPopup tooltippopup, BaseAlert basealert) {
		tooltippopup.setTitle(basealert.getName());
		tooltippopup.setImage(basealert.getType().getImage());
		tooltippopup.addDescription(Labels.getLabel("AlarmType:"), basealert.getType().toString());
		if (basealert.getType() == AlertType.EmailAlert) {
			tooltippopup.addDescription(Labels.getLabel("AlarmReceiveMailAddress:"), ((EmailAlert) basealert).getEmailAddresss());
			tooltippopup.addDescription(Labels.getLabel("OtherEmailAddress"), ((EmailAlert) basealert).getOtherAddress());
			tooltippopup.addDescription(Labels.getLabel("EmailTemplate:"), ((EmailAlert) basealert).getEmailTemplate());
			tooltippopup.addDescription(Labels.getLabel("UpgradeNumber"), ((EmailAlert) basealert).getUpgradeTimes());
			tooltippopup.addDescription(Labels.getLabel("UpgradeRecipientAddress"), ((EmailAlert) basealert).getReceiverAddress());
			tooltippopup.addDescription(Labels.getLabel("StoppingTime"), ((EmailAlert) basealert).getStopTimes());
			tooltippopup.addDescription(Labels.getLabel("WatchAlarmList"), ((EmailAlert) basealert).getWatchSheet());
		} else if (basealert.getType() == AlertType.SmsAlert) {
			tooltippopup.addDescription(Labels.getLabel("AlarmReceivingPhoneNumber:"), ((SMSAlert) basealert).getSmsNumber());
			tooltippopup.addDescription(Labels.getLabel("OtherMobilePhone:"), ((SMSAlert) basealert).getOtherNumber());
			tooltippopup.addDescription(Labels.getLabel("TransmissionMode"), ((SMSAlert) basealert).getSendMode());
			tooltippopup.addDescription(Labels.getLabel("SMSTemplates:"), ((SMSAlert) basealert).getSMSTemplate());
			tooltippopup.addDescription(Labels.getLabel("UpgradeNumber"), ((SMSAlert) basealert).getUpgradeTimes());
			tooltippopup.addDescription(Labels.getLabel("UpgradeRecipientAddress"), ((SMSAlert) basealert).getReceiverAddress());
			tooltippopup.addDescription(Labels.getLabel("StoppingTime"), ((SMSAlert) basealert).getStopTimes());
			tooltippopup.addDescription(Labels.getLabel("OnDutyConfigurationList:"), ((SMSAlert) basealert).getWatchSheet());
		} else if (basealert.getType() == AlertType.ScriptAlert) {
			tooltippopup.addDescription(Labels.getLabel("Server:"), ((ScriptAlert) basealert).getScriptServer());
			tooltippopup.addDescription(Labels.getLabel("Script:"), ((ScriptAlert) basealert).getScriptFile());
			tooltippopup.addDescription(Labels.getLabel("AdditionalParameters:"), ((ScriptAlert) basealert).getScriptParam());
		} else if (basealert.getType() == AlertType.SoundAlert) {
			tooltippopup.addDescription(Labels.getLabel("ServerName:"), ((SoundAlert) basealert).getServerName());
			tooltippopup.addDescription(Labels.getLabel("LoginName:"), ((SoundAlert) basealert).getLoginName());
		}
		tooltippopup.addDescription(Labels.getLabel("AlarmStrategy:"), basealert.getStrategy());
		tooltippopup.addDivRow();
		tooltippopup.addDescription(Labels.getLabel("AlarmEvent:"), basealert.getCategory().getDisplayString());
		tooltippopup.addDescription(Labels.getLabel("AlarmConditionClassification:"), basealert.getTimes().getDisplayString());

		StringBuffer sb = new StringBuffer();
		if (basealert.getTimes() == AlertTimes.Always) {
			sb.append(Labels.getLabel("FrontAlwaysSend"));
			sb.append(basealert.getAlways());
			sb.append(Labels.getLabel("EndAlwaysSend"));
		} else if (basealert.getTimes() == AlertTimes.Only) {
			sb.append(Labels.getLabel("FrontSendAAlarm="));
			sb.append(basealert.getOnly());
			sb.append(Labels.getLabel("EndSendAAlarm"));
		} else if (basealert.getTimes() == AlertTimes.Select) {
			sb.append(Labels.getLabel("FrontSelectSend"));
			sb.append(basealert.getSelect1());
			sb.append(Labels.getLabel("MidSelectSend"));
			sb.append(basealert.getSelect2());
			sb.append(Labels.getLabel("EndSelectSend"));
		}
		tooltippopup.addDescription("", sb.toString());
		/*
		 * sb.append("--������б�-----------------------\n"); String monitors =
		 * basealert.getTarget(); if (monitors!=null){ String[] monitorlist =
		 * monitors.split(","); int count = 0; for (String monitor :
		 * monitorlist){ if (monitor == null || "".equals(monitor)) continue;
		 * sb.append(monitor); sb.append("\n"); count++; if (count>=5){
		 * sb.append("......"); sb.append("\n"); break; } } }
		 */
	}
	
	private void refreshAlertLogListbox() throws Exception{
		System.out.println("refresh !!!!!!!!!!!!!! refreshAlertLogListbox");
		Listitem item = getAlertRuleListbox().getSelectedItem();
		if (item != null) {
			Object value = item.getValue();
			if (!(value instanceof BaseAlert))
				return;
			BaseAlert basealert = (BaseAlert) value;
			AlertLogQueryCondition queryCondition = new AlertLogQueryCondition();
			queryCondition.setAlertIndex(basealert.getId());
			queryCondition.setAlertType(basealert.getType());

			queryCondition.setEndTime(new Date());

			Calendar calStart = Calendar.getInstance();
			calStart.setTime(new Date());
			calStart.add(Calendar.HOUR, -24);
			queryCondition.setStartTime(calStart.getTime());

			queryCondition.setLimitIndex(true);
			queryCondition.setLimitTime(true);
			queryCondition.setLimitType(true);
			initLargeListbox(queryCondition);
		}
	}
	private void refreshAlertLogListboxWithNoSelectedItem() throws Exception{
		LargeListbox ll = new LargeListbox(this);
	}
}
