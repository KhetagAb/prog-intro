package markup;

import markup.interfeces.ParagraphAble;

import java.util.List;

public class Strong extends AbstractMark {
    private final static String MARKDOWN_TAG = "__";
    private final static String BBCODE_TAG = "b";

    public Strong(List<ParagraphAble> elements) {
        super(elements);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        super.toMarkdown(sb, MARKDOWN_TAG);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        super.toBBCode(sb, BBCODE_TAG);
    }
}
