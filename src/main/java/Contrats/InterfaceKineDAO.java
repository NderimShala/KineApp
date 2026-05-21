package Contrats;

import java.util.Optional;

public interface InterfaceKineDAO {

    public Optional<InterfaceUtilisateur> findByUsername(String username);
    public boolean existsByUsername(String username);
    public void save(String username, String password, InterfaceUtilisateur kine);
    public boolean checkPassword(String username, String password);
    public String getPassword(String username);
}
