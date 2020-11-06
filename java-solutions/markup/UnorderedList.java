package markup;

import markup.interfeces.ListAble;

import java.util.List;

public class UnorderedList extends AbstractList {
    public UnorderedList(List<ListItem> elements) {
       super(elements);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        super.toBBCode(sb, "list");
    }
}
