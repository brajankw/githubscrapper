package kwiatkowski.brajan.githubscrapper.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a response for a GitHub repository.
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GithubRepositoryResponseDto {

  private String name;
  private String owner;
  private List<BranchResponseDto> branches;
}
