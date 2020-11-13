package markup;

import java.util.List;

public class UnorderedList extends AbstractList implements ListAble {
    public UnorderedList(List<ListItem> elements) {
       super(elements);
    }

    @Override
    public String getBBTag() {
        return "list";
    }
}
