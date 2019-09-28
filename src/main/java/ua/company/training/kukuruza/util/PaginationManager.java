package ua.company.training.kukuruza.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class PaginationManager {
    public static final int RECORDS_PER_PAGE = 9;
    private static final int FIRST_PAGE = 1;

    public static int manage(HttpServletRequest req, long rowsCount) {
        String pageStr = req.getParameter(RequestParameters.PAGE);
        int page = Objects.isNull(pageStr) ? FIRST_PAGE : Integer.valueOf(pageStr);
        int skip = (page - 1) * RECORDS_PER_PAGE;
        long numberOfPages = rowsCount / RECORDS_PER_PAGE;
        if (rowsCount % RECORDS_PER_PAGE > 0) {
            numberOfPages++;
        }
        req.setAttribute(AttributeNames.PAGE, page);
        req.setAttribute(AttributeNames.NUMBER_OF_PAGES, numberOfPages);
        return skip;
    }
}
