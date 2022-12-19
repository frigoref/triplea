package games.strategy.engine.framework;

/** In this class commonly used constants are getting defined. */
public final class GameRunner {
  public static final String TRIPLEA_HEADLESS = "triplea.headless";
  public static final String BOT_GAME_HOST_COMMENT = "automated_host";
  public static final String BOT_GAME_HOST_NAME_PREFIX = "Bot";
  public static final int PORT = 3300;

  public static boolean headless() {
    return Boolean.parseBoolean(System.getProperty(TRIPLEA_HEADLESS, "false"));
  }
}
