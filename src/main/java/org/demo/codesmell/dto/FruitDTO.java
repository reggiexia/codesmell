package org.demo.codesmell.dto;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
public class FruitDTO {

    public Season ripe;
    protected Color flesh;

    public void ripe() {
        log.info("This fruit is ripe.");
    }

    public enum Season {
        SPRING, SUMMER, AUTUMN, WINTER
    }

    public enum Color {
        RED, YELLOW, GREEN, BLUE
    }
}
