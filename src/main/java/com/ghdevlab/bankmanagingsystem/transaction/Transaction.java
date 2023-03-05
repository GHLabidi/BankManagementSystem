package com.ghdevlab.bankmanagingsystem.transaction;

import jakarta.persistence.*;
import com.ghdevlab.bankmanagingsystem.client.Client;

import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Client sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Client receiver;



    private String type;
    private Double amount;
    private Date date;

    public Transaction() {

        }

    public Transaction(Client sender, Client receiver, String type,Double amount, Date date) {
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(Client sender, String type, Double amount, Date date) {
        this.sender = sender;
        this.receiver = null;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getSender() {
        return sender;
    }

    public void setSender(Client sender) {
        this.sender = sender;
    }

    public Client getReceiver() {
        return receiver;
    }

    public void setReceiver(Client receiver) {
        this.receiver = receiver;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
