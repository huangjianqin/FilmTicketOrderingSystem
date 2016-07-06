package org.filmticketorderingsystem.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 健勤 on 2016/4/25.
 */
@Entity
@Table(name = "actor_info")
public class Actor implements Serializable{
    @Id
    @Column(name = "actor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actor_id;
    @Column(name = "actor_name")
    private String actorName;
    @ManyToMany(targetEntity = Film.class, fetch = FetchType.LAZY)
    @JoinTable(name = "film_actor_info",
            joinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id")
            )
    private Set<Film> films;

    public Actor() {
        films = new HashSet<Film>();
    }

    public Integer getActor_id() {
        return actor_id;
    }

    public void setActor_id(Integer actor_id) {
        this.actor_id = actor_id;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actor)) return false;

        Actor actor = (Actor) o;

        if (actor_id != null ? !actor_id.equals(actor.actor_id) : actor.actor_id != null) return false;
        return !(actorName != null ? !actorName.equals(actor.actorName) : actor.actorName != null);

    }

    @Override
    public int hashCode() {
        int result = actor_id != null ? actor_id.hashCode() : 0;
        result = 31 * result + (actorName != null ? actorName.hashCode() : 0);
        return result;
    }

}
