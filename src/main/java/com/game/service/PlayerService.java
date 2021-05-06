package com.game.service;

import com.game.constraint.PageConstraint;
import com.game.constraint.PlayerConstraint;
import com.game.controller.PlayerOrder;
import com.game.entity.Player;

import java.util.List;

public interface PlayerService {

    List<Player> getPlayerList(PlayerConstraint playerConstraint,
                               PlayerOrder playerOrder, PageConstraint pageConstraint);

    long getPlayersCount(PlayerConstraint playerConstraint);

    Player create(Player player);

    Player getPlayer(Long id);

    void update(Long id, Player player);

    void delete(Long id);

    boolean exists(Long id);
}
