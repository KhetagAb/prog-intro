package markup.marks;

import markup.interfeces.ParagraphAble;

import java.util.List;

public abstract class AbstractMark implements ParagraphAble {
    protected final List<ParagraphAble> elements;

    protected AbstractMark(List<ParagraphAble> elements) {
        this.elements = elements;
    }

    protected void toMarkdown(StringBuilder sb, String openBoarder, String closeBoarder) {
        sb.append(openBoarder);
        for (ParagraphAble e: elements) {
            e.toMarkdown(sb);
        }
        sb.append(closeBoarder);
    }

    protected void toBBCode(StringBuilder sb, String openBoarder, String closeBoarder) {
        sb.append(openBoarder);
        for (ParagraphAble e: elements) {
            e.toBBCode(sb);
        }
        sb.append(closeBoarder);
    }
}
