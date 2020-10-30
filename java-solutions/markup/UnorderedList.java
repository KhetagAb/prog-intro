package markup;

import java.util.List;

public class UnorderedList extends AbstractList {
    private static final String BBCODE_TAG = "list";
    public UnorderedList(List<ListItem> elements) {
       super(elements);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        super.toBBCode(sb, BBCODE_TAG);
    }
}
