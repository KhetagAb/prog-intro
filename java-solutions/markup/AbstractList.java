package markup;

import java.util.List;

public abstract class AbstractList {
    protected final List<ListItem> elements;

    protected AbstractList(List<ListItem> elements) {
        this.elements = elements;
    }

    protected abstract String getBBTag();

    public void toBBCode(StringBuilder sb) {
        sb.append('[').append(getBBTag()).append(']');
        for (ListItem e: elements) {
            e.toBBCode(sb);
        }
        sb.append("[/list]");
    }
}
