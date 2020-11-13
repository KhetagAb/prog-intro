package markup;

import java.util.List;

public class ListItem implements BBCodeAble {
    private final List<ListAble> elements;

    public ListItem(List<ListAble> elements) {
        this.elements = elements;
    }

    @Override
    public String getBBTag() {
        return "[*]";
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append(getBBTag());
        for (ListAble e: elements) {
            e.toBBCode(sb);
        }
    }
}
