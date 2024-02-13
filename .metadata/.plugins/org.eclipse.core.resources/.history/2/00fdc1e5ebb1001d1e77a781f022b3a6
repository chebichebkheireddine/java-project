public class StudentDAOImpl implements StudentDAO {
    private Connection conn;

    public StudentDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void create(Student student) {
        String sql = "INSERT INTO students(name, moy) VALUES(?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setFloat(2, student.getMoy());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student read(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                float moy = rs.getFloat("moy");
                Student student = new Student();
                student.setId(id);
                student.setName(name);
                student.setMoy(moy);
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Student student) {
        String sql = "UPDATE students SET name = ?, moy = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getName());
            stmt.setFloat(2, student.getMoy());
            stmt.setInt(3, student.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
