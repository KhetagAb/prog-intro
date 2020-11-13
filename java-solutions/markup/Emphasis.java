package markup;

import java.util.List;

public class Emphasis extends AbstractElement implements ParagraphAble {
    public Emphasis(List<ParagraphAble> elements) {
        super(elements);
    }

    @Override
    public String getBBTag() {
        return "i";
    }

    @Override
    public String getMarkdownTag() {
        return "*";
    }
}