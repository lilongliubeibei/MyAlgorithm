package easy.day6;

//纸片折痕问题
public class PaperFolding {
    public static void printAllFolds(int N) {
        printProcess(1, N, true);
    }

    public static void printProcess(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        printProcess(i + 1, N, true);
        System.out.println(down ? "down " : "up ");
        printProcess(i + 1, N, false);
    }

    public static void main(String[] args) {
        int N = 1;
        printAllFolds(N);
    }
}
