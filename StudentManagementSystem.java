import java.util.*;

//  Student Class (Encapsulation)
class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getMarks() { return marks; }

    public void display() {
        System.out.println("ID: " + id + " | Name: " + name + " | Marks: " + marks);
    }
}

//  Sorting Algorithms 
class Sorting {
    // Bubble Sort by Marks
    public static void bubbleSort(List<Student> list) {
        int n = list.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (list.get(j).getMarks() > list.get(j+1).getMarks()) {
                    Collections.swap(list, j, j+1);
                }
            }
        }
    }

    // Quick Sort by Name
    public static void quickSort(List<Student> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi-1);
            quickSort(list, pi+1, high);
        }
    }

    private static int partition(List<Student> list, int low, int high) {
        String pivot = list.get(high).getName();
        int i = (low-1);
        for (int j = low; j < high; j++) {
            if (list.get(j).getName().compareToIgnoreCase(pivot) <= 0) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i+1, high);
        return i+1;
    }
}

// Searching Algorithms 
class Searching {
    // Linear Search by ID
    public static Student linearSearch(List<Student> list, int id) {
        for (Student s : list) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    // Binary Search by Name (List must be sorted by name)
    public static Student binarySearchByName(List<Student> list, String name) {
        int low = 0, high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = list.get(mid).getName().compareToIgnoreCase(name);
            if (cmp == 0) return list.get(mid);
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }
}

//  Main Class 
public class StudentManagementSystem {
    private static List<Student> students = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n==== Student Management System ====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search by ID (Linear Search)");
            System.out.println("4. Search by Name (Binary Search)");
            System.out.println("5. Sort by Marks (Bubble Sort)");
            System.out.println("6. Sort by Name (Quick Sort)");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1: addStudent(); break;
                case 2: viewStudents(); break;
                case 3: searchById(); break;
                case 4: searchByName(); break;
                case 5: Sorting.bubbleSort(students);
                        System.out.println("Sorted by marks!");
                        break;
                case 6: Sorting.quickSort(students, 0, students.size()-1);
                        System.out.println("Sorted by name!");
                        break;
                case 0: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while(choice != 0);
    }

    private static void addStudent() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();
        students.add(new Student(id, name, marks));
        System.out.println("Student added successfully!");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        for (Student s : students) s.display();
    }

    private static void searchById() {
        System.out.print("Enter ID to search: ");
        int id = sc.nextInt();
        Student s = Searching.linearSearch(students, id);
        if (s != null) s.display();
        else System.out.println("Student not found!");
    }

    private static void searchByName() {
        System.out.print("Enter name to search: ");
        String name = sc.nextLine();
        Sorting.quickSort(students, 0, students.size()-1);
        Student s = Searching.binarySearchByName(students, name);
        if (s != null) s.display();
        else System.out.println("Student not found!");
    }
}
