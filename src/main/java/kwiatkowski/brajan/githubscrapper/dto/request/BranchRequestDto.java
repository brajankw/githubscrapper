package kwiatkowski.brajan.githubscrapper.dto.request;

import kwiatkowski.brajan.githubscrapper.dto.Commit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BranchRequestDto {

  private String name;
  private Commit commit;
}
