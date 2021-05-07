package com.game.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;// ID игрока
    @Column(name = "name")
    private String name;// Имя персонажа (до 12 знаков включительно)
    @Column(name = "title")
    private String title;// Титул персонажа (до 30 знаков включительно)
    @Column(name = "race")
    @Enumerated(value = EnumType.STRING)
    private Race race;// Расса персонажа
    @Column(name = "profession")
    @Enumerated(value = EnumType.STRING)
    private Profession profession;// Профессия персонажа
    @Column(name = "experience")
    private Integer experience;// Опыт персонажа. Диапазон значений 0..10,000,000
    @Column(name = "level")
    private Integer level;//Уровень персонажа
    @Column(name = "untilNextLevel")
    private Integer untilNextLevel;// Остаток опыта до следующего уровня
    @Column(name = "birthday")
    private Date birthday;// Дата регистрации. Диапазон значений года 2000..3000 включительно
    @Column(name = "banned")
    private Boolean banned = Boolean.FALSE;// Забанен / не забанен. Также должна присутствовать бизнес-логика

    public Player() {
    }

    public Player(Player player) {
        this.id = player.id;
        this.name = player.name;
        this.title = player.title;
        this.race = player.race;
        this.profession = player.profession;
        this.experience = player.experience;
        this.level = player.level;
        this.untilNextLevel = player.untilNextLevel;
        this.birthday = player.birthday == null ? null : new Date(player.birthday.getTime());
        setBanned(player.banned);
    }

    public Player(Long id, String name, String title,
                  Race race, Profession profession, Integer experience,
                  Integer level, Integer untilNextLevel,
                  Date birthday, Boolean banned) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.experience = experience;
        this.level = level;
        this.untilNextLevel = untilNextLevel;
        this.birthday = birthday;
        setBanned(banned);
    }

    public void copyNonNullFields(Player source) {
        if (source.getName() != null) {
            this.setName(source.getName());
        }
        if (source.getTitle() != null) {
            this.setTitle(source.getTitle());
        }
        if (source.getExperience() != null) {
            this.setExperience(source.getExperience());
        }
        if (source.getBanned() != null) {
            this.setBanned(source.getBanned());
        }
        if (source.getBirthday() != null) {
            this.setBirthday(source.getBirthday());
        }
        if (source.getProfession() != null) {
            this.setProfession(source.getProfession());
        }
        if (source.getRace() != null) {
            this.setRace(source.getRace());
        }
        if (source.getLevel() != null) {
            this.setLevel(source.getLevel());
        }
        if (source.getUntilNextLevel() != null) {
            this.setUntilNextLevel(source.getUntilNextLevel());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public void setUntilNextLevel(Integer untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
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
        if (banned != null) {
            this.banned = banned;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(name, player.name) &&
                Objects.equals(title, player.title) &&
                race == player.race &&
                profession == player.profession &&
                Objects.equals(experience, player.experience) &&
                Objects.equals(level, player.level) &&
                Objects.equals(untilNextLevel, player.untilNextLevel) &&
                Objects.equals(birthday, player.birthday) &&
                Objects.equals(banned, player.banned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, title, race, profession, experience, level, untilNextLevel, birthday, banned);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", race=" + race +
                ", profession=" + profession +
                ", experience=" + experience +
                ", level=" + level +
                ", untilNextLevel=" + untilNextLevel +
                ", birthday=" + birthday +
                ", banned=" + banned +
                '}';
    }
}
