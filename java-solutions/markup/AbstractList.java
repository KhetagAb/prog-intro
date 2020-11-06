package markup;

import java.util.List;

public abstract class AbstractList {
    protected final List<ListItem> elements;

    protected AbstractList(List<ListItem> elements) {
        this.elements = elements;
    }

    protected abstract String getListTag();

    protected void toBBCode(StringBuilder sb) {
        sb.append('[').append(getListTag()).append(']');
        for (ListItem e: elements) {
            e.toBBCode(sb);
        }
        sb.append("[/list]");
    }
}
