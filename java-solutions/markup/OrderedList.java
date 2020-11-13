package markup;

import java.util.List;

public class OrderedList extends AbstractList implements ListAble {
    public OrderedList(List<ListItem> elements) {
        super(elements);
    }

    @Override
    public String getBBTag() {
        return "list=1";
    }
}
