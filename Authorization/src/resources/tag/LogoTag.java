package resources.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * LogoTag.java
 * Class implemented interface SimpleTagSupport
 * Creates a tag translated on JSP.
 * It reflects a logo WCO and when you press this logo jsp will redirect page to WCO index page 
 * @author MasSword
 */

public class LogoTag extends SimpleTagSupport {
	private String url = "";
	private boolean showBorder = false;
	private boolean showUrl = false;

	/**
     * Method-setter
     * @param String url
     */
	
	public void setUrl(String url) {
		this.url = url;
	}

	/**
     * Method-setter
     * @param boolean showBorder
     */
	
	public void setShowBorder(boolean showBorder) {
		this.showBorder = showBorder;
	}

	/**
     * Method-setter
     * @param boolean showUrl
     */
	
	public void setShowUrl(boolean showUrl) {
		this.showUrl = showUrl;
	}
	
	 /**
     * Creates a tag translated on JSP.
     * It reflects a logo WCO and when you press this logo jsp will redirect page to WCO index page
     */
	
	public void doTag() throws JspException {

		PageContext context = (PageContext)getJspContext();
		JspWriter out = context.getOut();

		try {
			StringBuffer sb = new StringBuffer();
			if (showUrl) {
				sb.append("<h3>");
				sb.append(url);
				sb.append("</h3>\n");
			}
			sb.append("<a href=\"http://www.wcoomd.org/\"><img ");
			if (showBorder) {
				sb.append("border=\"1\" ");
			}
			sb.append("alt=\"\" src=\"");
			sb.append(url);
			sb.append("\"/></a>");

			out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
