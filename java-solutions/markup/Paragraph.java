package markup;

import markup.Interfeces.BBCodeAble;
import markup.Interfeces.ListAble;
import markup.Interfeces.MarkdownAble;
import markup.Interfeces.ParagraphAble;
import markup.Marks.AbstractMark;

import java.util.List;

public class Paragraph extends AbstractMark implements ListAble {
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
