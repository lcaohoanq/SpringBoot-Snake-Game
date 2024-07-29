package com.lcaohoanq.Spring_Snake_Game.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lcaohoanq.Spring_Snake_Game.util.PBKDF2;
import com.lcaohoanq.Spring_Snake_Game.util.ValidatorUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull //JSR-380 annotation
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Email(message = "Email should be valid")
    @Column(name = "email", unique = true)
    private String email;

    @Pattern(regexp = "(84|0[3|5|7|8|9])[0-9]{8}", message = "Phone number should be 10 digits, Viet Nam format")
    @Column(name = "phone", unique = true)
    private String phone;

    @NotNull
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one digit and one special character")
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name="role", nullable = false)
    private int role;

    @NotNull
    @Column(name="status", nullable = false)
    private int status;

    @NotNull
    @Column(name="created_at", nullable = false)
    private String created_at;

    @NotNull
    @Column(name="updated_at", nullable = false)
    private String updated_at;

    @Lob
    @Column(name="avatar_url", length = 1000000)
    private byte[] avatar_url;

    @NotNull
    @Column(name="subscription", nullable = false)
    private int subscription;

    @Transient
    private String confirmPassword;

    public User(Long id, String firstName, String lastName, String email, String phone, String password, int role, int status, String created_at, String updated_at, byte[] avatar_url, int subscription) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.avatar_url = avatar_url;
        this.subscription = subscription;
    }

    public static void main(String[] args) {
        User user = new User(1L, "hoang", "luu", "hoangdz1604@gmail.com",
            "0987654321", new PBKDF2().hash("Iloveyou123!".toCharArray()), 1, 1, LocalDateTime.now().toString(), LocalDateTime.now().toString(), null, 0);
        try {
            ValidatorUtil.validate(user);
            System.out.println("User is valid");
            System.out.println(user);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
