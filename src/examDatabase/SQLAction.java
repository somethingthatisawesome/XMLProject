package examDatabase;

public interface SQLAction {
public boolean insert();
public boolean remove();
public boolean update();
public boolean findByID(int ID);
}
