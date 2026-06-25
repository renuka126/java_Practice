public class MultithreadDemod {
    public static void main(String[] args) {
        Thread t1 = new NumberThread();
        Thread t2 = new AlphabetThread();
        Thread t3 = new SymbolThread();

        t1.start();
        t2.start();
        t3.start();
    }
}
class NumberThread extends Thread {
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
        System.out.println();
    }
}

class AlphabetThread extends Thread {
    public void run() {
        for (char c = 'A'; c <= 'J'; c++) {
            System.out.print(c + " ");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
        System.out.println();
    }
}

class SymbolThread extends Thread {
    char[] symbols = {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')'};

    public void run() {
        for (char c : symbols) {
            System.out.print(c + " ");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
        }
        System.out.println();
    }
}

