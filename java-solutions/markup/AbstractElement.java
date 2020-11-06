package markup;

import markup.interfeces.ParagraphAble;

import java.util.List;

public abstract class AbstractElement {
    protected final List<ParagraphAble> elements;

    protected AbstractElement(List<ParagraphAble> elements) {
        this.elements = elements;
    }

    protected void toMarkdown(StringBuilder sb, String tag) {
        sb.append(tag);
        for (ParagraphAble e: elements) {
            e.toMarkdown(sb);
        }
        sb.append(tag);
    }

    protected void toBBCode(StringBuilder sb, String tag) {
        sb.append('[').append(tag).append(']');
        for (ParagraphAble e: elements) {
            e.toBBCode(sb);
        }
        sb.append("[/").append(tag).append(']');
    }
}
