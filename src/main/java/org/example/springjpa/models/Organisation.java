package org.example.springjpa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

@Getter
@Setter
@Entity
@Table(name = "organisation")
@NoArgsConstructor
public class Organisation {
    @Id
    @Column(name = "organisation_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 60)
    @NotNull
    @Column(name = "address", nullable = false, length = 60)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ceo_id")
    private Staff ceo;

    public Organisation(String name, String address, Staff ceo) {
        this.name = name;
        this.address = address;
        this.ceo = ceo;
    }

    @Override
    public String toString() {
        return "Organisation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", ceo=" + ObjectUtils.nullSafeClassName(ceo) +
                '}';
    }
}