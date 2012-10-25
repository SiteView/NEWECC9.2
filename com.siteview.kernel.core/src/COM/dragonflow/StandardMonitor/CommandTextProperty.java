package COM.dragonflow.StandardMonitor;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Vector;

import jgl.HashMap;
import COM.dragonflow.HTTP.HTTPRequest;
import COM.dragonflow.Page.CGI;
import COM.dragonflow.Properties.ScalarProperty;
import COM.dragonflow.Properties.StringProperty;
import COM.dragonflow.SiteView.AtomicMonitor;
import COM.dragonflow.SiteView.SiteViewObject;
import COM.dragonflow.SiteViewException.SiteViewException;
import COM.dragonflow.Utils.TextUtils;

//Referenced classes of package COM.dragonflow.Properties:
//         StringProperty

public class CommandTextProperty extends ScalarProperty {

	public static String OTHER_STRING = "Other";
	public boolean allowDisable;
	public boolean allowOther;
	public int otherSize;
	public boolean defaultToOther;
	public boolean multiple;
	public int listSize;

	public CommandTextProperty(String s) {
		this(s, "");
	}

	public CommandTextProperty(String s, boolean flag) {
		this(s, "", flag);
	}

	public CommandTextProperty(String s, String s1) {
		this(s, s1, false);
	}

	public CommandTextProperty(String s, String s1, boolean flag) {
		super(s, s1);
		allowDisable = false;
		allowOther = false;
		otherSize = 20;
		defaultToOther = false;
		multiple = false;
		listSize = 3;
		allowDisable = flag;
	}

	public boolean displayValue(String s, String s1,
			SiteViewObject siteviewobject) {
		return true;
	}

	public void printProperty(CGI cgi, PrintWriter printwriter,
			SiteViewObject siteviewobject, HTTPRequest httprequest,
			HashMap hashmap, boolean flag) {
		if (!flag && (siteviewobject instanceof AtomicMonitor)) {
			flag = !((AtomicMonitor) siteviewobject).isEditableProperty(this,
					httprequest);
		}
		if (allowDisable && flag) {
			super.printProperty(cgi, printwriter, siteviewobject, httprequest,
					hashmap, flag);
			return;
		}
		Vector vector = new Vector();

		vector.add("cisco");
		vector.add("huawei");
		vector.add("huawei");
		vector.add("huawei");
		vector.add("cisco");
		vector.add("cisco");

		printwriter
				.println("<TR><TD ALIGN=\"RIGHT\" VALIGN=\"TOP\">"
						+ getLabel()
						+ "</TD>"
						+ "<TD><TABLE><TR><TD ALIGN=\"left\" VALIGN=\"top\"><SELECT name="
						+ getName());
		boolean flag1 = false;
		String s5 = siteviewobject.getProperty(this);
		HashMap hashmap1 = new HashMap();
		for (Enumeration enumeration = siteviewobject.getMultipleValues(this); enumeration
				.hasMoreElements(); hashmap1.put(enumeration.nextElement(),
				"yes")) {
		}
		if (hashmap1.size() == 0 && s5.length() > 0) {
			hashmap1.put(s5, "yes");
		}

		for (int j = 0; j < vector.size(); j +=2) {
			String s7 = "";
			String s10 = (String) vector.elementAt(j);
			if (hashmap1.get(s10) != null) {
				flag1 = true;
				s7 = "SELECTED ";
			}
			printwriter.print("<OPTION " + s7 + "value=\""
					+ TextUtils.escapeHTML(s10) + "\">"
					+ TextUtils.escapeHTML((String) vector.elementAt(j + 1)));
		}

		String s6 = "";
		if (!flag1) {
			if (s5.length() != 0) {
				if (allowOther) {
					s6 = "SELECTED";
				} else {
					String s8 = s5;
					int k = s5.indexOf('|');
					if (k != -1) {
						s8 = s5.substring(0, k);
						if (k + 1 < s5.length()) {
							s8 = s8 + " (" + s5.substring(k + 1, s5.length())
									+ ")";
						}
					}
					printwriter.print("<OPTION SELECTED value=\"" + s5 + "\">"
							+ s8);
				}
			} else if (defaultToOther) {
				s6 = "SELECTED";
			}
		}
		if (allowOther) {
			printwriter.print("<OPTION " + s6 + " value=\"" + OTHER_STRING
					+ "\">" + OTHER_STRING);
		}
		String s9 = "";
		if (allowOther) {
			String s11 = "";
			if (!flag1) {
				s11 = s5;
			}
//			s9 = "<br><INPUT TYPE=TEXT NAME=" + getName() + " SIZE="
//					+ otherSize + " VALUE=\"" + s11 + "\">";
		}
		printwriter.print("</SELECT>" + s9 + "</TD></TR>"
				+ "<TR><TD ALIGN=\"left\" VALIGN=\"top\"><FONT SIZE=-1>"
				+ getDescription() + "</FONT></TD></TR>"
				+ "</TABLE></TD></TR>");
	}

	public String getRawValue(HTTPRequest httprequest) {
		String s = "";
		String s1 = "";
		Enumeration enumeration = httprequest.getValues(getName());
		if (enumeration.hasMoreElements()) {
			s = (String) enumeration.nextElement();
		}
		if (enumeration.hasMoreElements()) {
			s1 = (String) enumeration.nextElement();
		}
		if (s.equals(OTHER_STRING) || s1.length() > 0) {
			return s1;
		} else {
			return s;
		}
	}

	public String getCustomValue(HTTPRequest httprequest) {
		String s = "";
		String s1 = "";
		Enumeration enumeration = httprequest.getValues(getName());
		if (enumeration.hasMoreElements()) {
			s = (String) enumeration.nextElement();
		}
		while (enumeration.hasMoreElements()) {
			s1 = (String) enumeration.nextElement();
		}
		if (s.equals(OTHER_STRING) || s1.length() > 0) {
			return s1;
		} else {
			return "";
		}
	}

	public boolean usingCustomValue(HTTPRequest httprequest) {
		String s = "";
		String s1 = "";
		Enumeration enumeration = httprequest.getValues(getName());
		if (enumeration.hasMoreElements()) {
			s = (String) enumeration.nextElement();
		}
		while (enumeration.hasMoreElements()) {
			s1 = (String) enumeration.nextElement();
		}
		return s.equals(OTHER_STRING) || s1.length() > 0;
	}

	public String verify(String s) {
		return super.verify(s);
	}

}
