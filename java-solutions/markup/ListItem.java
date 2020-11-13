package markup;

import java.util.List;

public class ListItem extends AbstractElement {
    public ListItem(List<ListAble> elements) {
        super(elements);
    }

    @Override
    protected String getBBTag(boolean isOpenTag) {
        if (isOpenTag) {
            return "[*]";
        } else {
            return "";
        }
    }

    @Override
    protected String getMarkdownTag() {
        throw new UnsupportedOperationException("ListItem doesn't support toMarkdown!");
    }
}
