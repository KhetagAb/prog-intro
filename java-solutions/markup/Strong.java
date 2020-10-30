package markup;

import markup.interfeces.ParagraphAble;

import java.util.List;

public class Strong extends AbstractMark {
    public Strong(List<ParagraphAble> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        super.toMarkdown(sb, "__", "__");
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        super.toBBCode(sb, "[b]", "[/b]");
    }
}
