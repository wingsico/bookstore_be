package org.wingsico.bookstore.domain;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.wingsico.bookstore.annotation.NameUnique;
import org.wingsico.bookstore.annotation.PasswordInformation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * User实体类
 *
 */
@Entity
@Table(name = "users")
@Data
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "用户名不能为空")
    @NameUnique
    private String userName;

    @Column(nullable = false)
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    @Column(nullable = false)
    @NotBlank(message = "密码不能为空")
    @PasswordInformation
    private String password;
}
