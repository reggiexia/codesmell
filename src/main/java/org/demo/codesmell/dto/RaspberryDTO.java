package org.demo.codesmell.dto;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class RaspberryDTO extends FruitDTO {

    private boolean ripe;

    private static Color FLESH;

    public void ripe() {
        log.info("Raspberry is ripe.");
    }
}
