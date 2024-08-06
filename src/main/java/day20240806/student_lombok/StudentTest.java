package day20240806.student_lombok;

public class StudentTest {
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student("name", 18);
        s1.setName("qq");
        s1.setAge(12);
        System.out.println(s2.getName());
        System.out.println(s2.getAge());
    }
}
