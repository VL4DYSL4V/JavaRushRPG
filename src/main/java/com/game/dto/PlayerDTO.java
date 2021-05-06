package com.game.dto;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;

import java.util.Date;
import java.util.Objects;

public abstract class PlayerDTO {

    protected String name;
    protected String title;
    protected Race race;
    protected Profession profession;
    protected Integer experience;
    protected Date birthday;
    protected Boolean banned;

    public PlayerDTO() {
    }

    public PlayerDTO(String name, String title, Race race, Profession profession, Integer experience, Date birthday, Boolean banned) {
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.experience = experience;
        this.birthday = birthday;
        this.banned = banned;
    }

    public Player toPlayer() {
        return new Player(null, name, title, race, profession, experience, null, null, birthday, banned);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerDTO playerDTO = (PlayerDTO) o;
        return Objects.equals(name, playerDTO.name) &&
                Objects.equals(title, playerDTO.title) &&
                race == playerDTO.race &&
                profession == playerDTO.profession &&
                Objects.equals(experience, playerDTO.experience) &&
                Objects.equals(birthday, playerDTO.birthday) &&
                Objects.equals(banned, playerDTO.banned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, title, race, profession, experience, birthday, banned);
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", race=" + race +
                ", profession=" + profession +
                ", experience=" + experience +
                ", birthday=" + birthday +
                ", banned=" + banned +
                '}';
    }
}
