package markup;

import java.util.List;

public class Strong extends AbstractElement implements ParagraphAble {
    public Strong(List<ParagraphAble> elements) {
        super(elements);
    }

    @Override
    protected String getBBTag(boolean isOpenTag) {
        if (isOpenTag) {
            return "[b]";
        } else {
            return "[/b]";
        }
    }

    @Override
    protected String getMarkdownTag() {
        return "__";
    }
}
