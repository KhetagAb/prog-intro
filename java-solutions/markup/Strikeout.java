package markup;

import java.util.List;

public class Strikeout extends AbstractElement implements ParagraphAble {
    public Strikeout(List<ParagraphAble> elements) {
        super(elements);
    }

    @Override
    protected String getBBTag() {
        return "s";
    }

    @Override
    protected String getMarkdownTag() {
        return "~";
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
