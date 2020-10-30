package markup;

import markup.interfeces.BBCodeAble;
import markup.interfeces.ListAble;

import java.util.List;

public abstract class AbstractList implements ListAble {
    protected final List<ListItem> elements;

    protected AbstractList(List<ListItem> elements) {
        this.elements = elements;
    }

    protected void toBBCode(StringBuilder sb, String tag) {
        sb.append('[').append(tag).append(']');
        for (ListItem e: elements) {
            e.toBBCode(sb);
        }
        sb.append("[/list]");
    }
}
