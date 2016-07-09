package org.filmticketorderingsystem.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by 健勤 on 2016/4/19.
 */
@Entity
@Table(name = "film_ticket")
public class FilmTicket implements Serializable {
    @Id
    @Column(name = "film_ticket_id")
    private Integer filmTicketId;
    @Column(name = "selected_seat_id")
    private String selectedSeat;
    @Column(name = "flag")
    private Integer flag;

    @ManyToOne(targetEntity = FilmSession.class)
    @JoinColumn(name = "film_session_id", referencedColumnName = "film_session_id")
    private FilmSession filmSession;

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

    public FilmTicket() {
        this.flag = 1;
    }

    public Integer getFilmTicketId() {
        return filmTicketId;
    }

    public void setFilmTicketId(Integer filmTicketId) {
        this.filmTicketId = filmTicketId;
    }

    public String getSelectedSeat() {
        return selectedSeat;
    }

    public void setSelectedSeat(String selectedSeat) {
        this.selectedSeat = selectedSeat;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public FilmSession getFilmSession() {
        return filmSession;
    }

    public void setFilmSession(FilmSession filmSession) {
        this.filmSession = filmSession;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmTicket)) return false;

        FilmTicket that = (FilmTicket) o;

        if (filmTicketId != null ? !filmTicketId.equals(that.filmTicketId) : that.filmTicketId != null) return false;
        if (selectedSeat != null ? !selectedSeat.equals(that.selectedSeat) : that.selectedSeat != null) return false;
        if (flag != null ? !flag.equals(that.flag) : that.flag != null) return false;
        return !(filmSession != null ? !filmSession.equals(that.filmSession) : that.filmSession != null);

    }

    @Override
    public int hashCode() {
        int result = filmTicketId != null ? filmTicketId.hashCode() : 0;
        result = 31 * result + (selectedSeat != null ? selectedSeat.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (filmSession != null ? filmSession.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "FilmTicket{" +
                "filmTicketId=" + filmTicketId +
                ", selectedSeat='" + selectedSeat + '\'' +
                ", flag=" + flag +
                ", filmSession=" + filmSession +
                ", order=" + order +
                '}';
    }
}
