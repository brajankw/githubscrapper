package kwiatkowski.brajan.githubscrapper.controller;

import java.util.List;
import kwiatkowski.brajan.githubscrapper.dto.response.GithubRepositoryResponseDto;
import kwiatkowski.brajan.githubscrapper.service.GithubRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/repositories")
@RestController
@RequiredArgsConstructor
public class GithubRepositoryController {

  private final GithubRepositoryService githubRepositoryService;

  @GetMapping(value = "/{username}",
      headers = "Accept=application/json")
  public List<GithubRepositoryResponseDto> getAllUserRepositories(@PathVariable String username) {

    return githubRepositoryService.getAllUserRepositories(username);
  }
}
