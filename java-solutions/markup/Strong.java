package markup;

import markup.interfeces.BBCodeAble;
import markup.interfeces.MarkdownAble;
import markup.interfeces.ParagraphAble;

import java.util.List;

public class Strong extends AbstractElement implements ParagraphAble {
    public Strong(List<ParagraphAble> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        super.toMarkdown(sb, "__");
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        super.toBBCode(sb, "b");
    }
}
