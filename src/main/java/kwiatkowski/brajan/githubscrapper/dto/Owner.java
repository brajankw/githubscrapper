package kwiatkowski.brajan.githubscrapper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing an owner (e.g., user or organization).
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Owner {

  private String login;
}
