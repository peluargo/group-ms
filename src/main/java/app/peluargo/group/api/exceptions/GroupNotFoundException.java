package app.peluargo.group.api.exceptions;

public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException() {
        super("User not found");
    }
}

