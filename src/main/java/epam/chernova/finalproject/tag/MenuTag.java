package epam.chernova.finalproject.tag;


import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class MenuTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        Integer role = (Integer) pageContext.getSession().getAttribute("role");
        String menu = null;
        System.out.println(role);
        if (role != null) {
            switch (role) {
                case 1:
                    menu = "/front/jsp/admin/menu.jsp";
                    break;
                case 2:
                    menu = "/front/jsp/client/menu.jsp";
                    break;
            }
        }else{
            menu = "/front/jsp/common/menu.jsp";
        }

        try {
            pageContext.include(menu);
        } catch (IOException | ServletException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

}
