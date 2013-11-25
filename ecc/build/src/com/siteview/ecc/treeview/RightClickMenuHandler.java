package com.siteview.ecc.treeview;

import java.util.LinkedHashMap;
import java.util.Map;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Include;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Treerow;

import com.siteview.base.manage.View;
import com.siteview.base.template.EntityTemplate;
import com.siteview.base.template.TemplateManager;
import com.siteview.base.tree.INode;
import com.siteview.base.treeInfo.EntityInfo;
import com.siteview.base.treeInfo.GroupInfo;
import com.siteview.base.treeInfo.SeInfo;

public class RightClickMenuHandler implements EventListener {

	@Override
	public void onEvent(Event event) throws Exception 
	{
		Treerow row=(Treerow)event.getTarget();
		Treeitem item=(Treeitem)row.getParent();
		EccTreeItem eccTreeItem=(EccTreeItem)item.getValue();
		
	}
	public void onEventDel(Event event) throws Exception {
	

		Treerow row=(Treerow)event.getTarget();
		Treeitem item=(Treeitem)row.getParent();
		
		Include eccbody=(Include) row.getFellow("eccBody");
		String id=((EccTreeItem)item.getValue()).getId();
		EccTreeModel treeModel = (EccTreeModel) item.getTree().getModel();
		View view = treeModel.getView();
		INode inode = view.getNode(id);
		if(item.getContext()!=null)
			  return;
		    
		if(inode!=null)
			setItemMenu(item,view,inode,eccbody);
		
	}
	private void setItemMenu(Treeitem treeitem,View view,INode node,Include eccbody)
	{
		Menupopup popMenu =new Menupopup();
		treeitem.setContext(popMenu);
		popMenu.setPage(treeitem.getPage());

		// itemMenu.setId(node.getId());
		Menu menu;
		Menuitem item;
		Menupopup tmp;
		if (node.getType().equals(INode.SE))
		{
			SeInfo se = view.getSeInfo(node);
			if (se.canAddGroup())
			{
				item = new Menuitem();
				item.setLabel(Labels.getLabel("GroupTitle"));
				item.addEventListener("onClick",new MenuItemClickListener(eccbody,"addgroup"));
				item.setParent(popMenu);
			} else
			{
				item = new Menuitem();
				item.setLabel(Labels.getLabel("GroupTitle"));
				item.setParent(popMenu);
				item.setDisabled(true);
			}
			if (se.canAddDevice())
			{
				item = new Menuitem();
				item.setLabel("Ìí¼ÓÉè±¸");
				item.setParent(popMenu);
				item.addEventListener("onClick",new MenuItemClickListener(eccbody,"addentity"));
//				createEntityMenu(popMenu);
			} else
			{
				item = new Menuitem();
				item.setLabel("Ìí¼ÓÉè±¸");
				item.setParent(popMenu);
				item.setDisabled(true);
			}
			if (se.canEdit())
			{
				item = new Menuitem();
				item.setLabel("±à¼­");
				item.setParent(popMenu);
			} else
			{
				item = new Menuitem();
				item.setLabel("±à¼­");
				item.setParent(popMenu);
				item.setDisabled(true);
			}
		} else if (node.getType().equals(INode.GROUP))
		{
			GroupInfo group = view.getGroupInfo(node);
			if (group.canAddGroup())
			{
				item = new Menuitem();
				item.setLabel(Labels.getLabel("GroupTitle"));
				item.setParent(popMenu);
				item.addEventListener("onClick",new MenuItemClickListener(eccbody,"addgroup"));
			} else
			{
				item = new Menuitem();
				item.setLabel(Labels.getLabel("GroupTitle"));
				item.setParent(popMenu);
				item.setDisabled(true);
			}
			if (group.canAddDevice())
			{
				item = new Menuitem();
				item.setLabel("Ìí¼ÓÉè±¸");
				item.setParent(popMenu);
				item.addEventListener("onClick",new MenuItemClickListener(eccbody,"addentity"));
//				createEntityMenu(popMenu);
			} else
			{
				item = new Menuitem();
				item.setLabel("Ìí¼ÓÉè±¸");
				item.setParent(popMenu);
				item.setDisabled(true);
			}
			if (group.canEdit())
			{
				item = new Menuitem();
				item.setLabel("±à¼­");
				item.setParent(popMenu);
			} else
			{
				item = new Menuitem();
				item.setLabel("±à¼­");
				item.setParent(popMenu);
				item.setDisabled(true);
			}
			if (group.canEdit())
			{
				item = new Menuitem();
				item.setLabel("½ûÖ¹");
				item.setParent(popMenu);
			} else
			{
				item = new Menuitem();
				item.setLabel("½ûÖ¹");
				item.setParent(popMenu);
				item.setDisabled(true);
			}
			
			if (group.canPasteDevice())
			{
				item = new Menuitem();
				item.setLabel("Õ³Ìû");
				item.setParent(popMenu);
			} else
			{
				item = new Menuitem();
				item.setLabel("Õ³Ìû");
				item.setParent(popMenu);
				item.setDisabled(true);
			}
			if (group.canDeleteNode())
			{
				item = new Menuitem();
				item.setLabel("É¾³ý");
				item.setParent(popMenu);
			} else
			{
				item = new Menuitem();
				item.setLabel("É¾³ý");
				item.setParent(popMenu);
				item.setDisabled(true);
			}
			
		} else if (node.getType().equals(INode.ENTITY))
		{
			EntityInfo entity = view.getEntityInfo(node);
			if (entity.canAddMonitor())
			{
//				menu = new Menu();
//				menu.setLabel("Ìí¼Ó¼ì²âÆ÷");
//				menu.setParent(popMenu);
//				EntityTemplate tpl = entity.getDeviceTemplate();
//				LinkedHashMap<String, String> a = tpl.getSubMonitorTemplateLabel();
//				Menupopup mp = new Menupopup();
//				mp.setParent(menu);
//				for (String tid : a.keySet())
//				{
//					item = new Menuitem();
//					item.setLabel(a.get(tid));
//					item.setParent(mp);
//				}
				item = new Menuitem();
				item.setLabel("Ìí¼Ó¼à²âÆ÷");
				item.addEventListener("onClick",new MenuItemClickListener(eccbody,entity.getSvId(),"addmonitor"));
				item.setParent(popMenu);
			} else
			{
				item = new Menuitem();
				item.setLabel("Ìí¼Ó¼à²âÆ÷");
				item.setParent(popMenu);
				item.setDisabled(true);
			}
			
			if (entity.canEdit())
			{
				item = new Menuitem();
				item.setLabel("±à¼­");
				item.setParent(popMenu);
			} else
			{
				item = new Menuitem();
				item.setLabel("±à¼­");
				item.setParent(popMenu);
				item.setDisabled(true);
			}
			if (entity.canTestDevice())
			{
				item = new Menuitem();
				item.setLabel("²âÊÔ");
				item.setParent(popMenu);
			} else
			{
				item = new Menuitem();
				item.setLabel("²âÊÔ");
				item.setParent(popMenu);
				item.setDisabled(true);
			}
			if (entity.canEdit())
			{
				item = new Menuitem();
				item.setLabel("½ûÖ¹");
				item.setParent(popMenu);
			} else
			{
				item = new Menuitem();
				item.setLabel("½ûÖ¹");
				item.setParent(popMenu);
				item.setDisabled(true);
			}
			if (entity.canDeleteNode())
			{
				item = new Menuitem();
				item.setLabel("É¾³ý");
				item.setParent(popMenu);
			} else
			{
				item = new Menuitem();
				item.setLabel("É¾³ý");
				item.setParent(popMenu);
				item.setDisabled(true);
			}
			if (entity.canRefresh())
			{
				item = new Menuitem();
				item.setLabel("Ë¢ÐÂ");
				item.setParent(popMenu);
			} else
			{
				item = new Menuitem();
				item.setLabel("Ë¢ÐÂ");
				item.setParent(popMenu);
				item.setDisabled(true);
			}
		} else if (node.getType().equals(INode.MONITOR))
		{
			
		} else
		{
		}
		popMenu.open(treeitem.getTreerow());
		
	}
	
