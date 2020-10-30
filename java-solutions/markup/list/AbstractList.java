package markup.list;

import markup.interfeces.BBCodeAble;
import markup.interfeces.ListAble;

import java.util.List;

public abstract class AbstractList implements ListAble {
    protected final List<ListItem> elements;

    protected AbstractList(List<ListItem> elements) {
        this.elements = elements;
    }

    protected void toBBCode(StringBuilder sb, String openBorder, String closeBorder) {
        sb.append(openBorder);
        for (ListItem e: elements) {
            ((BBCodeAble) e).toBBCode(sb);
        }
        sb.append(closeBorder);
    }
}
