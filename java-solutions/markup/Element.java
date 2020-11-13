package markup;

interface Element {
    void toBBCode(StringBuilder sb);
    void toMarkdown(StringBuilder sb);
}
