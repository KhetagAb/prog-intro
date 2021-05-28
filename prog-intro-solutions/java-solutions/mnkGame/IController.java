package mnkGame;

public interface IController {
    int intInputFor(String name);
    void showPosition(IPosition position);

    void showMsg(String msg);
    void close();
}
