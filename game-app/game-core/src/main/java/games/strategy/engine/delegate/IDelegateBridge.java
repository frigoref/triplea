package games.strategy.engine.delegate;

import games.strategy.engine.data.Change;
import games.strategy.engine.data.GameData;
import games.strategy.engine.data.GamePlayer;
import games.strategy.engine.data.UnitType;
import games.strategy.engine.display.IDisplay;
import games.strategy.engine.history.IDelegateHistoryWriter;
import games.strategy.engine.player.Player;
import games.strategy.engine.random.IRandomStats.DiceType;
import games.strategy.triplea.ResourceLoader;
import games.strategy.triplea.util.TuvCostsCalculator;
import java.util.Optional;
import org.triplea.http.client.web.socket.messages.WebSocketMessage;
import org.triplea.java.collections.IntegerMap;
import org.triplea.sound.ISound;

/**
 * A class that communicates with the Delegate. DelegateBridge coordinates communication between the
 * Delegate and both the players and the game data. The reason for communicating through a
 * DelegateBridge is to achieve network transparency. The delegateBridge allows the Delegate to talk
 * to the player in a safe manner.
 */
public interface IDelegateBridge {
  /**
   * Equivalent to getRemotePlayer(getPlayerId()).
   *
   * @return remote for the current player.
   */
  Player getRemotePlayer();

  /** Get a remote reference to the given player. */
  Player getRemotePlayer(GamePlayer gamePlayer);

  GamePlayer getGamePlayer();

  /**
   * Add a change to game data. Use this rather than changing gameData directly since this method
   * allows us to send the changes to other machines.
   */
  void addChange(Change change);

  /** equivalent to getRandom(max,1,annotation)[0]. */
  int getRandom(int max, GamePlayer player, DiceType diceType, String annotation);

  /**
   * Return a random value to be used by the delegate.
   *
   * <p>Delegates should not use random data that comes from any other source.
   *
   * <p>
   *
   * @param annotation a string used to describe the random event.
   */
  int[] getRandom(int max, int count, GamePlayer player, DiceType diceType, String annotation);

  /**
   * return the delegate history writer for this game.
   *
   * <p>The delegate history writer allows writing to the game history.
   */
  IDelegateHistoryWriter getHistoryWriter();

  /**
   * Return an object that implements the IDisplay interface for the game.
   *
   * <p>Methods called on this returned object will be invoked on all displays in the game,
   * including those on remote machines
   */
  IDisplay getDisplayChannelBroadcaster();

  /**
   * Return an object that implements the ISound interface for the game.
   *
   * <p>Methods called on this returned object will be invoked on all sound channels in the game,
   * including those on remote machines
   */
  ISound getSoundChannelBroadcaster();

  /**
   * After this step finishes executing, the next delegate will not be called.
   *
   * <p>This method allows the delegate to signal that the game is over, but does not force the ui
   * or the display to shutdown.
   */
  void stopGameSequence(String status, String title);

  void leaveDelegateExecution();

  void enterDelegateExecution();

  GameData getData();

  void sendMessage(WebSocketMessage webSocketMessage);

  /**
   * Allow delegate code to access a {@link ResourceLoader}. Implementations may choose to return an
   * empty optional to prevent messaging in simulation scenarios.
   */
  Optional<ResourceLoader> getResourceLoader();

  default IntegerMap<UnitType> getCostsForTuv(final GamePlayer player) {
    return new TuvCostsCalculator().getCostsForTuv(player);
  }
}
