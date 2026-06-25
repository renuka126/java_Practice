class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Override toString()
    @Override
    public String toString() {
        return "Person[name=" + name + ", age=" + age + "]";
    }

    // Override equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person p = (Person) obj;
        return age == p.age && name.equals(p.name);
    }

    // Override hashCode()
    @Override
    public int hashCode() {
        return name.hashCode() + age;
    }
}

public class ObjectMethodsDemo {
    public static void main(String[] args) {
        Person p1 = new Person("Alice", 30);
        Person p2 = new Person("Alice", 30);
        Person p3 = new Person("Bob", 25);

        System.out.println(p1.toString());  // Person[name=Alice, age=30]
        System.out.println("p1 equals p2? " + p1.equals(p2));  // true
        System.out.println("p1 equals p3? " + p1.equals(p3));  // false
        System.out.println("p1 hashCode: " + p1.hashCode());
        System.out.println("p2 hashCode: " + p2.hashCode());
        System.out.println("p3 hashCode: " + p3.hashCode());
    }
}