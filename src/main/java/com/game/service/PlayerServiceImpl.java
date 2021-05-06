package com.game.service;

import com.game.constraint.PageConstraint;
import com.game.constraint.PlayerConstraint;
import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.repository.PlayerRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("playerService")
public final class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    private static int calculateCurrentLevel(int experience) {
        return (int) (Math.sqrt(2500 + 200 * experience) - 50) / 100;
    }

    private static int calculateUntilNextLevel(int level, int experience) {
        return 50 * (level + 1) * (level + 2) - experience;
    }

    @Override
    public List<Player> getPlayerList(PlayerConstraint playerConstraint,
                                      PlayerOrder playerOrder, PageConstraint pageConstraint) {
        playerOrder = (playerOrder == null) ? PlayerOrder.getDefault() : playerOrder;
        Sort sort = Sort.by(Sort.Direction.ASC, playerOrder.getFieldName());
        if (pageConstraint == null) {
            return playerRepository.getPlayerList(playerConstraint, null);
        }
        Integer page = pageConstraint.getPageNumber();
        Integer size = pageConstraint.getPageSize();
        Pageable pageRequest = PageRequest.of(page, size, sort);
        return playerRepository.getPlayerList(playerConstraint, pageRequest);
    }

    @Override
    public long getPlayersCount(PlayerConstraint playerConstraint) {
        return getPlayerList(playerConstraint, null, null).size();
    }

    @Override
    public Player create(Player player) {
        Player playerToStore = new Player(player);
        playerToStore.setLevel(calculateCurrentLevel(playerToStore.getExperience()));
        playerToStore.setUntilNextLevel(calculateUntilNextLevel(playerToStore.getLevel(),
                playerToStore.getExperience()));
        return playerRepository.save(playerToStore);
    }

    @Override
    public Player getPlayer(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Long id, Player player) {
        if(player.getExperience() != null){
            player = new Player(player);
            int exp = player.getExperience();
            int level = calculateCurrentLevel(exp);
            player.setLevel(level);
            int untilNextLevel = calculateUntilNextLevel(level, exp);
            player.setUntilNextLevel(untilNextLevel);
        }
        playerRepository.update(id, player);
    }

    @Override
    public void delete(Long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return playerRepository.existsById(id);
    }
}

