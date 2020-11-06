package markup;

import java.util.List;

public class OrderedList extends AbstractList implements ListAble {
    public OrderedList(List<ListItem> elements) {
        super(elements);
    }

    @Override
    protected String getListTag() {
        return "list=1";
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        super.toBBCode(sb);
    }
}
