package com.tw.travel.ticketing.infrastructure.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ticketing_order")
@EntityListeners({AuditingEntityListener.class})
public class TicketingOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNo;


    private String flightNumber;

    @Enumerated(EnumType.STRING)
    private TicketingOrderStatus status;

    private String createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime flightDepartTime;

    public boolean canBeCancel() {
        return  LocalDateTime.now().isBefore(flightDepartTime);
    }



    public void cancel() {
        this.status = TicketingOrderStatus.CANCELED;
    }
}
