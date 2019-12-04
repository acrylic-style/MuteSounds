package xyz.acrylicstyle.mutesounds.utils;

public abstract class PeriodCommand {
    public abstract void execute(String message, String originalMessage, String[] args);
    public String getDescription() {
        return "Description not defined.";
    }
}
