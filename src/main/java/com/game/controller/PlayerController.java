package com.game.controller;

import com.game.constraint.PageConstraint;
import com.game.constraint.PlayerConstraint;
import com.game.dto.PlayerCreateDTO;
import com.game.dto.PlayerUpdateDTO;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.service.PlayerService;
import com.game.validation.PlayerCreateDTOValidator;
import com.game.validation.PlayerUpdateDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/players")
public final class PlayerController {

    private final PlayerService playerService;
    private final PlayerCreateDTOValidator playerCreateDTOValidator;
    private final PlayerUpdateDTOValidator playerUpdateDTOValidator;

    @Autowired
    public PlayerController(PlayerService playerService,
                            PlayerCreateDTOValidator playerCreateDTOValidator,
                            PlayerUpdateDTOValidator playerUpdateDTOValidator) {
        this.playerService = playerService;
        this.playerCreateDTOValidator = playerCreateDTOValidator;
        this.playerUpdateDTOValidator = playerUpdateDTOValidator;
    }

    @InitBinder("playerCreateDTO")
    public void playerCreateBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(playerCreateDTOValidator);
    }

    @InitBinder("playerUpdateDTO")
    public void playerUpdateBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(playerUpdateDTOValidator);
    }

    @GetMapping
    public ResponseEntity<List<Player>> getPlayersList(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Race race,
            @RequestParam(required = false) Profession profession,
            @RequestParam(required = false) Long after,
            @RequestParam(required = false) Long before,
            @RequestParam(required = false) Boolean banned,
            @RequestParam(required = false) Integer minExperience,
            @RequestParam(required = false) Integer maxExperience,
            @RequestParam(required = false) Integer minLevel,
            @RequestParam(required = false) Integer maxLevel,
            @RequestParam(required = false) PlayerOrder order,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize) {
        PlayerConstraint playerConstraint = new PlayerConstraint(name, title, race,
                profession, fromMillis(after), fromMillis(before), banned, minExperience, maxExperience, minLevel, maxLevel);
        PageConstraint pageConstraint = new PageConstraint(pageNumber, pageSize);
        List<Player> players = playerService.getPlayerList(playerConstraint, order, pageConstraint);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getPlayersCount(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Race race,
            @RequestParam(required = false) Profession profession,
            @RequestParam(required = false) Long after,
            @RequestParam(required = false) Long before,
            @RequestParam(required = false) Boolean banned,
            @RequestParam(required = false) Integer minExperience,
            @RequestParam(required = false) Integer maxExperience,
            @RequestParam(required = false) Integer minLevel,
            @RequestParam(required = false) Integer maxLevel) {
        PlayerConstraint playerConstraint = new PlayerConstraint(name, title, race,
                profession, fromMillis(after), fromMillis(before), banned, minExperience, maxExperience, minLevel, maxLevel);
        long count = playerService.getPlayersCount(playerConstraint);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Player> create(
            @Validated @RequestBody PlayerCreateDTO playerCreateDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Player out = playerService.create(playerCreateDTO.toPlayer());
        return new ResponseEntity<>(out, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable("id") String id) {
        if (invalidId(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Long playerId = Long.parseLong(id);
        Player player = playerService.getPlayer(playerId);
        if (player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Player> update(
            @PathVariable("id") String id,
            @Validated @RequestBody PlayerUpdateDTO playerUpdateDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors() || invalidId(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Long playerId = Long.parseLong(id);
        Optional<Player> updateResult = playerService.update(playerId, playerUpdateDTO.toPlayer());
        return updateResult.map(player -> new ResponseEntity<>(player, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if (invalidId(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Long playerId = Long.parseLong(id);
        if (!playerService.exists(playerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playerService.delete(playerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Date fromMillis(Long millis) {
        return millis == null ? null : new Date(millis);
    }

    private boolean invalidId(String id) {
        try {
            long playerId = Long.parseLong(id);
            if (playerId <= 0) {
                return true;
            }
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

}
