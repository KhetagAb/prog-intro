package markup.list;

import java.util.List;

public class OrderedList extends AbstractList {
    public OrderedList(List<ListItem> elements) {
        super(elements);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        super.toBBCode(sb, "[list=1]", "[/list]");
    }
}
