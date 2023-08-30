package kwiatkowski.brajan.githubscrapper.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a response for a branch.
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BranchResponseDto {

  private String name;
  private String sha;
}
