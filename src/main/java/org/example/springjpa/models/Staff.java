package org.example.springjpa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.util.ObjectUtils;

@Getter
@Setter
@Entity
@Table(name = "staff")
@NoArgsConstructor
public class Staff {
    @Id
    @Column(name = "staff_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 60)
    @Column(name = "address", length = 60)
    private String address;

    @Column(name = "age")
    private Integer age;

    @Size(max = 30)
    @NotNull
    @Column(name = "\"position\"", nullable = false, length = 30)
    private String position;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Staff(String name, String address, Integer age, String position, Department department) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.position = position;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", position='" + position + '\'' +
                ", department=" + ObjectUtils.nullSafeClassName(department) +
                '}';
    }
}