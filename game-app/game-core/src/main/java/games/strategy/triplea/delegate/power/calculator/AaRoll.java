package games.strategy.triplea.delegate.power.calculator;

import games.strategy.engine.data.Unit;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;
import org.triplea.java.collections.IntegerMap;

/** Calculate the roll for AA dice */
@Value
@Getter(AccessLevel.NONE)
class AaRoll implements RollCalculator {

  AvailableSupports supportFromFriends;
  AvailableSupports supportFromEnemies;

  @Override
  public RollValue getRoll(final Unit unit) {
    return RollValue.of(unit.getUnitAttachment().getMaxAaAttacks())
        .add(supportFromFriends.giveSupportToUnit(unit))
        .add(supportFromEnemies.giveSupportToUnit(unit));
  }

  @Override
  public Map<Unit, IntegerMap<Unit>> getSupportGiven() {
    return SupportCalculator.getCombinedSupportGiven(supportFromFriends, supportFromEnemies);
  }
}