	/**
	 * Éú³ÉÉè±¸²Ëµ¥
	 * 
	 * @param itemMenu
	 */
	private void createEntityMenu(Menupopup itemMenu)
	{
		Menupopup tmp;
		Menuitem item;
		Menu menu = new Menu();
		menu.setLabel("Ìí¼ÓÉè±¸");
		menu.setParent(itemMenu);
		Map<String, Map<String, String>> a = TemplateManager.getEntityGroupTemplateLabel();
		Menupopup mp = new Menupopup();
		mp.setParent(menu);
		
		for (String gid : a.keySet())
		{
			// logger.info(" Éè±¸×é £º" + gid);
			menu = new Menu();
			// menu.setId(gid);
			menu.setParent(mp);
			menu.setLabel(gid);
			
			tmp = new Menupopup();
			tmp.setParent(menu);
			Map<String, String> e = a.get(gid);
			for (String eid : e.keySet())
			{
				item = new Menuitem();
				// item.setId(eid);
				EntityTemplate entem = TemplateManager.getEntityTemplate(eid);
				if(entem==null) continue;
				String decription = entem.get_sv_description() == null ? entem.get_sv_name() : entem.get_sv_description();
				item.setTooltiptext(decription);
				item.setLabel(e.get(eid));
				
				item.setParent(tmp);
				
				// logger.info("    label/id £º" + e.get(eid) + "/" + eid);
			}
			
		}
	
	}
}
