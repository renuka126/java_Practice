import java.util.HashMap;

public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<String, Integer> marks = new HashMap<>();

        marks.put("Renuka", 85);
        marks.put("Aditya", 78);
        marks.put("Sneha", 92);

        System.out.println(marks); // print whole map

        System.out.println("Sneha's marks: " + marks.get("Sneha"));

        for (String key : marks.keySet()) {
            System.out.println(key + " -> " + marks.get(key));
        }
    }
}