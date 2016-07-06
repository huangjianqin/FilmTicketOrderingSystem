package org.filmticketorderingsystem.bean;

/**
 * Created by 健勤 on 2016/7/6.
 */
public class ActorBean {
    private Integer actor_id;
    private String actorName;
    private Integer state;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActorBean)) return false;

        ActorBean actorBean = (ActorBean) o;

        return !(actor_id != null ? !actor_id.equals(actorBean.actor_id) : actorBean.actor_id != null);

    }

    @Override
    public int hashCode() {
        return actor_id != null ? actor_id.hashCode() : 0;
    }
}
