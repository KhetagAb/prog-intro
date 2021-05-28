package markup;

import java.util.List;

public class Strikeout extends AbstractElement implements ParagraphAble {
    public Strikeout(List<ParagraphAble> elements) {
        super(elements);
    }

    @Override
    protected String getBBTag(boolean isOpenTag) {
        if (isOpenTag) {
            return "[s]";
        } else {
            return "[/s]";
        }
    }

    @Override
    protected String getMarkdownTag() {
        return "~";
    }
}
