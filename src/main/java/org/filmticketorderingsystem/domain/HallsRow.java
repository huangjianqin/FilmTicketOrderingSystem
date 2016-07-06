package org.filmticketorderingsystem.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by 健勤 on 2016/4/19.
 */
@Entity
@Table(name = "halls_row_info")
public class HallsRow implements Serializable {
    @Id
    @Column(name = "row_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rowId;
    @Column(name = "row_name")
    private String rowName;
    @Column(name = "col_length")
    private Integer colLength;

    @ManyToOne(targetEntity = CinemaHall.class)
    @JoinColumn(name = "cinema_hall_id", referencedColumnName = "cinema_hall_id")
    private CinemaHall cinemaHall;



    public HallsRow() {
    }

    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public Integer getColLength() {
        return colLength;
    }

    public void setColLength(Integer colLength) {
        this.colLength = colLength;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HallsRow)) return false;

        HallsRow hallsRow = (HallsRow) o;

        if (rowId != null ? !rowId.equals(hallsRow.rowId) : hallsRow.rowId != null) return false;
        return !(cinemaHall != null ? !cinemaHall.equals(hallsRow.cinemaHall) : hallsRow.cinemaHall != null);

    }

    @Override
    public int hashCode() {
        int result = rowId != null ? rowId.hashCode() : 0;
        result = 31 * result + (cinemaHall != null ? cinemaHall.hashCode() : 0);
        return result;
    }
}
