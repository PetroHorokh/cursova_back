package main.cursova_integrate.DAL.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="Users")
public class User {
    @Id
    @Column(name = "UserId")
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID UserId;
    @Column(name = "Login")
    public String Login;
    @Column(name = "Email")
    public String Email;
    @Column(name = "Password")
    public String Password;
    @Column(name = "Phone")
    public String Phone;
    @Column(name = "BirthDate")
    public Timestamp BirthDate;
}
