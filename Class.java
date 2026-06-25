import java.util.Scanner;
public class Class {
    int name;
    int age;
    int ID;

    Class(int name, int age, int ID) {
        this.name = name;
        this.age = age;
        this.ID = ID;
    }
    void getinfo( String name, int age, int ID) {
        System.out.println("Name: ");
        String name = sc.nextLine();
        System.out.println("Age: ");
        int age = sc.nextInt();
        System.out.println("ID: " );
        int ID= sc.nextInt();
    }

    void displayinfo(){
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("ID: " + ID);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Class obj = new Class(Renu, 19 , 43);
        obj.getinfo();
        obj.displayinfo();
    }
}
