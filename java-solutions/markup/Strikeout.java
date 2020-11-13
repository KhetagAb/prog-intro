package markup;

import java.util.List;

public class Strikeout extends AbstractElement implements ParagraphAble {
    public Strikeout(List<ParagraphAble> elements) {
        super(elements);
    }

    @Override
    public String getBBTag() {
        return "s";
    }

    @Override
    public String getMarkdownTag() {
        return "~";
    }
}
