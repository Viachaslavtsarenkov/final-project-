package by.tsarenkov.shop.tag;


import by.tsarenkov.shop.bean.Product;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class PaginationTag extends TagSupport {

    private PaginationTag list;
    private int currentPage;
    private int countPage = 1;
    private int countElements = 6;
    private List<Product> products;

    public PaginationTag() {}

    public PaginationTag(List<Product> products) {
        this.products = products;
        this.currentPage = 1;
        this.countPage = products.size() / countElements;
    }


    public List<Product> getProducts() {
        return products.subList(1, 6);
    }

    public void setList(PaginationTag list) {
        this.list = list;
    }

    public int doStartTag() throws JspException {

        try {
            JspWriter out = pageContext.getOut();
            for(int i = countPage; i < countPage; ++i) {
                out.write("<b>" + products.get(i).getId() + "</b>");
            }
        } catch (IOException e) {

        }
        return SKIP_BODY;
    }


}
