package markup;

import java.util.List;

public class Strong extends AbstractElement implements ParagraphAble {
    public Strong(List<ParagraphAble> elements) {
        super(elements);
    }

    @Override
    public String getBBTag() {
        return "b";
    }

    @Override
    public String getMarkdownTag() {
        return "__";
    }
}
