package markup;

import java.util.List;

public abstract class AbstractElement {
    protected final List<ParagraphAble> elements;

    protected AbstractElement(List<ParagraphAble> elements) {
        this.elements = elements;
    }

    protected abstract String getBBTag();
    protected abstract String getMarkdownTag();

    protected void toMarkdown(StringBuilder sb) {
        sb.append(getMarkdownTag());
        for (ParagraphAble e: elements) {
            e.toMarkdown(sb);
        }
        sb.append(getMarkdownTag());
    }

    protected void toBBCode(StringBuilder sb) {
        sb.append('[').append(getBBTag()).append(']');
        for (ParagraphAble e: elements) {
            e.toBBCode(sb);
        }
        sb.append("[/").append(getBBTag()).append(']');
    }
}
