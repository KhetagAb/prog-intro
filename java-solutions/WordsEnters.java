public class WordsEnters {
    private final IntList entries = new IntList();
    private int lastEntry = -1;

    public int countOfWords = 0;

    public void tryPushEntry(int row, int numInLine) {
        if (lastEntry != row) {
            lastEntry = row;

            entries.pushBack(numInLine);
        }

        countOfWords++;
    }

    public IntList getEntries() {
        return entries;
    }
}