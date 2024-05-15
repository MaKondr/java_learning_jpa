package org.example.springjpa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.util.ObjectUtils;

@Getter
@Setter
@Entity
@Table(name = "department")
@NoArgsConstructor
public class Department {
    @Id
    @Column(name = "department_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 60)
    @NotNull
    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @NotNull
    @Column(name = "amount_staff", nullable = false)
    private Integer amountStaff;

    @Size(max = 60)
    @Column(name = "rooms", length = 60)
    private String rooms;

    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "ceo_id")
    private Staff ceo;

    public Department(String name, int employeeAmount, String rooms, Staff staff) {
        this.name = name;
        this.amountStaff = employeeAmount;
        this.rooms = rooms;
        this.ceo = staff;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amountStaff=" + amountStaff +
                ", rooms='" + rooms + '\'' +
                ", ceo=" + ObjectUtils.nullSafeClassName(ceo) +
                '}';
    }
}