package markup;

import java.util.List;

public class ListItem implements BBCodeAble {
    private final List<ListAble> elements;

    public ListItem(List<ListAble> elements) {
        this.elements = elements;
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append("[*]");
        for (ListAble e: elements) {
            e.toBBCode(sb);
        }
    }
}
