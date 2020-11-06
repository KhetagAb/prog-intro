package markup;

import java.util.List;

public class UnorderedList extends AbstractList implements ListAble {
    public UnorderedList(List<ListItem> elements) {
       super(elements);
    }

    @Override
    protected String getListTag() {
        return "list";
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        super.toBBCode(sb);
    }
}
