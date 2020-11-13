package markup;

import java.util.List;

abstract class AbstractElement implements Element {
    private final List<? extends Element> elements;

    protected AbstractElement(List<? extends Element> elements) {
        this.elements = elements;
    }

    protected abstract String getBBTag(boolean isOpenTag);
    protected abstract String getMarkdownTag();

    public void toMarkdown(StringBuilder sb) {
        sb.append(getMarkdownTag());
        for (Element e: elements) {
            e.toMarkdown(sb);
        }
        sb.append(getMarkdownTag());
    }

    public void toBBCode(StringBuilder sb) {
        sb.append(getBBTag(true));
        for (Element e: elements) {
            e.toBBCode(sb);
        }
        sb.append(getBBTag(false));
    }
}
