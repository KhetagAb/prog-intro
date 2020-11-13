package markup;

import java.util.List;

public class OrderedList extends AbstractElement implements ListAble {
    public OrderedList(List<ListItem> elements) {
        super(elements);
    }

    @Override
    protected String getBBTag(boolean isOpenTag) {
        if (isOpenTag) {
            return "[list=1]";
        } else {
            return "[/list]";
        }
    }

    @Override
    protected String getMarkdownTag() {
        throw new UnsupportedOperationException("OrderedList doesn't support getMarkdownTag!");
    }
}
