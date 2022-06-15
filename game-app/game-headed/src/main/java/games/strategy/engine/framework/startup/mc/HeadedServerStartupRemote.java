package games.strategy.engine.framework.startup.mc;

import games.strategy.engine.framework.HeadlessAutoSaveType;
import games.strategy.engine.framework.message.PlayerListing;
import games.strategy.net.INode;
import java.util.Set;

public class HeadedServerStartupRemote implements IServerStartupRemote {

  private final ServerModelView serverModelView;

  public HeadedServerStartupRemote(ServerModelView serverModelView) {
    this.serverModelView = serverModelView;
  }

  @Override
  public PlayerListing getPlayerListing() {
    return serverModelView.getPlayerListing();
  }

  @Override
  public void takePlayer(final INode who, final String playerName) {
    serverModelView.takePlayer(who, playerName);
  }

  @Override
  public void releasePlayer(final INode who, final String playerName) {
    serverModelView.releasePlayer(who, playerName);
  }

  @Override
  public void disablePlayer(final String playerName) {}

  @Override
  public void enablePlayer(final String playerName) {}

  @Override
  public boolean isGameStarted(final INode newNode) {
    return serverModelView.isGameStarted(newNode);
  }

  @Override
  public boolean getIsServerHeadless() {
    return false;
  }

  @Override
  public byte[] getSaveGame() {
    return serverModelView.getSaveGame();
  }

  @Override
  public byte[] getGameOptions() {
    return serverModelView.getGameOptions();
  }

  @Override
  public Set<String> getAvailableGames() {
    return Set.of();
  }

  @Override
  public void changeServerGameTo(final String gameName) {}

  @Override
  public void changeToLatestAutosave(final HeadlessAutoSaveType autoSaveType) {}

  @Override
  public void changeToGameSave(final byte[] bytes, final String fileName) {}

  @Override
  public void changeToGameOptions(final byte[] bytes) {}
}
