package az.orient.bankboot55.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "customer")
@Entity
@DynamicInsert
public class Customer {

    //primary key etmek bu annotation yazilmalidir
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment ucun yazilir
    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Date dob;
    private String cif;
    private String pin;
    private String seria;
    @CreationTimestamp
    private Date dataDate;
    @ColumnDefault(value = "1")
    private Integer active;


}
