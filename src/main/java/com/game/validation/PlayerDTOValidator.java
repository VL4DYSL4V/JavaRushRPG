package com.game.validation;

import com.game.dto.PlayerDTO;
import org.springframework.validation.Errors;

import java.util.Calendar;
import java.util.Date;

class PlayerDTOValidator {

    private static final Date MIN_REGISTRATION_DATE = new Date(100, Calendar.JANUARY, 1);
    private static final Date MAX_REGISTRATION_DATE = new Date(1101, Calendar.JANUARY, 1);
    private static final int MAX_NAME_LENGTH = 12;
    private static final int MAX_TITLE_LENGTH = 30;
    private static final int MIN_EXPERIENCE = 0;
    private static final int MAX_EXPERIENCE = 10_000_000;

    protected void validateName(String name, Errors errors) {
        if (name.isEmpty() || name.length() > MAX_NAME_LENGTH) {
            errors.rejectValue("name", "",
                    String.format("Name length must be equals to or less than %d and must not be blank", MAX_NAME_LENGTH));
        }
    }

    protected void validateTitle(String title, Errors errors) {
        if (title.length() > MAX_TITLE_LENGTH) {
            errors.rejectValue("title", "",
                    String.format("Title length must be equals or less than %d", MAX_TITLE_LENGTH));
        }
    }

    protected void validateExperience(Integer experience, Errors errors) {
        if (experience < MIN_EXPERIENCE || experience > MAX_EXPERIENCE) {
            errors.rejectValue("experience", "",
                    String.format("Experience must be between %d and %d (incl)", MIN_EXPERIENCE, MAX_EXPERIENCE));
        }
    }

    protected void validateBirthday(Date birthday, Errors errors) {
        if (MIN_REGISTRATION_DATE.after(birthday) || MAX_REGISTRATION_DATE.before(birthday)) {
            errors.rejectValue("birthday", "",
                    String.format("Birthday year must be between %d and %d (incl)",
                            MIN_REGISTRATION_DATE.getYear() + 1900, MAX_REGISTRATION_DATE.getYear() + 1899));
        }
    }

    protected void validate(PlayerDTO playerDTO, Errors errors) {
        if (playerDTO.getName() == null || playerDTO.getTitle() == null
                || playerDTO.getBirthday() == null || playerDTO.getExperience() == null) {
            errors.reject("", "Not all parameters are specified");
            return;
        }
        validateName(playerDTO.getName(), errors);
        validateTitle(playerDTO.getTitle(), errors);
        validateExperience(playerDTO.getExperience(), errors);
        validateBirthday(playerDTO.getBirthday(), errors);
    }
}
