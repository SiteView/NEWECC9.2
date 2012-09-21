package SiteView.ecc.data;

import java.util.HashMap;
import java.util.Map;

import system.Collections.ArrayList;
import system.Collections.IEnumerator;
import system.Collections.IList;
import Siteview.AutoTaskDef;
import Siteview.DefRequest;
import Siteview.PlaceHolder;
import Siteview.Api.DefinitionLibrary;
import Siteview.Api.ISiteviewApi;
import Siteview.Api.PerspectiveDef;
import Siteview.Windows.Forms.ConnectionBroker;
import Siteview.Windows.Forms.ScopeUtil;
import Siteview.Xml.Scope;
import Siteview.Xml.XmlSecurityRight;

public class AQuickData {
	private PerspectiveDef perspectiveDef; 
	private ISiteviewApi m_api; // 初始化接口
	private IList m_lstSupportedScopesWithOwners;
	private DefinitionLibrary m_Library;
	private String m_strDefClassName = AutoTaskDef.get_ClassName(); // C#查询数据时的固定参数
	private ScopeUtil m_ScopeUtil; // 暂时叫 查询范围的类
	private String m_moduleSecurityName = "Siteview.Security.AutoTasks"; // C#查询数据时的固定参数
	/**
	 * 根据选择下拉式 装载关联的范围和类型
	 */
	private Map<String, ArrayList> htCatToListerItems = new HashMap<String, ArrayList>();
	public void getcboQuickOblink(String strBusLink) {
		m_api = ConnectionBroker.get_SiteviewApi();
		m_Library = ConnectionBroker.get_SiteviewApi().get_LiveDefinitionLibrary();
		perspectiveDef=m_Library.GetPerspectiveDef(m_api.get_RoleManager().PerspectiveFromRole());
		m_lstSupportedScopesWithOwners=m_ScopeUtil.GetSupportedScopesWithOwners(XmlSecurityRight.ToRight("VAED"), true);
		IEnumerator it = m_lstSupportedScopesWithOwners.GetEnumerator();
		while (it.MoveNext()) {
			ScopeUtil.ScopeAndOwners owners = (ScopeUtil.ScopeAndOwners) it
					.get_Current();
			if (owners.get_Owners().get_Count() > 0
					&& owners.get_Scope() != Scope.Global) {
				for (int i = 0; i < owners.get_Owners().get_Count(); i++) {
					String str = (String) owners.get_Owners().get_Item(i);
					OrganizeListerItemsByCategory(htCatToListerItems,
							owners.get_Scope(), str, strBusLink, false);
				}
			} else {
				OrganizeListerItemsByCategory(htCatToListerItems,
						owners.get_Scope(), "", strBusLink, false);
			}

		}

	}
	
	private void OrganizeListerItemsByCategory(
			Map<String, ArrayList> listItems, int s, String Owner,
			String linkTo, boolean bFilter) {
		String strPerspective = (this.perspectiveDef != null) ? this.perspectiveDef
				.get_Name() : "(Base)";

		ArrayList obs = (ArrayList) m_Library.GetPlaceHolderList(DefRequest
				.ForList(s, Owner, linkTo, m_strDefClassName, strPerspective));

		
		if (obs != null) {
			for (int i = 0; i < obs.get_Count(); i++) {
				PlaceHolder holder = (PlaceHolder) obs.get_Item(i);
				if (!listItems.containsKey(holder.get_Scope() + "~"
						+ holder.get_Folder())) {
					listItems.put(
							holder.get_Scope() + "~" + holder.get_Folder(),
							new ArrayList());
				}
				listItems.get(holder.get_Scope() + "~" + holder.get_Folder())
						.Add(holder);

//				if (!httypeToListerItems.containsKey(holder.get_Folder())) {
//					httypeToListerItems.put(holder.get_Folder(),
//							new ArrayList());
//				}
//				httypeToListerItems.get(holder.get_Folder()).Add(holder);
			}
		}
	}
}
