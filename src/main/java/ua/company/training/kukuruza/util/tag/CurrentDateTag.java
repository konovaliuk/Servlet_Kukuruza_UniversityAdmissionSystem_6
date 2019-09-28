package ua.company.training.kukuruza.util.tag;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CurrentDateTag extends SimpleTagSupport {
    @Override
    public void doTag() throws IOException {
        getJspContext().getOut().print(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }
}
