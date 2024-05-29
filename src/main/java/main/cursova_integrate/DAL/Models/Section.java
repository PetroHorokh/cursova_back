package main.cursova_integrate.DAL.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="Sections")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID SectionId;
    public UUID UserId;
    public String Title;
    public String Details;
    public Timestamp Timestamp;
}
