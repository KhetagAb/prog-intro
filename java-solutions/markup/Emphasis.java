package markup;

import java.util.List;

public class Emphasis extends AbstractElement implements ParagraphAble {
    public Emphasis(List<ParagraphAble> elements) {
        super(elements);
    }

    @Override
    protected String getBBTag() {
        return "i";
    }

    @Override
    protected String getMarkdownTag() {
        return "*";
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