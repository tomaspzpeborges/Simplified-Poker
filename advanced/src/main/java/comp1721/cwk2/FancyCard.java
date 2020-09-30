package comp1721.cwk2;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;

/**
 * Fancier version of Card which can provide images
 * (e.g., for use in card game applications that have a GUI)
 *
 * <p>Provided for use in COMP1721 Coursework 2 (advanced solution).</p>
 *
 * @author Nick Efford
 */
public class FancyCard extends Card {

  private static Map<String,Image> images = new HashMap<>();

  static {
    List<String> keys = new ArrayList<>();
    keys.add("back");
    for (Suit suit: Suit.values()) {
      for (Rank rank: Rank.values()) {
        keys.add(String.format("%c%c", rank.getSymbol(), suit.getSymbol()));
      }
    }

    for (String key: keys) {
      String filename = String.format("/images/%s.png", key);
      try (InputStream stream = FancyCard.class.getResourceAsStream(filename)) {
        if (stream != null) {
          images.put(key, new Image(stream));
        }
      }
      catch (IOException error) {
        // do nothing if image couldn't be loaded properly
      }
    }
  }

  /**
   * Creates a FancyCard object.
   *
   * @param rank Rank of the card
   * @param suit Suit of the card
   */
  public FancyCard(Rank rank, Suit suit) {
    super(rank, suit);
  }

  /**
   * Creates a FancyCard object, given its name as a string.
   *
   * @param name Name of card
   * @throws IllegalArgumentException if string is invalid
   * @see Card#Card(String)
   */
  public FancyCard(String name) {
    super(name);
  }

  /**
   * Provides the bitmap image associated with this card.
   *
   * @return This card's image
   */
  public Image getImage() {
    char r = getRank().getSymbol();
    char s = getSuit().getSymbol();
    String key = String.format("%c%c", r, s);
    return images.get(key);
  }

  /**
   * Provides a bitmap image representing the back of a card.
   *
   * @return Image of back of a card
   */
  public static Image getBackImage() {
    return images.get("back");
  }
}
