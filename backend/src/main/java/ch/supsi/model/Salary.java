package ch.supsi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double amount;
    private Date start;
    private Date end;
    private Date lastUpdate;

    public Salary(){
        lastUpdate = new Date();
    }

    public void setAmount(double amount){
        if(amount >= 0){
            this.amount = amount;
            lastUpdate = new Date();
        }
    }
}
