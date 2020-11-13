package markup;

public class Text implements ParagraphAble {
    protected final String text;//NOTE: private

    public Text(String text) {
        this.text = text;
    }

    @Override
    public String getBBTag() {
        return "";
    }

    @Override
    public String getMarkdownTag() {
        return "";
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(text);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        sb.append(text);
    }
}

