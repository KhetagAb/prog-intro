package markup;

import java.util.List;

public class UnorderedList extends AbstractElement implements ListAble {
    public UnorderedList(List<ListItem> elements) {
       super(elements);
    }

    @Override
    protected String getBBTag(boolean isOpenTag) {
        if (isOpenTag) {
            return "[list]";
        } else {
            return "[/list]";
        }
    }

    @Override
    protected String getMarkdownTag() {
        throw new UnsupportedOperationException("UnorderedList doesn't support getMarkdownTag!");
    }
}
