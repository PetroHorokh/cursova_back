package main.cursova_integrate.DAL.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="Records")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID RecordId;
    public UUID SectionId;
    public String Title;
    public String Text;
    public Timestamp Timestamp;
}
