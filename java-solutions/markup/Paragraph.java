package markup;

import markup.interfeces.BBCodeAble;
import markup.interfeces.ListAble;
import markup.interfeces.MarkdownAble;
import markup.interfeces.ParagraphAble;

import java.util.List;

public class Paragraph extends AbstractElement implements MarkdownAble, BBCodeAble {
    public Paragraph(List<ParagraphAble> elements) {
        super(elements);
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
