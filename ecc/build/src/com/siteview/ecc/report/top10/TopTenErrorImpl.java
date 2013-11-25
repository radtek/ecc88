package com.siteview.ecc.report.top10;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;

import com.siteview.ecc.monitorbrower.MonitorBean;
import com.siteview.ecc.monitorbrower.MonitorDaomImpl;
import com.siteview.ecc.report.top10.type.IComponent;
import com.siteview.ecc.report.top10.type.MonitorLinkImpl;
import com.siteview.ecc.report.top10.type.MonitorStatusImpl;
import com.siteview.ecc.report.top10.type.NumberImpl;
import com.siteview.ecc.report.top10.type.TextImpl;
import com.siteview.ecc.treeview.EccTreeModel;


public class TopTenErrorImpl implements TopTen{

	
	@Override
	public List<Map<String, IComponent>> getData() throws Exception {
		List<Map<String, IComponent>> retlist = new LinkedList<Map<String, IComponent>>();
		
		EccTreeModel model = EccTreeModel.getInstance(Executions.getCurrent().getDesktop().getSession());
		MonitorDaomImpl info = new MonitorDaomImpl(model,model.getView());

		List<MonitorBean> list = info.getErrorMost(10);
		
		for (MonitorBean bean : list){
			Map<String, IComponent> map = new HashMap<String, IComponent>();
			map.put(Labels.getLabel("ID"), new MonitorLinkImpl(bean,bean.getMonitorId()));
			map.put(Labels.getLabel("State"), new MonitorStatusImpl(bean.getStatus()));
			map.put(Labels.getLabel("Name"), new TextImpl(bean.getMonitorName()));
//			map.put("����", new NumberImpl(12,bean.getErrorPersent() + "%",bean.getStatus()));
			retlist.add(map);
		}
		return retlist;
	}

	@Override
	public List<String> getTitles() throws Exception {
		List<String> retlist = new LinkedList<String>();
		retlist.add(Labels.getLabel("ID"));
		retlist.add(Labels.getLabel("State"));
		retlist.add(Labels.getLabel("Name"));
//		retlist.add("����");
		return retlist;
	}

	@Override
	public String getCaption() throws Exception {
		return Labels.getLabel("DetectorTop10MakeMostMistakes");
	}
	
}