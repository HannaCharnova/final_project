package epam.chernova.finalproject.tag;


import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class HomeTag extends TagSupport{

    @Override
    public int doStartTag() throws JspException {
        String home="/front/jsp/common/home.jsp";
        try {
            pageContext.include(home);
        } catch (IOException | ServletException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

}
