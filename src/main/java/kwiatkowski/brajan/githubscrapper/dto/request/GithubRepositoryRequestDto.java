package kwiatkowski.brajan.githubscrapper.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import kwiatkowski.brajan.githubscrapper.dto.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing a request for a GitHub repository.
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GithubRepositoryRequestDto {

  private String name;
  private Owner owner;
  @JsonProperty("branches_url")
  private String branchesUrl;
  private boolean fork;
}
