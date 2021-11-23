package by.tsarenkov.shop.tag;

import by.tsarenkov.shop.dao.impl.SQLProductDAO;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class BasketProductTag extends TagSupport {

    private static final Logger LOGGER = Logger.getLogger(BasketProductTag.class);

    private int count;

    public BasketProductTag() {}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public int doStartTag() throws JspException {
        String str = " <div class='basket_tag'>\n" +
                "<img src='img/cart-black.svg'>\n" +
                "<p class='count_products'>"+ count +"</p>\n" +
                "</div>";
        try {
            JspWriter out = pageContext.getOut();
            out.write(str);
        } catch (IOException e) {
            LOGGER.error("Cannot write the tag: " + e);
        }
       return SKIP_BODY;
    }


}
