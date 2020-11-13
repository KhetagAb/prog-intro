package markup;

import java.util.List;

public class Paragraph extends AbstractElement implements ListAble, MarkdownAble, BBCodeAble {
    public Paragraph(List<ParagraphAble> elements) {
        super(elements);
    }

    @Override
    public String getBBTag() {
        return "";
    }

    @Override
    public String getMarkdownTag() {
        return "";
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        for (ParagraphAble e: elements) {
            e.toMarkdown(sb);
        }
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        for (ParagraphAble e: elements) {
            e.toBBCode(sb);
        }
    }
}
