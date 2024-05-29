package main.cursova_integrate.DAL.Repositories;

import main.cursova_integrate.DAL.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {}
