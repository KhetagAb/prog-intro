package markup;

public interface Element {
    void toBBCode(StringBuilder sb);
    void toMarkdown(StringBuilder sb);
}
