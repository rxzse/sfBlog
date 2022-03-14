package org.apache.jsp.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class category_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/WEB-INF/jspf/newjsp.jspf");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("    <title>Document</title>\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.2.0/css/all.css\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://unpkg.com/buefy/dist/buefy.min.css\">\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("    ");
      out.write("\n");
      out.write("\n");
      out.write("<div id=\"op\">\n");
      out.write("        <aside class=\"menu\">\n");
      out.write("            <p class=\"menu-label\">\n");
      out.write("                Categories\n");
      out.write("            </p>\n");
      out.write("            <ul class=\"menu-list\">\n");
      out.write("                <li>\n");
      out.write("                    <span class=\"tag is-primary is-medium \">Category 1</span>\n");
      out.write("                    <span class=\"icon\"> <i class=\"fa fa-edit\" @click=\"edit(1)\"></i></span>\n");
      out.write("                    <span class=\"icon\"> <i class=\"fa fa-trash\" @click=\"del(1)\"></i></span>\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                    <span class=\"tag is-primary is-medium \">Category 1</span>\n");
      out.write("                    <span class=\"icon\"> <i class=\"fa fa-edit\"></i></span>\n");
      out.write("                    <span class=\"icon\"> <i class=\"fa fa-trash\"></i></span>\n");
      out.write("                </li>\n");
      out.write("                <br>\n");
      out.write("                <li><span class=\"tag is-danger is-medium \" @click=\"isComponentModalActive = true\">Create category</span>\n");
      out.write("                </li>\n");
      out.write("            </ul>\n");
      out.write("\n");
      out.write("        </aside>\n");
      out.write("        <b-modal v-model=\"isComponentModalActive\" has-modal-card full-screen :can-cancel=\"false\">\n");
      out.write("            <div class=\"modal-card\" style=\"width: auto\">\n");
      out.write("                <header class=\"modal-card-head\">\n");
      out.write("                    <p class=\"modal-card-title\">{{ formProps.typeName }}</p>\n");
      out.write("                </header>\n");
      out.write("\n");
      out.write("                <section class=\"modal-card-body\">\n");
      out.write("                    <input hidden name=\"id\" value=\"id\">\n");
      out.write("                    <b-field label=\"Name\">\n");
      out.write("                        <b-input type=\"text\" name=\"name\" v-model=\"formProps.name\" placeholder=\"Category Name\" required>\n");
      out.write("                        </b-input>\n");
      out.write("                    </b-field>\n");
      out.write("\n");
      out.write("                    <b-field label=\"Sequence\">\n");
      out.write("                        <b-input type=\"number\" name=\"sequence\" v-model=\"formProps.sequence\" placeholder=\"Sequence number\"\n");
      out.write("                            required>\n");
      out.write("                        </b-input>\n");
      out.write("                    </b-field>\n");
      out.write("\n");
      out.write("                    <b-field label=\"Alias\">\n");
      out.write("                        <b-input type=\"text\" name=\"alias\" v-model=\"formProps.alias\" placeholder=\"Alias indentify\" required>\n");
      out.write("                        </b-input>\n");
      out.write("                    </b-field>\n");
      out.write("\n");
      out.write("                    <b-button label=\"Save\" @click=\"submit\" />\n");
      out.write("                </section>\n");
      out.write("\n");
      out.write("                <b-button label=\"Close\" @click=\"isComponentModalActive = false\" />\n");
      out.write("                \n");
      out.write("            </div>\n");
      out.write("        </b-modal>\n");
      out.write("    </div>\n");
      out.write("    <script src=\"https://unpkg.com/vue@2\"></script>\n");
      out.write("    <script src=\"https://unpkg.com/buefy/dist/buefy.min.js\"></script>\n");
      out.write("    <script async type=\"text/javascript\" src=\"./js/app.js\"></script>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
