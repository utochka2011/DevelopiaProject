package com.example.developia.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    @Size(min = 3, max = 50, message = "Имя пользователя должно быть от 3 до 50 символов")
    private String username;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(message = "Пароль должен содержать минимум 6 символов")
    private String password;

    private boolean enabled;

    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Некорректный формат email")
    private String email;

    @NotBlank(message = "Номер телефона не должен быть пустым")
    @Pattern(
    	    regexp = "^\\+994\\s?(50|51|55|70|77)[\\s\\-]?\\d{3}[\\s\\-]?\\d{2}[\\s\\-]?\\d{2}$",
    	    message = "Invalid phone number format"
    	)
    private String phoneNumber;

    @NotBlank(message = "Фамилия не должна быть пустой")
    @Column(length = 5134)
    private String surname;

}
