package expression.parser;

import expression.exceptions.ParserException;

public interface ExpressionSource {
    boolean hasNext();
    char next();

    boolean hasNext(int forward);
    char getNext(int forward);

    ParserException error(String message);
}
