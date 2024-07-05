package pja.edu.pl.s26772.restaurantmanagement.models;

import jakarta.persistence.*;

import java.util.List;

@Entity()
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "tables",fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "tables",fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private List<Visit> visits;

    public Tables() {}

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
