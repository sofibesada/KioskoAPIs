package com.uade.tpo.Marketplace.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
 //forma de cambiar el nombre de la tabla (ya que order tabla reservada de mysql)
public class Order {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private int number;

    @PostPersist
    public void postPersist() {
        if (this.number == 0) {
            this.number = this.id.intValue();
        }
    }
    @Enumerated(EnumType.STRING) 
    @Column(nullable = false)
    private OrderState state = OrderState.PENDIENTE; //VALOR INICIAL POR DEFECTO
    
    @Column
    private Timestamp created_at;
    @Column
    private Timestamp updated_at;
    @Column
    private Timestamp deleteAt;
    @Column
    private Long total_amount;
    
   


    public void calculateTotalAmount() {
        BigDecimal total = BigDecimal.ZERO;

    // Sumar productos (precio * cantidad)
        for (OrderDetail detail : orderDetail) {
                BigDecimal productPrice = BigDecimal.valueOf((double) detail.getProduct().getPrice()); 
                BigDecimal lineTotal = productPrice.multiply(BigDecimal.valueOf(detail.getQuantity()));
                total = total.add(lineTotal);

                // Guardar subtotal en OrderDetail
                detail.setSubtotal(lineTotal.floatValue()); 
        }

        // Sumar el costo del método de entrega
        if (deliveryMethod != null) {
                total = total.add(BigDecimal.valueOf((double) deliveryMethod.getPrice()));
        }

        // Setear el total en la orden
        this.total_amount = total.longValue(); // o .floatValue() si lo querés igual que tu atributo
   }


    @ManyToOne //join entre las dos--> esta orden q es de 1 unico usuario se relaciona con esta orden
    @JoinColumn(name = "user_id", nullable = false) //si o si tiene q haber user_id sino ordern no se persiste (para eso nullable q es un booleano) 
    private User user;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetail;

    @OneToOne(mappedBy = "order")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "deliveryMethod_id", nullable = false)
    private DeliveryMethod deliveryMethod;




   


}
