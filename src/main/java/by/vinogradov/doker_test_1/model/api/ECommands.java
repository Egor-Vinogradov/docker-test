package by.vinogradov.doker_test_1.model.api;

public enum ECommands {
    PIN("/pin");

    private String command;

    ECommands(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
