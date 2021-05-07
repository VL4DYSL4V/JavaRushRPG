package com.game.repository;

import com.game.constraint.PlayerConstraint;
import com.game.entity.Player;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

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

}
