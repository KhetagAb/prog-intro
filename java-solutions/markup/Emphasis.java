package markup;

import java.util.List;

public class Emphasis extends AbstractElement implements ParagraphAble {
    public Emphasis(List<ParagraphAble> elements) {
        super(elements);
    }

    @Override
    protected String getBBTag(boolean isOpenTag) {
        if (isOpenTag) {
            return "[i]";
        } else {
            return "[/i]";
        }
    }

    @Override
    protected String getMarkdownTag() {
        return "*";
    }
}