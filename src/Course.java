public class Course {
    private String name;
    public Course(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    // TODO: Task 4 — Обязательно переопредели (иначе HashMap не будет работать!)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course other = (Course) o;
        if (name == null) {
            return other.name == null;
        }
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name == null ? 0 : name.hashCode();
    }

    @Override
    public String toString() {
        return "Course: " + name;
    }
}