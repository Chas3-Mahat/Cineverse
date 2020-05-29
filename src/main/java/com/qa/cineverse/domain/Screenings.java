package com.qa.cineverse.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
    @Table(name = "screenings")
    @Proxy(lazy=false)
    public class Screenings {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "screenings_id")
        @Getter
        @Setter
        private Long screeningsId;
        @Column(name = "movie_date_time")  /// YYYY-MM-DDT00:00:00
        @Getter
        @Setter
        private LocalDateTime movieDateTime;
        @Column(name = "screen_number")
        @Getter
        @Setter
        private Long screenNumber;
        @Column(name = "screen_type")
        @Getter
        @Setter
        private String screenType;
        @Column(name = "movie_name")
        @Getter
        @Setter
        private String movieName;

        @JsonIgnoreProperties("screenings")
        @ManyToMany(targetEntity = Customers.class, fetch = FetchType.LAZY, cascade= CascadeType.ALL)
        @JoinTable(
                name = "screenings_customers",
                joinColumns=@JoinColumn(name="screenings_id"),
                inverseJoinColumns=@JoinColumn(name="customers_id"))
        @Getter
        @Setter
        private List<Customers> customers = new ArrayList<>();

        public Screenings() {
        }

    public Screenings(LocalDateTime movieDateTime, Long screenNumber, String screenType, String movieName) {
        this.movieDateTime = movieDateTime;
        this.screenNumber = screenNumber;
        this.screenType = screenType;
        this.movieName = movieName;
    }

    public Screenings(Long screeningsId, LocalDateTime movieDateTime, Long screenNumber, String screenType, String movieName) {
        this.screeningsId = screeningsId;
        this.movieDateTime = movieDateTime;
        this.screenNumber = screenNumber;
        this.screenType = screenType;
        this.movieName = movieName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Screenings))
            return false;
        Screenings that = (Screenings) o;
        return getScreeningsId ().equals (that.getScreeningsId ()) &&
                getMovieDateTime ().equals (that.getMovieDateTime ()) &&
                getScreenType ().equals (that.getScreenType ()) &&
                getScreenNumber ().equals (that.getScreenNumber ()) &&
                getMovieName ().equals (that.getMovieName ()) &&
                getCustomers ().equals (that.getCustomers ());
    }

    @Override
    public int hashCode() {
        return Objects.hash (getScreeningsId (), getMovieDateTime (), getScreenNumber (), getScreenType (), getMovieName (), getCustomers ());
    }

    @Override
    public String toString() {
        return "Screenings{" +
                "screeningsId=" + screeningsId +
                ", movieDateTime=" + movieDateTime +
                ", screenNumber=" + screenNumber +
                ", screenType='" + screenType + '\'' +
                ", movieName='" + movieName + '\'' +
                ", customers=" + customers +
                '}';
    }
}
