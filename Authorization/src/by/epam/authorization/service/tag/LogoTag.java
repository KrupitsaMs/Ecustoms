package by.epam.authorization.service.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class LogoTag extends SimpleTagSupport {
	private String url = "";
	private boolean showBorder = false;
	private boolean showUrl = false;

	public void setUrl(String url) {
		this.url = url;
	}

	public void setShowBorder(boolean showBorder) {
		this.showBorder = showBorder;
	}

	public void setShowUrl(boolean showUrl) {
		this.showUrl = showUrl;
	}

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
