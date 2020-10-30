package markup;

import java.util.List;

public class OrderedList extends AbstractList {
    private static final String BBCODE_TAG = "list=1";

    public OrderedList(List<ListItem> elements) {
        super(elements);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        super.toBBCode(sb, BBCODE_TAG);
    }
}
