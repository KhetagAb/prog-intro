package markup;

import markup.Interfeces.BBCodeAble;
import markup.Interfeces.MarkdownAble;
import markup.Interfeces.ParagraphAble;

import java.util.List;

public class Text implements ParagraphAble {
    final String text;

    public Text(String text) {
        this.text = text;
    }

    private void formatText(StringBuilder sb) {
        sb.append(text);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        formatText(sb);
    }

    @Override
    public void toBBCode(StringBuilder sb) {
        formatText(sb);
    }
}

