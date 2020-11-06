package markup;

import java.util.List;

public class Strong extends AbstractElement implements ParagraphAble {
    public Strong(List<ParagraphAble> elements) {
        super(elements);
    }

    @Override
    protected String getBBTag() {
        return "b";
    }

    @Override
    protected String getMarkdownTag() {
        return "__";
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        super.toMarkdown(sb);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        super.toBBCode(sb);
    }
}
