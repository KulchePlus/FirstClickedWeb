package com.firstclick.server;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.relation.InvalidRelationTypeException;
import java.util.HashMap;
import java.util.Objects;

public class Memory {
    static final Logger log = LoggerFactory.getLogger(Memory.class);
    private final HashMap<Integer, String> players = new HashMap<>();


    @Setter
    private Integer firstClickedId;

    @Setter
    private String firstClickedName;

    public void addPlayer(int userId, String name) {
        players.put(userId, name);
    }

    public void removePlayer(int userId) {
        players.remove(userId);
    }


    public void writeFirstClickedIfNull(Integer userId, String name) {
        if (firstClickedName == null || firstClickedName.equals("")) {
            log.info("Whiting firstClicked");
            writeFirstClicked(userId, name);
        }
    }

    private void writeFirstClicked(Integer userId, String name) {
        this.firstClickedId = userId;
        this.firstClickedName = name;
    }

    public void clearFirstClicked() {
        log.trace("Clearing firstClicked");
        Integer nullInt = null;
        writeFirstClicked(nullInt, null);
    }

    public Integer getFirstClickedId() {
        return Objects.requireNonNullElse(firstClickedId, -1);
    }

    public String getFirstClickedName() {
        return Objects.requireNonNullElse(firstClickedName, ""); //if null returnes ""
    }


}
