public class ExceptionDemo {
    public static void main(String[] args) {

        try {
            int[] arr = new int[5];
            arr[10] = 50;  // This will throw ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception caught: " + e);
        } finally {
            System.out.println("Finally block executed.");
        }
        System.out.println("Program continues...");
        
    }
}

