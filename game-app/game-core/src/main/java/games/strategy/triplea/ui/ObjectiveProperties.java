package games.strategy.triplea.ui;

import games.strategy.triplea.ResourceLoader;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/** Loads objective text from objectives.properties. */
public class ObjectiveProperties {
  static final String GROUP_PROPERTY = "TABLEGROUP";
  private static final String PROPERTY_FILE = "objectives.properties";
  private static final String OBJECTIVES_PANEL_NAME = "Objectives.Panel.Name";
  private final Properties properties;

  public ObjectiveProperties(final ResourceLoader resourceLoader) {
    properties = resourceLoader.loadPropertyFile(PROPERTY_FILE);
  }

  public String getName() {
    return properties.getProperty(ObjectiveProperties.OBJECTIVES_PANEL_NAME, "Objectives");
  }

  public Set<Entry<Object, Object>> entrySet() {
    return properties.entrySet();
  }
}
