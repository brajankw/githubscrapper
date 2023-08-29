package kwiatkowski.brajan.githubscrapper.dto.response;

import java.util.List;
import kwiatkowski.brajan.githubscrapper.dto.response.BranchResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@ToString
public class GithubRepositoryResponseDto {
  private String name;
  private String owner;
  private List<BranchResponseDto> branches;
}
