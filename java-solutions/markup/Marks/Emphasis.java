package markup.Marks;

import markup.Interfeces.BBCodeAble;
import markup.Interfeces.MarkdownAble;
import markup.Interfeces.ParagraphAble;

import java.util.List;

public class Emphasis extends AbstractMark {
    public Emphasis(List<ParagraphAble> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        super.toMarkdown(sb, "*", "*");
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        super.toBBCode(sb, "[i]", "[/i]");
    }
}