import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        HashMap<String, Student> students = new HashMap<>();

        // ====================== TASK 1 ======================
        // TODO: Добавь минимум 5 студентов (ключ = ID)
        // Сделай минимум два студента с одинаковым GPA (для Task 3)
        students.put("S001", new Student("Ali", 3.5, 20));
        students.put("S002", new Student("Sara", 3.9, 22));
        students.put("S003", new Student("Yusuf", 3.5, 21));
        students.put("S004", new Student("Fatima", 3.8, 19));
        students.put("S005", new Student("Bek", 3.2, 23));
        students.put("S006", new Student("Aigerim", 4.0, 20));

        // TODO: Напечатай всех студентов (ID + объект)
        System.out.println("=== All Students ===");
        for (String id : students.keySet()) {
            System.out.println(id + " → " + students.get(id));
        }

        // TODO: Найди студента по ID и выведи его
        System.out.println("\n=== Find by ID ===");
        Student found = students.get("S002");
        if (found != null) {
            System.out.println("Found: " + found);
        }

        // TODO: Удали одного студента по ID
        System.out.println("\n=== Remove Student ===");
        students.remove("S005");
        System.out.println("After removal: " + students.size() + " students");

        // TODO: Обнови GPA у одного студента
        System.out.println("\n=== Update GPA ===");
        Student ali = students.get("S001");
        if (ali != null) {
            ali.setGpa(3.7);
            System.out.println("Updated: " + ali);
        }

        // ====================== SORTING (IMPORTANT) ======================
        // TODO: Создай ArrayList из всех студентов (students.values())
        ArrayList<Student> studentList = new ArrayList<>(students.values());

        // TODO 6a: Отсортируй по GPA (natural ordering) и выведи
        System.out.println("\n=== Sorted by GPA (ascending) ===");
        Collections.sort(studentList);
        for (Student s : studentList) {
            System.out.println(s);
        }

        // TODO 6b: Отсортируй по имени (Comparator) и выведи
        System.out.println("\n=== Sorted by Name ===");
        Collections.sort(studentList, new NameSorter());
        for (Student s : studentList) {
            System.out.println(s);
        }

        // ====================== TASK 2 ======================
        System.out.println("\n=== Task 2: Top 3 by GPA ===");
        // TODO: Создай новый список, отсортируй по GPA по убыванию, выведи первые 3
        ArrayList<Student> topList = new ArrayList<>(students.values());
        Collections.sort(topList, new GpaDescSorter());
        int count = 0;
        for (Student s : topList) {
            if (count >= 3) break;
            System.out.println(s);
            count++;
        }

        // ====================== TASK 3 ======================
        System.out.println("\n=== Task 3: Students with same GPA ===");
        // TODO: Сгруппируй студентов по GPA и выведи только те, где больше 1 студента
        HashMap<Double, List<String>> groups = new HashMap<>();
        for (Student s : students.values()) {
            groups.putIfAbsent(s.getGpa(), new ArrayList<>());
            groups.get(s.getGpa()).add(s.getName());
        }
        for (Double gpa : groups.keySet()) {
            List<String> names = groups.get(gpa);
            if (names.size() > 1) {
                System.out.println("GPA " + gpa + " → " + String.join(", ", names));
            }
        }

        // ====================== TASK 4 ======================
        System.out.println("\n=== Task 4: Courses ===");
        HashMap<Course, List<Student>> courseMap = new HashMap<>();
        // TODO: Создай 2–3 курса, добавь студентов, выведи всё
        Course javaCourse = new Course("Java Programming");
        Course mathCourse = new Course("Discrete Math");
        Course dbCourse = new Course("Databases");

        courseMap.put(javaCourse, new ArrayList<>());
        courseMap.get(javaCourse).add(students.get("S001"));
        courseMap.get(javaCourse).add(students.get("S003"));
        courseMap.get(javaCourse).add(students.get("S006"));

        courseMap.put(mathCourse, new ArrayList<>());
        courseMap.get(mathCourse).add(students.get("S002"));
        courseMap.get(mathCourse).add(students.get("S004"));

        courseMap.put(dbCourse, new ArrayList<>());
        courseMap.get(dbCourse).add(students.get("S001"));
        courseMap.get(dbCourse).add(students.get("S004"));

        for (Course course : courseMap.keySet()) {
            System.out.println(course.getName() + ":");
            for (Student s : courseMap.get(course)) {
                System.out.println("  - " + s.getName());
            }
        }

        // ====================== TASK 5 ======================
        System.out.println("\n=== Task 5: GPA desc + Name ===");
        // TODO: Создай Comparator (GPA убывание → если равно, то имя возрастание) и отсортируй
        ArrayList<Student> finalList = new ArrayList<>(students.values());
        Collections.sort(finalList, new ComplexSorter());
        for (Student s : finalList) {
            System.out.println(s);
        }
    }
}

class NameSorter implements Comparator<Student> {
    public int compare(Student a, Student b) {
        return a.getName().compareTo(b.getName());
    }
}

class GpaDescSorter implements Comparator<Student> {
    public int compare(Student a, Student b) {
        return Double.compare(b.getGpa(), a.getGpa());
    }
}

class ComplexSorter implements Comparator<Student> {
    public int compare(Student a, Student b) {
        int gpaCompare = Double.compare(b.getGpa(), a.getGpa());
        if (gpaCompare != 0) {
            return gpaCompare;
        }
        return a.getName().compareTo(b.getName());
    }
}