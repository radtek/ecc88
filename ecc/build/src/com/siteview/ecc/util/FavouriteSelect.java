package com.siteview.ecc.util;

import org.zkoss.util.resource.Labels;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

import com.siteview.ecc.treeview.EccTreeItem;
import com.siteview.ecc.treeview.controls.EccComboitem;

public class FavouriteSelect extends Combobox {

	public FavouriteSelect() {
		super();
		
		Comboitem item;
		item=new EccComboitem(Labels.getLabel("DisplayAllState"),EccTreeItem.STATUS_ALL,"/main/control/images/filter.gif");
		item.setParent(this);
		item.setStyle(Toolkit.getToolkit().getStatusStyle(EccTreeItem.STATUS_ALL));
		super.setSelectedItem(item);
		
		item=new EccComboitem(Labels.getLabel("WrongAndDangerous"), EccTreeItem.STATUS_ERROR|EccTreeItem.STATUS_WARNING,"/main/control/images/state_red_yellow.gif")	;
		item.setParent(this);
		item.setStyle(Toolkit.getToolkit().getStatusStyle(EccTreeItem.STATUS_ERROR|EccTreeItem.STATUS_WARNING));
		
		item=new EccComboitem(Labels.getLabel("OnlyShowError"), EccTreeItem.STATUS_ERROR,"/main/control/images/state_red.gif");
		item.setParent(this);
		item.setStyle(Toolkit.getToolkit().getStatusStyle(EccTreeItem.STATUS_ERROR));
		
		item=new EccComboitem(Labels.getLabel("OnlyShowDangerous"), EccTreeItem.STATUS_WARNING,"/main/control/images/state_yellow.gif");
		item.setParent(this);
		item.setStyle(Toolkit.getToolkit().getStatusStyle(EccTreeItem.STATUS_WARNING));
		
		item=new EccComboitem(Labels.getLabel("OnlyShowGood"), EccTreeItem.STATUS_OK,"/main/control/images/state_green.gif");
		item.setParent(this);
		item.setStyle(Toolkit.getToolkit().getStatusStyle(EccTreeItem.STATUS_OK));
		
		item=new EccComboitem(Labels.getLabel("OnlyShowDisable"), EccTreeItem.STATUS_DISABLED,"/main/control/images/state_stop.gif");
		item.setParent(this);
		item.setStyle(Toolkit.getToolkit().getStatusStyle(EccTreeItem.STATUS_DISABLED));
		
		item=new EccComboitem(Labels.getLabel("OnlyShowNoData"), EccTreeItem.STATUS_NULL,"/main/control/images/state_grey.gif");
		item.setParent(this);
		item.setStyle(Toolkit.getToolkit().getStatusStyle(EccTreeItem.STATUS_NULL));
		
		item=new EccComboitem(Labels.getLabel("OnlyShowDefinitionError"), EccTreeItem.STATUS_BAD,"/main/control/images/state_grey.gif");
		item.setParent(this);
		item.setStyle(Toolkit.getToolkit().getStatusStyle(EccTreeItem.STATUS_BAD));

		item=new EccComboitem(Labels.getLabel("AllErrorsWithNoData"), EccTreeItem.STATUS_BAD|EccTreeItem.STATUS_ERROR|EccTreeItem.STATUS_NULL,"/main/control/images/state_error_all.gif");
		item.setParent(this);
		item.setStyle(Toolkit.getToolkit().getStatusStyle(EccTreeItem.STATUS_BAD|EccTreeItem.STATUS_ERROR|EccTreeItem.STATUS_NULL));

	}

}
