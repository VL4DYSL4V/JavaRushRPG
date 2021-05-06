package com.game.dto;

import com.game.entity.Profession;
import com.game.entity.Race;

import java.util.Date;

public final class PlayerUpdateDTO extends PlayerDTO {

    public PlayerUpdateDTO() {
    }

    public PlayerUpdateDTO(String name, String title, Race race, Profession profession, Integer experience, Date birthday, Boolean banned) {
        super(name, title, race, profession, experience, birthday, banned);
    }

    @Override
    public String toString() {
        return "PlayerUpdateDTO{" +
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
