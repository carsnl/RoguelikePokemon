package game.time;

public enum TimePeriod {
  DAY("day-time"),
  NIGHT("night-time"),
  CURSED_NIGHT("cursed-night-time");

  public final String label;

  TimePeriod(String label) {
    this.label = label;
  }
}
