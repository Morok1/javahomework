public class Test {
    public static void main(String[] args) {
        Employee from = new Employee("Alex", 32, "Programer");
        Person to = new Person("Noname", 0);

        System.out.println("\nСвойства класса получателя до assign: ");
        System.out.println("name: "  + to.getName());
        System.out.println("age: "  + to.getAge());

        BeanUtils.assign(to, from);

        System.out.println("\nСвойства класса получателя после assign: ");
        System.out.println("name: "  + to.getName());
        System.out.println("age: "  + to.getAge());
    }

}




