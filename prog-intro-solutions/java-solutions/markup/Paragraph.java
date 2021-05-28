package markup;

import java.util.List;

public class Paragraph extends AbstractElement implements ListAble {
    public Paragraph(List<ParagraphAble> elements) {
        super(elements);
    }

    @Override
    protected String getBBTag(boolean isOpen) {
        return "";
    }

    @Override
    protected String getMarkdownTag() {
        return "";
    }
}
