package com.game.repository;

import com.game.constraint.PlayerConstraint;
import com.game.entity.Player;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PlayerRepository extends PagingAndSortingRepository<Player, Long> {

    @Query("SELECT p FROM Player p " +
            "WHERE (:#{#playerConstraint.name} IS NULL OR p.name LIKE CONCAT('%', :#{#playerConstraint.name}, '%')) " +
            "AND (:#{#playerConstraint.title} IS NULL OR p.title LIKE CONCAT('%',:#{#playerConstraint.title},'%')) " +
            "AND (:#{#playerConstraint.race} IS NULL OR p.race = :#{#playerConstraint.race} " +
            "AND (:#{#playerConstraint.profession} IS NULL OR p.profession = :#{#playerConstraint.profession})) " +
            "AND (:#{#playerConstraint.banned} IS NULL OR p.banned = :#{#playerConstraint.banned}) " +
            "AND (:#{#playerConstraint.minExperience} IS NULL OR p.experience >= :#{#playerConstraint.minExperience}) " +
            "AND (:#{#playerConstraint.maxExperience} IS NULL OR p.experience <= :#{#playerConstraint.maxExperience}) " +
            "AND (:#{#playerConstraint.minLevel} IS NULL OR p.level >= :#{#playerConstraint.minLevel}) " +
            "AND (:#{#playerConstraint.maxLevel} IS NULL OR p.level <= :#{#playerConstraint.maxLevel}) " +
            "AND (:#{#playerConstraint.after} IS NULL OR p.birthday >= :#{#playerConstraint.after}) " +
            "AND (:#{#playerConstraint.before} IS NULL OR p.birthday <= :#{#playerConstraint.before}) ")
    List<Player> getPlayerList(@Param("playerConstraint") PlayerConstraint playerConstraint,
                               Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Player p SET " +
            "p.name = CASE WHEN :#{#player.name} IS NOT NULL THEN :#{#player.name} ELSE p.name END, " +
            "p.birthday = CASE WHEN :#{#player.birthday} IS NOT NULL THEN :#{#player.birthday} ELSE p.birthday END, " +
            "p.profession = CASE WHEN :#{#player.profession} IS NOT NULL THEN :#{#player.profession} ELSE p.profession END, " +
            "p.race = CASE WHEN :#{#player.race} IS NOT NULL THEN :#{#player.race} ELSE p.race END, " +
            "p.title = CASE WHEN :#{#player.title} IS NOT NULL THEN :#{#player.title} ELSE p.title END, " +
            "p.banned = CASE WHEN :#{#player.banned} IS NOT NULL THEN :#{#player.banned} ELSE p.banned END," +
            "p.experience = CASE WHEN :#{#player.experience} IS NOT NULL THEN :#{#player.experience} ELSE p.experience END, " +
            "p.level = CASE WHEN :#{#player.level} IS NOT NULL THEN :#{#player.level} ELSE p.level END, " +
            "p.untilNextLevel = CASE WHEN :#{#player.untilNextLevel} IS NOT NULL THEN :#{#player.untilNextLevel} ELSE p.untilNextLevel END " +
            "WHERE p.id = :#{#id}")
    void update(@Param("id") Long id,
                @Param("player") Player player);
}
