package main.cursova_integrate.DAL.Repositories;

import main.cursova_integrate.DAL.Models.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ISectionRepository  extends JpaRepository<Section, UUID> {}
