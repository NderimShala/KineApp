package Contrats;

import java.util.Optional;
import entity.Kine;

public interface InterfaceKineDAO {

    public Optional<Kine> findByUsername(String username);
    public boolean existsByUsername(String username);
    public void save(String username, String password, Kine kine);
    public boolean checkPassword(String username, String password);
    public String getPassword(String username);
}
