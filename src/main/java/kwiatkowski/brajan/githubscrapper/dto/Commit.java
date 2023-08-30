package kwiatkowski.brajan.githubscrapper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a commit.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Commit {

  private String sha;
}
